package com.asecave.syntax;

import java.util.LinkedList;

import com.asecave.Token;

public class Command {
	
	public static final int BINARY_LOAD = 0;
	public static final int BINARY_STORE = 1;
	public static final int BINARY_ADD = 2;
	public static final int BINARY_SUB = 3;
	public static final int BINARY_INC = 4;
	public static final int BINARY_DEC = 5;
	public static final int BINARY_COMPARE = 6;
	public static final int BINARY_RESULT = 7;
	public static final int BINARY_IN = 8;
	public static final int BINARY_OUT = 9;
	public static final int BINARY_JUMP = 10;
	public static final int BINARY_IF = 11;
	public static final int BINARY_CLASS = 12;
	public static final int BINARY_HALT = 13;
	public static final int BINARY_ADDRESS = 13;
	public static final int BINARY_NUMBER = 13;
	public static final int BINARY_CHARACTER = 13;

	private int type = -1;
	LinkedList<Token> tokens = new LinkedList<>();
	
	public void addToken(Token token) {
		tokens.add(token);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Token t : tokens) {
			s += t;
		}
		return s;
	}
	
	public void validate() {
		for (Token t : tokens) {
			if (t.getType() == Token.BINARY) {
				if (t.getSnippet().startsWith("i")) {
					switch (t.getSnippet().substring(1, 5)) {
					case "0000":
						type = BINARY_LOAD;
						break;
					case "0001":
						type = BINARY_STORE;
						break;
					case "0010":
						type = BINARY_ADD;
						break;
					case "0011":
						type = BINARY_SUB;
						break;
					case "0100":
						type = BINARY_INC;
						break;
					case "0101":
						type = BINARY_DEC;
						break;
					case "0110":
						type = BINARY_COMPARE;
						break;
					case "0111":
						type = BINARY_RESULT;
						break;
					case "1000":
						type = BINARY_IN;
						break;
					case "1001":
						type = BINARY_OUT;
						break;
					case "1010":
						type = BINARY_JUMP;
						break;
					case "1011":
						type = BINARY_IF;
						break;
					case "1100":
						type = BINARY_CLASS;
						break;
					case "1101":
						type = BINARY_HALT;
						break;
					}
				} else if (t.getSnippet().startsWith("a")) {
					type = BINARY_ADDRESS;
				} else if (t.getSnippet().startsWith("n")) {
					type = BINARY_NUMBER;
				} else if (t.getSnippet().startsWith("c")) {
					type = BINARY_CHARACTER;
				}
			}
		}
	}
	
	public int getType() {
		return type;
	}
	
	public String getRawBinary() {
		return tokens.getFirst().getSnippet().substring(1, 9);
	}
}
