package com.asecave.expression.commands;

public class CommandClass extends Command {

	public int reg;
	
	public CommandClass(int line, int reg) {
		super(line);
		this.reg = reg;
	}

}
