package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Store extends Instruction implements Converter {

	public Store(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		binCode[binCodeLine] = "001000";
		binCode[binCodeLine] += binRegister(command[1]);
		binCodeLine++;
		binCode[binCodeLine] = intToBinary(Integer.parseInt(command[2]));
		this.binCodeLine = binCodeLine;
		return binCode;
	}
}
