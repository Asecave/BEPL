package com.asecave;

import java.util.LinkedList;

public class BEPL {

	ProgramLoader programLoader;
	Parser parser;

	public BEPL() {
		programLoader = new ProgramLoader();
		parser = new Parser();

		LinkedList<String> lines = programLoader.load("C:\\Users\\Asecave\\Desktop\\Program.txt");

		LinkedList<String> words = parser.parse(lines);
		for (String s : words) {
			System.out.println(s);
		}
	}
}
