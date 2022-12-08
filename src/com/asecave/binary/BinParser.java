package com.asecave.binary;

import java.util.LinkedList;

public class BinParser {

	public LinkedList<Integer> parse(LinkedList<BinToken> tokens) {

		LinkedList<Integer> commands = new LinkedList<>();

		for (int i = 0; i < tokens.size(); i++) {
			BinToken t = tokens.get(i);
			if (t.getType() == BinToken.BINARY) {
				int cmd = Integer.parseInt(t.getSnippet().substring(1, 9), 2);
				commands.add(cmd);
			} else if (t.getType() == BinToken.NAME) {
				for (String word : BinLexicalAnalyser.WORDS) {
					if (t.getSnippet().startsWith(word)) {
						int param1 = 0;
						int param2 = 0;
						int param3 = 0;
						int cmd = 0;
						switch (word) {
						case "load":
							param1 = translateParameter(t.getSnippet().charAt(5));
							cmd = (0 << 4) + (param1 << 2);
							commands.add(cmd);
							break;
						case "store":
							param1 = translateParameter(t.getSnippet().charAt(6));
							cmd = (1 << 4) + (param1 << 2);
							commands.add(cmd);
							break;
						case "add":
							param1 = translateParameter(t.getSnippet().charAt(4));
							param2 = translateParameter(t.getSnippet().charAt(5));
							param3 = translateParameter(t.getSnippet().charAt(6));
							cmd = (2 << 4) + (param1 << 2) + param2;
							commands.add(cmd);
							cmd = (7 << 4) + (param3 << 2);
							commands.add(cmd);
							break;
						case "sub":
							param1 = translateParameter(t.getSnippet().charAt(4));
							param2 = translateParameter(t.getSnippet().charAt(5));
							param3 = translateParameter(t.getSnippet().charAt(6));
							cmd = (3 << 4) + (param1 << 2) + param2;
							commands.add(cmd);
							cmd = (7 << 4) + (param3 << 2);
							commands.add(cmd);
							break;
						case "inc":
							param1 = translateParameter(t.getSnippet().charAt(4));
							cmd = (4 << 4) + (param1 << 2);
							commands.add(cmd);
							break;
						case "dec":
							param1 = translateParameter(t.getSnippet().charAt(4));
							cmd = (5 << 4) + (param1 << 2);
							commands.add(cmd);
							break;
						case "in":
							param1 = translateParameter(t.getSnippet().charAt(3));
							cmd = (8 << 4) + (param1 << 2);
							commands.add(cmd);
							break;
						case "out":
							param1 = translateParameter(t.getSnippet().charAt(4));
							cmd = (9 << 4) + (param1 << 2);
							commands.add(cmd);
							break;
						case "jump":
							param1 = translateParameter(t.getSnippet().charAt(5));
							cmd = (10 << 4) + (param1 << 2);
							commands.add(cmd);
							break;
						case "if":
							param1 = translateParameter(t.getSnippet().charAt(3));
							param2 = translateParameter(t.getSnippet().charAt(4));
							param3 = translateParameter(t.getSnippet().charAt(5));
							cmd = (6 << 4) + (param1 << 2) + param3;
							commands.add(cmd);
							cmd = (11 << 4) + (param2 << 2);
							commands.add(cmd);
							break;
						case "class":
							param1 = translateParameter(t.getSnippet().charAt(6));
							cmd = (12 << 4) + (param1 << 2);
							commands.add(cmd);
							break;
						case "halt":
							cmd = (13 << 4);
							commands.add(cmd);
							break;
						}
					}
				}
			}
		}

		return commands;
	}

	private int translateParameter(char c) {
		switch (c) {
		case 'a':
		case '<':
			return 0;
		case 'b':
		case '=':
			return 1;
		case 'c':
		case '>':
			return 2;
		case 'd':
			return 3;
		}
		return -1;
	}
}
