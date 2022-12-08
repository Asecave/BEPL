package com.asecave.binary;

public class BinToken {

	public static final int BINARY = 0;
	public static final int NAME = 1;
	public static final int SEMICOLON = 2;
	
	private int type;
	private String snippet;
	
	public BinToken(int type, String snippet) {
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
		String sType = null;
		switch (type) {
		case BINARY:
			sType = "BINARY";
			break;
		case NAME:
			sType = "NAME";
			break;
		case SEMICOLON:
			sType = "SEMICOLON";
			break;
		}
		return sType + ": " + snippet;
	}
}
