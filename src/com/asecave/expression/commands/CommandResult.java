package com.asecave.expression.commands;

public class CommandResult extends Command {

	public int var;
	
	public CommandResult(int line, int reg) {
		super(line);
		this.var = reg;
	}

}
