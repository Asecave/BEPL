package com.asecave;

import java.util.LinkedList;

import com.asecave.syntax.BEPLClass;
import com.asecave.syntax.Command;

public class Parser {

	public LinkedList<BEPLClass> parse(LinkedList<Token> tokens) {

		LinkedList<BEPLClass> classes = new LinkedList<>();

		BEPLClass currentClass = null;
		Command currentCommand = null;

		for (Token t : tokens) {
			if (currentClass != null) {
				if (currentClass.hasOpeningBrace()) {
					if (currentCommand == null) {
						if (t.getType() == Token.BRACE_CLOSE) {
							currentClass.setClosingBrace(t);
							classes.add(currentClass);
							currentClass = null;
							continue;
						}
						currentCommand = new Command();
					}
					if (t.getType() == Token.SEMICOLON) {
						currentCommand.validate();
						currentClass.addCommand(currentCommand);
						currentCommand = null;
					} else if (t.getType() == Token.BRACE_CLOSE) {
						System.err.println("Unexpected token: '" + t.getSnippet() + "'");
					} else if (t.getType() == Token.BRACE_OPEN) {
						System.err.println("Unexpected token: '" + t.getSnippet() + "'");
					} else {
						currentCommand.addToken(t);
					}
				} else {
					if (t.getType() == Token.BRACE_OPEN) {
						currentClass.setOpeningBrace(t);
					} else {
						System.err.println("Unexpected token: '" + t.getSnippet() + "' Expected '{'.");
					}
				}
			} else {
				if (t.getType() == Token.NAME) {
					currentClass = new BEPLClass(t);
				} else {
					System.err.println("Unexpected token: '" + t.getSnippet() + "' Expected NAME.");
				}
			}
		}

		int nextType = -1;
		for (BEPLClass bc : classes) {
			for (Command c : bc.getCommands()) {
				if (nextType != -1 && c.getType() != nextType) {
					System.err.println("Expected type " + nextType + " but got " + c.getType());
				} else {
					nextType = -1;
					switch(c.getType()) {
					case Command.BINARY_LOAD:
						nextType = Command.BINARY_ADDRESS;
						break;
					case Command.BINARY_STORE:
						nextType = Command.BINARY_ADDRESS;
						break;
					case Command.BINARY_ADD:
						nextType = Command.BINARY_RESULT;
						break;
					case Command.BINARY_SUB:
						nextType = Command.BINARY_SUB;
						break;
					case Command.BINARY_COMPARE:
						nextType = Command.BINARY_IF;
						break;
					}
				}
			}
			if (nextType != -1) {
				System.err.println("Unexpeced end of class.");
			}
		}

		return classes;
	}

}
