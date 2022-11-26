package com.asecave;

public class Token {

	public static final int NAME = 0;
	public static final int BRACE_OPEN = 1;
	public static final int BRACE_CLOSE = 2;
	public static final int SEMICOLON = 3;
	public static final int BINARY = 4;
	
	private int type;
	private String snippet;
	
	public Token(int type, String snippet) {
		this.type = type;
		this.snippet = snippet;
	}
	
	public int getType() {
		return type;
	}
	
	public String getSnippet() {
		return snippet;
	}
	
	@Override
	public String toString() {
		return snippet;
	}
}
