package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Subtract extends Instruction implements Converter {
	
	public Subtract(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		binCode[binCodeLine] = "1001";
		binCode[binCodeLine] += binRegister(command[1]);
		binCode[binCodeLine] += binRegister(command[2]);
		binCodeLine++;
		binCode[binCodeLine] = "110100";
		binCode[binCodeLine] += binRegister(command[3]);
		this.binCodeLine = binCodeLine;
		return binCode;
	}

}
