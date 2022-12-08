package com.asecave.expression.commands;

public class CommandDecrease extends Command {

	public int reg;
	
	public CommandDecrease(int line, int reg) {
		super(line);
		this.reg = reg;
	}

}
