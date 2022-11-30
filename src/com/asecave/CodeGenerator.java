package com.asecave;

import java.util.LinkedList;

import com.asecave.syntax.BEPLClass;
import com.asecave.syntax.Command;

public class CodeGenerator {

	public String generate(LinkedList<BEPLClass> classes) {
		
		StringBuilder sb = new StringBuilder();
		
		for (BEPLClass bc : classes) {
			for (Command c : bc.getCommands()) {
				sb.append(Integer.toHexString(Integer.parseInt(c.getRawBinary(), 2)) + "\n");
			}
		}
		
		return sb.toString();
	}

}
