package com.asecave;

import java.util.LinkedList;

import com.asecave.binary.BinLexicalAnalyser;
import com.asecave.binary.BinParser;
import com.asecave.binary.BinToken;
import com.asecave.expression.CodeGenerator;
import com.asecave.expression.LexicalAnalyser;
import com.asecave.expression.Parser;
import com.asecave.expression.Token;
import com.asecave.expression.commands.Command;

public class BEPL {

	ProgramLoader programLoader;
	BinLexicalAnalyser binLexicalAnalyser;
	BinParser binParser;
	LexicalAnalyser lexicalAnalyser;
	Parser parser;
	CodeGenerator codeGenerator;

	public BEPL(boolean binaryMode) {
		
			programLoader = new ProgramLoader();
			String program = programLoader.load("C:\\Users\\Asecave\\Desktop\\Program.txt");
		
		if (binaryMode) {
			binLexicalAnalyser = new BinLexicalAnalyser();
			binParser = new BinParser();

			LinkedList<BinToken> tokens = binLexicalAnalyser.analyse(program.toCharArray());
			for (BinToken t : tokens) {
				System.out.println(t);
			}
			LinkedList<Integer> commands = binParser.parse(tokens);

			System.out.println("\n");
			for (int cmd : commands) {
				System.out.println(Integer.toHexString(cmd));

			}
		} else {

			lexicalAnalyser = new LexicalAnalyser();
			LinkedList<Token> tokens = lexicalAnalyser.analyse(program);
			
			parser = new Parser();
			LinkedList<Command> commands = parser.parse(tokens);
			
			codeGenerator = new CodeGenerator();
			codeGenerator.generate(commands);
		}
	}
}
