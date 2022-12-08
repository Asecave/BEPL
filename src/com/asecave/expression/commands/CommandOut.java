package com.asecave.expression.commands;

public class CommandOut extends Command {

	public int reg;
	
	public CommandOut(int line, int reg) {
		super(line);
		this.reg = reg;
	}

}
