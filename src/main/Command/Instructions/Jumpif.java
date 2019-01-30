package main.Command.Instructions;

import main.Command.Converter;
import main.Command.Instruction;

public class Jumpif extends Instruction implements Converter {

	public Jumpif(String keyword) {
		super(keyword);
	}

	@Override
	public String[] convert(String[] command, String[] binCode, int binCodeLine) {
		binCode[binCodeLine] = "1010";
		binCode[binCodeLine] += binRegister(command[1]);
		binCode[binCodeLine] += binRegister(command[3]);
		binCodeLine++;
		binCode[binCodeLine] = "001100";
		switch (command[2]) {
		case ">":
			binCode[binCodeLine] += "00";
			break;
		case "=":
			binCode[binCodeLine] += "01";
			break;
		case "<":
			binCode[binCodeLine] += "10";
		}
		binCodeLine++;
		binCode[binCodeLine] = command[4];
		this.binCodeLine = binCodeLine;
		return binCode;
	}
}
