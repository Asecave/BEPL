package com.asecave;

import java.util.LinkedList;

public class BEPL {

	ProgramLoader programLoader;
	LexicalAnalyser lexicalAnalyser;
	Parser parser;

	public BEPL() {
		programLoader = new ProgramLoader();
		parser = new Parser();

		String program = programLoader.load("C:\\Users\\Asecave\\Desktop\\Program.txt");

		LinkedList<Token> tokens = parser.parse(program.toCharArray());
		for (Token t : tokens) {
			System.out.println(t.getType() + " : " + t.getSnippet());
		}
	}
}
