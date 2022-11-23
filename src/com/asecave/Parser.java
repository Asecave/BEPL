package com.asecave;

import java.util.LinkedList;

public class Parser {

	public Parser() {
	}
	
	public LinkedList<String> parse(LinkedList<String> lines) {
		LinkedList<String> words = new LinkedList<>();
		for (String line : lines) {
			words.add(line);
		}
		return words;
	}
}
