package com.asecave.expression.commands;

public class CommandStore extends Command {

	public int var, register;
	
	public CommandStore(int line, int var, int register) {
		super(line);
		this.var = var;
		this.register = register;
	}

}
