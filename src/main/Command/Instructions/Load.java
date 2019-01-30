package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Load extends Instruction implements Converter {

	public Load(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		if (command[1].charAt(0) == '\'') {
			binCode[binCodeLine] = "000101";
			binCode[binCodeLine] += binRegister(command[2]);
			binCodeLine++;
			binCode[binCodeLine] = intToBinary((int) command[1].charAt(1));
		} else {
			binCode[binCodeLine] = "000100";
			binCode[binCodeLine] += binRegister(command[2]);
			binCodeLine++;
			binCode[binCodeLine] = intToBinary(Integer.parseInt(command[1]));
		}
		this.binCodeLine = binCodeLine;
		return binCode;
	}
}
