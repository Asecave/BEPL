package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Decrease extends Instruction implements Converter {

	public Decrease(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		binCode[binCodeLine] = "110000";
		binCode[binCodeLine] += binRegister(command[1]);
		this.binCodeLine = binCodeLine;
		return binCode;
	}
}
