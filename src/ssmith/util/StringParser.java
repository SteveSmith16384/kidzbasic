package ssmith.util;

public class StringParser {

	private String str;
	private int pos;

	public StringParser(String s) {
		str = s;
	}

	
	public String runTo(String s) {
		int start = pos;
		while (pos < str.length()) {
			if (str.substring(pos, pos+s.length()).equalsIgnoreCase(s)) {
				break;
			}
			pos++;
		}
		pos += s.length(); // Skip the seperating chars
		return str.substring(start, pos-1).trim();
	}


	public String runToMult(String s) {
		int start = pos;
		all:while (pos < str.length()) {
			for (int i=0 ; i<s.length() ; i++) {
				if (str.substring(pos, pos+1).equalsIgnoreCase(s.substring(i, i+1))) {
					break all;
				}
			}
			pos++;
		}
		if (start+pos < str.length()) {
			pos += 1;//s.length(); // Skip the seperating chars
		}
		String result = str.substring(start, pos).trim(); 
		return result;
	}


	public boolean hasMore() {
		return pos < str.length();
	}
}
