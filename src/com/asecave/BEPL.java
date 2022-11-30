package com.asecave;

import java.util.LinkedList;

import com.asecave.syntax.BEPLClass;

public class BEPL {

	ProgramLoader programLoader;
	LexicalAnalyser lexicalAnalyser;
	Parser parser;
	CodeGenerator codeGenerator;

	public BEPL() {
		programLoader = new ProgramLoader();
		lexicalAnalyser = new LexicalAnalyser();
		parser = new Parser();
		codeGenerator = new CodeGenerator();

		String program = programLoader.load("C:\\Users\\Asecave\\Desktop\\Program.txt");

		LinkedList<Token> tokens = lexicalAnalyser.analyse(program.toCharArray());
		for (Token t : tokens) {
			System.out.println(t.getType() + " : " + t.getSnippet());
		}
		LinkedList<BEPLClass> classes = parser.parse(tokens);
		for (BEPLClass bc : classes) {
			System.out.println(bc);
		}
		System.out.println("\n");
		System.out.println(codeGenerator.generate(classes));
	}
}
