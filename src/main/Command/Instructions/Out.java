package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Out extends Instruction implements Converter {

	public Out(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		if (command[1].charAt(0) == '\'') {
			binCode[binCodeLine] = "01010000";
			binCodeLine++;
			binCode[binCodeLine] = intToBinary((int) command[1].charAt(1));
		} else {
			binCode[binCodeLine] = "010101";
			binCode[binCodeLine] += binRegister(command[1]);
		}
		this.binCodeLine = binCodeLine;
		return binCode;
	}
}
