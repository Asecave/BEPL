package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class In extends Instruction implements Converter {

	public In(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		binCode[binCodeLine] = "011000";
		binCode[binCodeLine] += binRegister(command[1]);
		this.binCodeLine = binCodeLine;
		return binCode;
	}
}
