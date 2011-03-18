package kidzbasic.compiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import ssmith.io.TextFile;
import ssmith.util.StringParser;

/**
 * This converts a .bas file to a java file.
 *
 */
public class Convertor {
	
	private ArrayList<String> imports = new ArrayList<String>();

	public Convertor() {

	}


	public String convert(String basic, String to_filename) throws FileNotFoundException, IOException {
		StringBuffer java = new StringBuffer();
		// Boilerplate start
		java.append("public class basic { \n\npublic static void main(String args[]) { \n");

		//basic.replaceAll("PRINT", "System.out.println(");
		StringParser tk = new StringParser(basic);
		while (tk.hasMore()) {
			String this_line = tk.runTo("\n");
			StringParser line = new StringParser(this_line);
			while (line.hasMore()) {
				String token = line.runToMult("\n ");
				if (token.equalsIgnoreCase("PRINT")) {
					String rest = line.runToMult("\n");
					java.append("System.out.println(" + rest + ");\n");
				} else if (token.equalsIgnoreCase("INPUT")) {
					this.addImport("import ssmith.io.EasyIn;\n");
					String varname = line.runToMult("\n ");
					java.append("String " + varname + " = EasyIn.readString();\n");
				} else if (token.equalsIgnoreCase("\n")) {
					//java.append(");\n");
				} else {
					java.append(token);
				}
			}
		}


		// boilerplate end
		java.append("\n}\n\n}");
		
		// Add imports
		for (int i=0 ; i<imports.size() ; i++) {
			java.insert(0, imports.get(i));
		}
		
		
		if (to_filename != null) {
			TextFile.QuickWrite(to_filename, java.toString(), true);
		}

		//System.out.println(java.toString());

		return java.toString();
	}

	
	private void addImport(String s) {
		if (this.imports.contains(s) == false) {
			this.imports.add(s);
		}
	}

}
