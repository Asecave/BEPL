package com.asecave.expression.commands;

public class CommandResult extends Command {

	public int reg;
	
	public CommandResult(int line, int reg) {
		super(line);
		this.reg = reg;
	}

}
