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

	public static LinkedList<Name> classes = new LinkedList<>();
	public static LinkedList<Location> locations = new LinkedList<>();
	public static LinkedList<Name> variables = new LinkedList<>();

	private static int classCounter = 0;
	private static int locationCounter = 0;
	private static int varCounter = 0;

	public class Name {

		int id;
		String name;

		public Name(String name, int id) {
			this.name = name;
			this.id = id;
		}
	}

	public class Location {

		int line;
		Name name;

		public Location(int line, Name name) {
			this.line = line;
			this.name = name;
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
				name = new Name(lexeme.substring(0, lexeme.length()), locationCounter++);
				locations.add(new Location(t.getLine(), name));
				break;
			case Token.VAR:
				name = new Name(lexeme.substring(0, lexeme.replaceAll(" ", "").indexOf('=')), varCounter++);
				variables.add(name);
				break;
			}
		}

		for (Token t : tokens) {
			String lexeme = t.getLexeme();

			switch (t.getType()) {
			case Token.CLASS_CALL:
				for (Name n : classes) {
					if (n.name.equals(lexeme)) {
						commands.add(new CommandClass(n.id, t.getLine()));
					}
				}
				break;
			case Token.GOTO:
				for (Location l : locations) {
					if (l.name.name.equals(lexeme)) {
						commands.add(new CommandJump(l.line, t.getLine()));
					}
				}
				break;
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
		for (Name n : variables) {
			if (n.name.equals(name)) {
				return n.id;
			}
		}
		System.err.println("Variable " + name + " not found.");
		return -1;
	}
}
