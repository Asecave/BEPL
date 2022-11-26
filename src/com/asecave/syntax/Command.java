package com.asecave.syntax;

import java.util.LinkedList;

import com.asecave.Token;

public class Command {

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
}
