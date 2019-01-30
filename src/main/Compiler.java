package main;

import java.util.ArrayList;

import main.Command.Instruction;
import main.Command.Instructions.*;

public class Compiler {

	String[] binCode = new String[BEPL.MAXIMUM_MEMORY * 3 + 1];

	public static int line = 0;

	public Compiler(String[] lines) throws Exception {
		int binCodeLine = 0;

		String[][] commands = new String[lines.length][];

		for (int i = 0; i < lines.length; i++) {
			if (lines[i] == null)
				break;
			lines[i] = lines[i].toLowerCase();
			lines[i] = lines[i].replaceAll("	", "");
			lines[i] = lines[i].replaceAll(" ", "");
			lines[i] = lines[i].replaceAll("<", ",<,");
			lines[i] = lines[i].replaceAll("=", ",=,");
			lines[i] = lines[i].replaceAll(">", ",>,");
			lines[i] = lines[i].replaceAll(":", ":,");
			lines[i] = lines[i].replaceAll("//", "//,");
			commands[i] = lines[i].split("\\(|\\,|\\;|\\)");
		}

		class Marker {
			int position;
			int codeLine;
			String name;

			Marker(int position, String name, int codeLine) {
				this.position = position;
				this.name = name;
				this.codeLine = codeLine;
			}

			public String getPositionString() {
				return Instruction.intToBinary(position);
			}
		}

		ArrayList<Marker> markers = new ArrayList<Marker>();

		Load load = new Load("load");
		Store store = new Store("store");
		Jumpif jumpif = new Jumpif("jumpif");
		Jump jump = new Jump("jump");
		Out out = new Out("out");
		In in = new In("in");
		Add add = new Add("add");
		Subtract subtract = new Subtract("subtract");
		Increase increase = new Increase("increase");
		Decrease decrease = new Decrease("decrease");
		Halt halt = new Halt("halt");

		while (commands[line + 1] != null) {
			if (commands[line][0].equals(load.keyword)) {
				binCode = load.convert(commands[line], binCode, binCodeLine);
				binCodeLine = load.getBinCodeLine();
			} else if (commands[line][0].equals(store.keyword)) {
				binCode = store.convert(commands[line], binCode, binCodeLine);
				binCodeLine = store.getBinCodeLine();
			} else if (commands[line][0].equals(jumpif.keyword)) {
				binCode = jumpif.convert(commands[line], binCode, binCodeLine);
				binCodeLine = jumpif.getBinCodeLine();
			} else if (commands[line][0].equals(jump.keyword)) {
				binCode = jump.convert(commands[line], binCode, binCodeLine);
				binCodeLine = jump.getBinCodeLine();
			} else if (commands[line][0].equals(out.keyword)) {
				binCode = out.convert(commands[line], binCode, binCodeLine);
				binCodeLine = out.getBinCodeLine();
			} else if (commands[line][0].equals(in.keyword)) {
				binCode = in.convert(commands[line], binCode, binCodeLine);
				binCodeLine = in.getBinCodeLine();
			} else if (commands[line][0].equals(add.keyword)) {
				binCode = add.convert(commands[line], binCode, binCodeLine);
				binCodeLine = add.getBinCodeLine();
			} else if (commands[line][0].equals(subtract.keyword)) {
				binCode = subtract.convert(commands[line], binCode, binCodeLine);
				binCodeLine = subtract.getBinCodeLine();
			} else if (commands[line][0].equals(increase.keyword)) {
				binCode = increase.convert(commands[line], binCode, binCodeLine);
				binCodeLine = increase.getBinCodeLine();
			} else if (commands[line][0].equals(decrease.keyword)) {
				binCode = decrease.convert(commands[line], binCode, binCodeLine);
				binCodeLine = decrease.getBinCodeLine();
			} else if (commands[line][0].equals(halt.keyword)) {
				binCode = halt.convert(commands[line], binCode, binCodeLine);
				binCodeLine = halt.getBinCodeLine();
			}
			
			else if (commands[line][0].equals(":")) {
				markers.add(new Marker(binCodeLine, commands[line][1], line));
				binCodeLine--;
			} else if (commands[line][0].equals("")) {
				binCodeLine--;
			} else if (commands[line][0].equals("//")) {
				binCodeLine--;
			} 
			
			else {
				System.err.println("Error: Unacceptable instruction: " + commands[line][0] + "\nLine: " + line);
				System.exit(0);
			}
			binCodeLine++;
			line++;
		}

		line = 0;

		while (binCode[line] != null) {
			compare: if (binCode[line].charAt(0) != '0' && binCode[line].charAt(0) != '1') {
				for (Marker marker : markers) {
					if (marker.name.equals(binCode[line])) {
						binCode[line] = marker.getPositionString();
						break compare;
					}
				}
				for (Marker marker : markers) {
					if (marker.name.equals(binCode[line])) {
						System.err.println(
								"Error: Marker " + binCode[line] + " does not exist.\nLine: " + marker.codeLine);
					} else {
						System.err.println(BEPL.getFatalErrorMessage());
					}
				}
			}
			line++;
		}

		binCode[binCodeLine] = "01110000";
	}

	public String[] getbinCode() {
		return binCode;
	}

}
