package com.asecave;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProgramLoader {

	private Scanner scanner;
	
	public String load(String path) {
		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		StringBuilder builder = new StringBuilder();
		
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			line = line.replaceAll(" ", "");
			line = line.replaceAll("\n", "");
			line = line.replaceAll("\t", "");
			builder.append(line);
		}
		
		return builder.toString();
	}
}
