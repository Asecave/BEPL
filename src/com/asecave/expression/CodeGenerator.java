package com.asecave.expression;

import java.util.Comparator;
import java.util.LinkedList;

import com.asecave.expression.Parser.StorageElement;
import com.asecave.expression.commands.Command;
import com.asecave.expression.commands.CommandAdd;
import com.asecave.expression.commands.CommandClass;
import com.asecave.expression.commands.CommandData;
import com.asecave.expression.commands.CommandDecrease;
import com.asecave.expression.commands.CommandHalt;
import com.asecave.expression.commands.CommandIn;
import com.asecave.expression.commands.CommandIncrease;
import com.asecave.expression.commands.CommandJump;
import com.asecave.expression.commands.CommandLoad;
import com.asecave.expression.commands.CommandOut;
import com.asecave.expression.commands.CommandResult;
import com.asecave.expression.commands.CommandStore;
import com.asecave.expression.commands.CommandSub;

public class CodeGenerator {

	public int[] generate(LinkedList<Command> commands) {

		commands.sort(new Comparator<Command>() {
			@Override
			public int compare(Command c1, Command c2) {
				return Integer.compare(c1.getLine(), c2.getLine());
			}
		});
		
		for (int i = 0; i < commands.size(); i++) {
			Command c = commands.get(i);
			if (c instanceof CommandLoad) {
				int var = ((CommandLoad) c).var;
				StorageElement e = getElement(var);
				commands.add(++i, new CommandData(c.getLine(), e.codeLine));
			} else if (c instanceof CommandStore) {
				int var = ((CommandStore) c).var;
				StorageElement e = getElement(var);
				commands.add(++i, new CommandData(c.getLine(), e.codeLine));
			}
		}
		
		for (int i = 0; i < commands.size(); i++) {
			commands.get(i).setLine(i);
		}

		int[] code = new int[256];
		int codeIndex = 0;

		for (Command c : commands) {
			if (c instanceof CommandLoad) {
				CommandLoad load = (CommandLoad) c;
				code[codeIndex++] = (0 << 4) + (load.register << 2);
			} else if (c instanceof CommandStore) {
				CommandStore store = (CommandStore) c;
				code[codeIndex++] = (1 << 4) + (store.register << 2);
			} else if (c instanceof CommandAdd) {
				CommandAdd add = (CommandAdd) c;
				code[codeIndex++] = (2 << 4) + (add.var1 << 2) + add.var2;
			} else if (c instanceof CommandSub) {
				CommandSub sub = (CommandSub) c;
				code[codeIndex++] = (3 << 4) + (sub.var1 << 2) + sub.var2;
			} else if (c instanceof CommandIncrease) {
				CommandIncrease increase = (CommandIncrease) c;
				code[codeIndex++] = (4 << 4) + (increase.reg << 2);
			} else if (c instanceof CommandDecrease) {
				CommandDecrease decrease = (CommandDecrease) c;
				code[codeIndex++] = (5 << 4) + (decrease.reg << 2);
			} else if (c instanceof CommandResult) {
				CommandResult result = (CommandResult) c;
				code[codeIndex++] = (7 << 4) + (result.reg << 2);
			} else if (c instanceof CommandJump) {
				CommandJump jump = (CommandJump) c;
				code[codeIndex++] = (10 << 4) + (jump.reg << 2);
			} else if (c instanceof CommandIn) {
				CommandIn in = (CommandIn) c;
				code[codeIndex++] = (8 << 4) + (in.reg << 2);
			} else if (c instanceof CommandOut) {
				CommandOut out = (CommandOut) c;
				code[codeIndex++] = (9 << 4) + (out.reg << 2);
			} else if (c instanceof CommandClass) {
				CommandClass call = (CommandClass) c;
				code[codeIndex++] = (12 << 4) + (call.reg << 2);
			} else if (c instanceof CommandHalt) {
				code[codeIndex++] = 13 << 4;
			} else if (c instanceof CommandData) {
				code[codeIndex++] = ((CommandData) c).data;
			}
		}
		
		code[codeIndex++] = 13 << 4;
		
		for (StorageElement e : Parser.getStorage()) {
			code[e.codeLine] = e.value;
		}
		
		if (codeIndex > 256 - Parser.getStorage().size()) {
			System.err.println("Class limit reached. Program interfears with variable storage.");
		}
		
		return code;
	}
	
	private StorageElement getElement(int var) {
		StorageElement e = null;
		for (int j = 0; j < Parser.getStorage().size(); j++) {
			e = Parser.getStorage().get(j);
			if (e.id == var) {
				e.codeLine = 255 - j;
				break;
			}
		}
		if (e == null) {
			System.err.println("Variable not found. ID: " + var);
		}
		return e;
	}

}
