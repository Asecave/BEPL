package com.asecave.expression.commands;

public class CommandSub extends Command {

	public int var1, var2;
	
	public CommandSub(int line, int reg1, int reg2) {
		super(line);
		this.var1 = reg1;
		this.var2 = reg2;
	}

}
