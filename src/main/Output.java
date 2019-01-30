package main;

public class Output {
	
	private String output = "";
	
	private String extraInfo;
	
	public Output(String[] binCode) {
		int size = 0;
		for (; size < binCode.length; size++) {
			if (binCode[size] == null) {
				break;
			}
		}
		String[] binarys = new String[size];
		for (int i = 0; i < size; i++) {
			binarys[i] = binCode[i];
		}
		for (int i = 0; i < binarys.length; i++) {
			for (int p = 0; p < binarys[i].length(); p++) {
				if (binarys[i].charAt(p) != '0' && binarys[i].charAt(p) != '1' && binarys[i].charAt(p) != ' ') {
					System.err.println(BEPL.getFatalErrorMessage());
					System.err.println("This charactercan't be converted to hex: " + binarys[i].charAt(p));
					System.exit(0);
				}
			}
		}
		int sum;
		output += "00";
		for (int i = 0; i < binarys.length; i++) {
			sum = 0;
			output += " ";
			if (binarys[i].length() == 8) {
				for (int x = 0; x < binarys[i].length(); x++) {
					if (binarys[i].charAt(x) == '1') {
						switch (x) {
						case 0:
						case 4:
							sum += 8;
							break;
						case 1:
						case 5:
							sum += 4;
							break;
						case 2:
						case 6:
							sum += 2;
							break;
						case 3:
						case 7:
							sum += 1;
							break;
						}
					}
					if (x == 3 || x == 7) {
						printSum(sum);
						sum = 0;
					}
				}
			} else {
				System.err.println(BEPL.getFatalErrorMessage());
				System.exit(0);
			}
		}
		output = makeSections(output, 16);
		extraInfo = "\nFinished. Your program has " + binarys.length + " lines of code. ("
				+ Math.round(binarys.length * 100 / BEPL.MAXIMUM_MEMORY) + "% of total memory used)";
	}
	
	public String getString() {
		return output;
	}
	
	public String getExtraInfo() {
		return extraInfo;
	}
	
	private void printSum(int sum) {
		if (sum < 10) {
			output += sum;
		} else {
			switch (sum) {
			case 10:
				output += "A";
				break;
			case 11:
				output += "B";
				break;
			case 12:
				output += "C";
				break;
			case 13:
				output += "D";
				break;
			case 14:
				output += "E";
				break;
			case 15:
				output += "F";
				break;
			}
		}
	}
	
	private String makeSections(String rawString, int sectionLength) {
		String[] temp = rawString.split(" ");
		String returnString = "";
		for (int i = 0; i < temp.length; i++) {
			returnString += " ";
			if (i % sectionLength == sectionLength - 1) {
				temp[i] += "\n";
			}
			returnString += temp[i];
			
		}
		return returnString;
	}
}
