package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Increase extends Instruction implements Converter {

	public Increase(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		binCode[binCodeLine] = "101100";
		binCode[binCodeLine] += binRegister(command[1]);
		this.binCodeLine = binCodeLine;
		return binCode;
	}
	
}
