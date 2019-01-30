package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Halt extends Instruction implements Converter {

	public Halt(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		binCode[binCodeLine] = "01110000";
		this.binCodeLine = binCodeLine;
		return binCode;
	}

}
