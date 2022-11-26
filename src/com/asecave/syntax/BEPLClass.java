package com.asecave.syntax;

import java.util.LinkedList;

import com.asecave.Token;

public class BEPLClass {
	
	private Token name;
	private LinkedList<Command> commands = new LinkedList<>();
	private Token openingBace = null;
	private Token closingBace = null;
	
	public BEPLClass(Token name) {
		this.name = name;
	}

	public void addCommand(Command command) {
		commands.add(command);
	}
	
	public boolean hasOpeningBrace() {
		return openingBace != null;
	}
	
	public boolean hasClosingBrace() {
		return closingBace != null;
	}
	
	public void setOpeningBrace(Token brace) {
		openingBace = brace;
	}
	
	public void setClosingBrace(Token brace) {
		closingBace = brace;
	}
	
	@Override
	public String toString() {
		String s = name + "{\n";
		for (Command c : commands) {
			s += c + "\n";
		}
		s += "}";
		return s;
	}
}
