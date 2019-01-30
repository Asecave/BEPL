package main;

//Binary Editor for Processor Language = BEPL

public class BEPL {

	public static final int MAXIMUM_MEMORY = 255;

	String[] lines = new String[MAXIMUM_MEMORY + 1];
	String[] binCode = new String[MAXIMUM_MEMORY * 3 + 1];

	public BEPL() {
		System.out.println("\n[BEPL: v.1.0 ; Relesed: 30.1.2019 ; Devloper: Asecave]");
		System.out.println("\nThis is BEPL. BEPL converts your program to hex code.\n");
		System.out.println("program{");
		lines = new Writer().getLines();
		System.out.println("Compiling...");
		try {
			binCode = new Compiler(lines).getbinCode();
		} catch (Exception e) {
			System.err.println("Fatal error while compiling!\nMake sure you've used every instruction correctly (parameters).");
			System.exit(0);
		}
		System.out.println("Compiling successful.");
		Output output = new Output(binCode);
		System.out.println(output.getString());
		System.out.println(output.getExtraInfo());
	}
	
	public static String getFatalErrorMessage() {
	    return ("Fatal error! BEPL Line: " + new Integer(Thread.currentThread().getStackTrace()[2].getLineNumber()).toString() + 
	    		"\nAt: " + Thread.currentThread().getStackTrace()[2].getClassName());
	}
	
	public static void main(String[] args) {
		new BEPL();
	}
}
