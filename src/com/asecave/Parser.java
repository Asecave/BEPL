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

		return classes;
	}

}
