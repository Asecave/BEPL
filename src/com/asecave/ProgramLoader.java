package com.asecave;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ProgramLoader {

	private Scanner scanner;
	
	public LinkedList<String> load(String path) {
		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		LinkedList<String> lines = new LinkedList<>();
		
		while (scanner.hasNext()) {
			lines.add(scanner.nextLine());
		}
		
		return lines;
	}
}
