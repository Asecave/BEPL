package com.asecave.expression;

public class Token {

	public static final int CLASS_DECLARATION = 0;
	public static final int CLASS_CALL = 1;
	public static final int VAR = 2;
	public static final int GOTO = 3;
	public static final int LOCATION = 4;
	public static final int IF = 5;
	public static final int IN = 6;
	public static final int OUT = 7;
	public static final int HALT = 8;
	public static final int EXPRESSION = 9;
	public static final int ENDIF = 10;
	
	private int type;
	private String lexeme;
	private int line;
	
	public Token(int type, String lexeme, int line) {
		this.type = type;
		this.lexeme = lexeme;
		this.line = line;
	}
	
	public int getType() {
		return type;
	}
	
	public String getLexeme() {
		return lexeme;
	}
	
	public int getLine() {
		return line;
	}
}
