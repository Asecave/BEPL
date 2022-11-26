package com.asecave;

import java.util.LinkedList;

public class LexicalAnalyser {

	public LinkedList<Token> analyse(char[] program) {

			LinkedList<Token> tokens = new LinkedList<>();

			String snippet = "";

			for (char c : program) {

				if (";{}".indexOf(c) != -1) {
					int type = getType(snippet);
					if (type != -1) {
						tokens.add(new Token(type, snippet));
					}
					snippet = "";
					switch (c) {
					case ';':
						tokens.add(new Token(Token.SEMICOLON, "" + c));
						break;
					case '{':
						tokens.add(new Token(Token.BRACE_OPEN, "" + c));
						break;
					case '}':
						tokens.add(new Token(Token.BRACE_CLOSE, "" + c));
						break;
					}
				} else {
					snippet += c;
				}
			}

			return tokens;
		}

		private int getType(String snippet) {
			int type = -1;
			if (snippet.length() == 0) {
				return -1;
			}
			if ("ianc".indexOf(snippet.charAt(0)) != -1 && snippet.length() == 9) {
				type = Token.BINARY;
				for (int i = 1; i < snippet.length(); i++) {
					if (snippet.charAt(i) != '0' && snippet.charAt(i) != '1') {
						type = -1;
					}
				}
			}
			if (type == -1) {
				type = Token.NAME;
			}
			return type;
		}
}
