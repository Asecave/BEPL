package main;

import java.util.Scanner;

public class Writer {
	
	String[] lines = new String[BEPL.MAXIMUM_MEMORY + 1];
	
	public Writer() {
		Scanner s = new Scanner(System.in);
		int lineCounter = 0;
		write: do {
			lines[lineCounter] = s.nextLine();
			if (lineCounter == BEPL.MAXIMUM_MEMORY) {
				System.out.println("Maximum memory has been reached.");
				break write;
			} else {
				lineCounter++;
			}
		} while (!lines[lineCounter - 1].equals("}"));
	}
	
	public String[] getLines() {
		return lines;
	}
}
