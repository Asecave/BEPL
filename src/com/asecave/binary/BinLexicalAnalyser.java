package com.asecave.binary;

import java.util.LinkedList;

public class BinLexicalAnalyser {
	
	public static final String[] WORDS = {
		"load",
		"store",
		"add",
		"sub",
		"inc",
		"dec",
		"in",
		"out",
		"jump",
		"if",
		"class",
		"halt"
	};

	public LinkedList<BinToken> analyse(char[] program) {

			LinkedList<BinToken> tokens = new LinkedList<>();

			String snippet = "";

			for (char c : program) {

				if (c == ';') {
					snippet = snippet.toLowerCase();
					int type = getType(snippet);
					if (type != -1) {
						tokens.add(new BinToken(type, snippet));
					}
					snippet = "";
					tokens.add(new BinToken(BinToken.SEMICOLON, "" + c));
				} else if (c != '\n') {
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
				type = BinToken.BINARY;
				for (int i = 1; i < snippet.length(); i++) {
					if (snippet.charAt(i) != '0' && snippet.charAt(i) != '1') {
						type = -1;
					}
				}
			}
			if (type == -1) {
				String wordInWords = null;
				for (String word : WORDS) {
					if (snippet.startsWith(word)) {
						wordInWords = word;
						break;
					}
				}
				if (wordInWords != null) {
					if (snippet.indexOf('(') < snippet.indexOf(')') || wordInWords.length() == snippet.length()) {
						type = BinToken.NAME;
					} else {
						System.err.println("')' not found at: " + snippet);
					}
				} else {
					System.err.println("Unknown command: " + snippet);
				}
			}
			return type;
		}
}
