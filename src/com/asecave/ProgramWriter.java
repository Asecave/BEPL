package com.asecave;

import java.io.FileWriter;
import java.io.IOException;

public class ProgramWriter {

	public ProgramWriter() {
	}
	
	public void write(int[] code, String path) {
		String str = "v2.0 raw";
		for (int c : code) {
			str += "\n" + Integer.toHexString(c);
		}
		
		try (FileWriter writer = new FileWriter(path)) {
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
