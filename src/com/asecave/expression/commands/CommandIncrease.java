package com.asecave.expression.commands;

public class CommandIncrease extends Command {

	public int reg;
	
	public CommandIncrease(int line, int reg) {
		super(line);
		this.reg = reg;
	}

}
