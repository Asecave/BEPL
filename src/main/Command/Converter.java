package main.Command;

public interface Converter {
	
	public String[] convert(String[] command, String[] binCode, int binCodeLine);
	
	public String getKeyword();
}
