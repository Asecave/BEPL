package com.asecave.expression;

import java.util.Comparator;
import java.util.LinkedList;

import com.asecave.expression.commands.Command;
import com.asecave.expression.commands.CommandClass;
import com.asecave.expression.commands.CommandJump;

public class CodeGenerator {

	public void generate(LinkedList<Command> commands) {
		
		commands.sort(new Comparator<Command>() {
			@Override
			public int compare(Command c1, Command c2) {
				return Integer.compare(c1.getLine(), c2.getLine());
			}
		});
		
		for (Command c : commands) {
			if (c instanceof CommandJump) {
				CommandJump jump = (CommandJump) c;
				for (int i = 0; i < commands.size(); i++) {
					if (commands.get(i).getLine() == jump.location) {
						jump.location = i;
						break;
					}
				}
			}
		}
		for (int i = 0; i < commands.size(); i++) {
			commands.get(i).setLine(i);
		}
		
		for (Command c : commands) {
			if (c instanceof CommandClass) {
//				System.out.println(((CommandClass) c).call);
			}
			if (c instanceof CommandJump) {
//				System.out.println(((CommandGoto) c).location);
			}
		}
	}

}
