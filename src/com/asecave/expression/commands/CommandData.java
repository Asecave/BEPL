package com.asecave.expression.commands;

public class CommandData extends Command {

	public int data;
	
	public CommandData(int line, int data) {
		super(line);
		this.data = data;
	}
}
