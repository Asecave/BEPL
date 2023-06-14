package com.asecave.expression.commands;

public class CommandJump extends Command {

	public int reg;
	
	public CommandJump(int line, int reg) {
		super(line);
		this.reg = reg;
	}
}
