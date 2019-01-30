package main.Command;

import main.Compiler;

public class Instruction {

	public String keyword;

	protected int binCodeLine;

	public Instruction(String keyword) {
		this.keyword = keyword;
	}

	public int getBinCodeLine() {
		return binCodeLine;
	}

	public String getKeyword() {
		return keyword;
	}

	public String binRegister(String letter) {
		switch (letter) {
		case "a":
			return "00";
		case "b":
			return "01";
		case "c":
			return "10";
		case "d":
			return "11";
		}
		System.err.println("Unacceptable parameter: " + letter + "\nOnly use A/B/C/D\nLine: " + Compiler.line);
		System.exit(0);
		return null;
	}

	public static String intToBinary(int num) {
		String s = "";
		if (num >= 128) {
			s += "1";
			num -= 128;
		} else {
			s += "0";
		}
		if (num >= 64) {
			s += "1";
			num -= 64;
		} else {
			s += "0";
		}
		if (num >= 32) {
			s += "1";
			num -= 32;
		} else {
			s += "0";
		}
		if (num >= 16) {
			s += "1";
			num -= 16;
		} else {
			s += "0";
		}
		if (num >= 8) {
			s += "1";
			num -= 8;
		} else {
			s += "0";
		}
		if (num >= 4) {
			s += "1";
			num -= 4;
		} else {
			s += "0";
		}
		if (num >= 2) {
			s += "1";
			num -= 2;
		} else {
			s += "0";
		}
		if (num >= 1) {
			s += "1";
			num -= 1;
		} else {
			s += "0";
		}
		return s;
	}

	public String writeJumpLine(int line){
		return intToBinary(line);
	}
}
