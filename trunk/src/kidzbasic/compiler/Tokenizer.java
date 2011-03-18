package kidzbasic.compiler;

import ssmith.lang.Functions;

public class Tokenizer { // todo - delete this

	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

	private String data;
	private int pos;

	public Tokenizer(String _data) {
		data =_data;
		pos = 0;
	}


	public boolean hasMoreTokens() {
		return pos < data.length();
	}

	public String getNextToken() {
		StringBuffer this_token = new StringBuffer();
		String chr = this.getNextChar();
		if (chr.equalsIgnoreCase("\"")) {
			return getRestOfStringLiteral(chr);
		} else if (chr.equalsIgnoreCase("\n")) {
			return chr;
		} else if (Functions.IsNumeric(chr)) { // Get numbers
			this_token.append(chr);
			while (pos < data.length()) {
				chr = this.getNextChar();
				if (Functions.IsNumeric(chr) == false) {
					break;
				}
				this_token.append(chr);
			}
			return this_token.toString();
		} else { // Get text
			this_token.append(chr);
			while (pos < data.length()) {
				chr = this.getNextChar();
				if (ALPHA.indexOf(chr.toLowerCase()) < 0) {
					break;
				}
				this_token.append(chr);
			}
			return this_token.toString();
		}
	}


	private String getRestOfStringLiteral(String chr) {
		StringBuffer this_token = new StringBuffer();
		this_token.append(chr);
		while (pos < data.length()) {
			chr = this.getNextChar();
			this_token.append(chr);
			if (chr.equalsIgnoreCase("\"")) {
				return this_token.toString();
			}
		}
		throw new RuntimeException("Not found string end delimiter in " + this_token.toString());
	}


	private String getNextChar() {
		String chr = data.substring(pos, pos+1);
		pos++;
		return chr;
	}

}
