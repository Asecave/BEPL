package com.asecave.expression;

import java.util.LinkedList;

public class LexicalAnalyser {

	public LinkedList<Token> analyse(String program) {
		
		LinkedList<Token> tokens = new LinkedList<>();
		
		String[] lines = program.split("\n");
		int ignoredLines = 0;
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			if (line.startsWith("[")) {
				tokens.add(new Token(Token.CLASS_DECLARATION, line.substring(1), i - ignoredLines++));
			} else if (line.startsWith("class")) {
				tokens.add(new Token(Token.CLASS_CALL, line.substring(6), i - ignoredLines));
			} else if (line.startsWith("var")) {
				tokens.add(new Token(Token.VAR, line.substring(4), i - ignoredLines++));
			} else if (line.startsWith("goto")) {
				tokens.add(new Token(Token.GOTO, line.substring(5), i - ignoredLines));
			} else if (line.startsWith(":")) {
				tokens.add(new Token(Token.LOCATION, line.substring(1), i - ignoredLines++));
			} else if (line.startsWith("if")) {
				tokens.add(new Token(Token.IF, line.substring(3), i - ignoredLines));
			} else if (line.startsWith("in")) {
				tokens.add(new Token(Token.IN, line.substring(3), i - ignoredLines));
			} else if (line.startsWith("out")) {
				tokens.add(new Token(Token.OUT, line.substring(4), i - ignoredLines));
			} else if (line.startsWith("halt")) {
				tokens.add(new Token(Token.HALT, "", i - ignoredLines));
			} else {
				tokens.add(new Token(Token.EXPRESSION, line, i - ignoredLines));
			}
		}
		
		return tokens;
	}
}
