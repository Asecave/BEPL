package com.asecave.expression.commands;

public class CommandJump extends Command {

	public int location;
	
	public CommandJump(int location, int line) {
		super(line);
		this.location = location;
	}
}
