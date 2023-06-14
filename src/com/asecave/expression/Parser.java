package com.asecave.expression;

import java.util.LinkedList;

import com.asecave.expression.commands.Command;
import com.asecave.expression.commands.CommandAdd;
import com.asecave.expression.commands.CommandClass;
import com.asecave.expression.commands.CommandDecrease;
import com.asecave.expression.commands.CommandHalt;
import com.asecave.expression.commands.CommandIn;
import com.asecave.expression.commands.CommandIncrease;
import com.asecave.expression.commands.CommandJump;
import com.asecave.expression.commands.CommandLoad;
import com.asecave.expression.commands.CommandOut;
import com.asecave.expression.commands.CommandResult;
import com.asecave.expression.commands.CommandStore;
import com.asecave.expression.commands.CommandSub;

public class Parser {

	private static LinkedList<Name> classes = new LinkedList<>();
	private static LinkedList<StorageElement> storage = new LinkedList<>();

	private static int classCounter = 0;
	private static int storageCounter = 0;

	public class Name {

		int id;
		String name;

		public Name(String name, int id) {
			this.name = name;
			this.id = id;
		}
	}

	public class StorageElement extends Name {

		int value;
		int codeLine;

		public StorageElement(String name, int id, int value) {
			super(name, id);
			this.value = value;
		}
	}

	public LinkedList<Command> parse(LinkedList<Token> tokens) {

		LinkedList<Command> commands = new LinkedList<>();

		for (Token t : tokens) {
			String lexeme = t.getLexeme();

			Name name;
			switch (t.getType()) {
			case Token.CLASS_DECLARATION:
				name = new Name(lexeme.substring(0, lexeme.indexOf(']')), classCounter++);
				classes.add(name);
				break;
			case Token.LOCATION:
				String locName = lexeme.substring(0, lexeme.length());
				storage.add(new StorageElement(locName, storageCounter++, t.getLine()));
				break;
			case Token.VAR:
				String varName;
				int initialValue = 0;
				if (lexeme.indexOf('=') != -1) {
					varName = lexeme.substring(0, lexeme.replaceAll(" ", "").indexOf('='));
					String rawInitialValue = lexeme.substring(lexeme.indexOf('=') + 1).strip();
					if (rawInitialValue.matches("[0-9]+")) {
						initialValue = Integer.parseInt(rawInitialValue);
						if (initialValue > 255 || initialValue < 0) {
							System.err.println("Number out of range at " + t.getLine() + ": " + initialValue);
						}
					} else {
						if (rawInitialValue.length() == 3 && rawInitialValue.charAt(0) == '\'' && rawInitialValue.charAt(2) == '\'') {
							initialValue = rawInitialValue.charAt(1);
						} else {
							System.err.println("Invalid value at " + t.getLine() + ": " + rawInitialValue);
						}
					}
				} else {
					varName = lexeme;
				}
				StorageElement var = new StorageElement(varName, storageCounter++, initialValue);
				storage.add(var);
				break;
			}
		}

		for (Token t : tokens) {
			String lexeme = t.getLexeme();

			switch (t.getType()) {
			case Token.CLASS_CALL:
				for (Name n : classes) {
					if (n.name.equals(lexeme)) {
						commands.add(new CommandLoad(t.getLine(), getClass(n.name), 0));
						commands.add(new CommandClass(t.getLine(), 0));
					}
				}
				break;
			case Token.GOTO:
				for (StorageElement e : storage) {
					if (e.name.equals(lexeme)) {
						commands.add(new CommandLoad(t.getLine(), getVar(e.name), 0));
						commands.add(new CommandJump(t.getLine(), 0));
					}
				}
				break;
			case Token.IF:
				String condition = lexeme.substring(lexeme.indexOf('('), lexeme.indexOf(')') - 1);
				condition = condition.replace(" ", "");
				char comparator;
				int compPos;
				if ((compPos = condition.indexOf('<')) != -1) {
					comparator = '<';
				} else if ((compPos = condition.indexOf('>')) != -1) {
					comparator = '>';
				} else {
					compPos = condition.indexOf('=');
					comparator = '=';
				}
				
				String v1name = condition.substring(0, compPos - 1);
				String v2name = condition.substring(compPos);
				int v1id = -1;
				int v2id = -1;
				for (StorageElement e : storage) {
					if (e.name.equals(v1name)) {
						v1id = getVar(e.name);
					} else if (e.name.equals(v2name)) {
						v2id = getVar(e.name);
					}
				}
				
			}
		}

		for (Token t : tokens) {
			String lexeme = t.getLexeme().replaceAll(" ", "");

			if (t.getType() == Token.EXPRESSION) {
				int eq = lexeme.indexOf('=');
				if (eq > 0 && (lexeme.indexOf('+') > 0 || lexeme.indexOf('-') > 0)) {
					int eqIndex = lexeme.indexOf('=');
					int operatorIndex = Math.max(lexeme.indexOf('+'), lexeme.indexOf('-'));
					String setVar = lexeme.substring(0, eqIndex);
					String var1 = lexeme.substring(eqIndex + 1, operatorIndex);
					String var2 = lexeme.substring(operatorIndex + 1);

					commands.add(new CommandLoad(t.getLine(), getVar(var1), 0));
					commands.add(new CommandLoad(t.getLine(), getVar(var2), 1));

					if (lexeme.charAt(operatorIndex) == '+') {
						commands.add(new CommandAdd(t.getLine(), 0, 1));
					} else {
						commands.add(new CommandSub(t.getLine(), 0, 1));
					}

					commands.add(new CommandResult(t.getLine(), 2));
					commands.add(new CommandStore(t.getLine(), getVar(setVar), 2));

				} else if (t.getLexeme().endsWith("++")) {

					String var = lexeme.substring(0, lexeme.length() - 2);
					commands.add(new CommandLoad(t.getLine(), getVar(var), 0));
					commands.add(new CommandIncrease(t.getLine(), 0));
					commands.add(new CommandStore(t.getLine(), getVar(var), 0));

				} else if (t.getLexeme().endsWith("--")) {

					String var = lexeme.substring(0, lexeme.length() - 2);
					commands.add(new CommandLoad(t.getLine(), getVar(var), 0));
					commands.add(new CommandDecrease(t.getLine(), 0));
					commands.add(new CommandStore(t.getLine(), getVar(var), 0));

				} else {
					System.err.println("Syntax error at line " + t.getLine() + ": " + t.getLexeme());
				}
			}
		}

		for (Token t : tokens) {
			switch (t.getType()) {
			case Token.IN:
				commands.add(new CommandIn(t.getLine(), 0));
				commands.add(new CommandStore(t.getLine(), getVar(t.getLexeme()), 0));
				break;
			case Token.OUT:
				commands.add(new CommandLoad(t.getLine(), getVar(t.getLexeme()), 0));
				commands.add(new CommandOut(t.getLine(), 0));
				break;
			case Token.HALT:
				commands.add(new CommandHalt(t.getLine()));
				break;
			}
		}

		return commands;
	}

	private int getVar(String name) {
		for (StorageElement n : storage) {
			if (n.name.equals(name)) {
				return n.id;
			}
		}
		System.err.println("Variable " + name + " not found.");
		return -1;
	}
	
	private int getClass(String name) {
		for (Name n : classes) {
			if (n.name.equals(name)) {
				return n.id;
			}
		}
		System.err.println("Class " + name + " not found.");
		return -1;
	}

	public static LinkedList<StorageElement> getStorage() {
		return storage;
	}
}
