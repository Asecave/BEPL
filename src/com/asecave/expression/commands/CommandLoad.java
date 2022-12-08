package com.asecave.expression.commands;

public class CommandLoad extends Command {

	public int var, register;
	
	public CommandLoad(int line, int var, int register) {
		super(line);
		this.var = var;
		this.register = register;
	}

}
