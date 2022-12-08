package com.asecave.expression.commands;

public class CommandClass extends Command {

	public int call;
	
	public CommandClass(int call, int line) {
		super(line);
		this.call = call;
	}

}
