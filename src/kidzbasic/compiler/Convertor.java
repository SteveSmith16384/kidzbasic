package kidzbasic.compiler;

import java.io.FileNotFoundException;
import java.io.IOException;

import ssmith.io.TextFile;

/**
 * This converts
 *
 */
public class Convertor {
	
	public Convertor() {
		
	}
	
	
	public String convert(String basic, String to_filename) throws FileNotFoundException, IOException {
		StringBuffer java = new StringBuffer();
		// Boilerplate start
		java.append("public class basic { \n\npublic basic() { \n\npublic static void main(String args[]) { \n");

		//basic.replaceAll("PRINT", "System.out.println(");
		Tokenizer tk = new Tokenizer(basic);
		while (tk.hasMoreTokens()) {
			String token = tk.getNextToken();
			/*if (token.startsWith("\"")) {
				java.append(token); // Raw String
			} else */if (token.equalsIgnoreCase("PRINT")) {
				java.append("System.out.println(");
			//} else if (token.equalsIgnoreCase("INPUT")) {
			} else {
				java.append(token);
			}
		}
		
		
		// boilerplate end
		java.append("\n } \n }");
		
		if (to_filename != null) {
			TextFile.QuickWrite(to_filename, java.toString(), true);
		}
		
		return java.toString();
	}
	
	
	public static void main(String args[]) {
		Convertor c = new Convertor();
		try {
			String s = c.convert("Print \"Hello World!\" ", null);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
