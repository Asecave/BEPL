package com.asecave.expression.commands;

public class CommandIn extends Command {

	public int reg;
	
	public CommandIn(int line, int reg) {
		super(line);
		this.reg = reg;
	}

}
