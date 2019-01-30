package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Jump extends Instruction implements Converter {

	public Jump(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		binCode[binCodeLine] = "01000000";
		binCodeLine++;
		binCode[binCodeLine] = command[1];
		this.binCodeLine = binCodeLine;
		return binCode;
	}
}
