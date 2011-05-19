package kidzbasic.compiler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;

import ssmith.io.TextFile;
import ssmith.util.StringParser;

/**
 * This converts a .bas file to a java file.
 *
 */
public class Convertor {

	private static final String DEF_NUM_TYPE = "double"; // Cannot be float otherwise we need to append "f" to all numbers!

	private HashSet<String> imports = new HashSet<String>();
	private HashSet<String> vars = new HashSet<String>();

	public Convertor() {

	}


	public String convert(String basic, String to_filename) throws FileNotFoundException, IOException, ParseException {
		StringBuffer java = new StringBuffer();
		// Boilerplate start
		java.append("public class basic { \n\n");
		java.append("public basic() { \n");

		//basic.replaceAll("PRINT", "System.out.println(");
		StringParser tk = new StringParser(basic);
		int line_num = 0;
		while (tk.hasMore()) {
			String this_line = tk.runTo("\n");
			line_num++;
			StringParser line = new StringParser(this_line);
			while (line.hasMore()) {
				String token = line.runToMult("\n ");
				
				/*if (token.equalsIgnoreCase("<>")) {
					token = "!=";
				}*/
				
				if (token.equalsIgnoreCase("PRINT")) {
					String rest = line.runToMult("\n");
					java.append("System.out.println(" + rest + ");\n");
				} else if (token.equalsIgnoreCase("WHILE")) {
					String rest = line.runToMult("\n");
					java.append("while (" + rest + ") {\n");
				} else if (token.equalsIgnoreCase("WEND")) {
					String rest = line.runToMult("\n");
					if (rest.trim().length() > 0) {
						throw new ParseException("Unexpected tokens on line " + line_num + ": " + token, line_num);
					}
					java.append("}\n");
				} else if (token.equalsIgnoreCase("REM")) {
					line.runToMult("\n"); // Ignore it
				} else if (token.equalsIgnoreCase("INPUT")) {
					this.imports.add("import ssmith.io.EasyIn;\n");
					String varname = line.runToMult("\n ");
					boolean new_var = false;
					if (this.vars.contains(varname)== false) { // New var
						this.vars.add(varname);
						new_var = true;
					}
					boolean is_string = false;
					if (varname.endsWith("$")) {
						is_string = true;
					}
					if (new_var) {
						if (is_string) {
							java.append("String ");// + varname + " = EasyIn.readString();\n");
						} else {
							java.append(DEF_NUM_TYPE + " ");// + varname + " = EasyIn.readFloat();\n");
						}
					}
					java.append(varname + " = ");
					if (is_string) {
						java.append("EasyIn.readString();\n");
					} else {
						java.append("EasyIn.readDouble();\n");
					}
				} else if (token.equalsIgnoreCase("LET")) {
					String varname = line.runToMult(" ");
					if (this.vars.contains(varname) ) {
						throw new ParseException("Unexpected tokens on line " + line_num + ": " + token, line_num);
					} else {
						this.vars.add(varname);
					}
					String rest = line.runToMult("\n");
					if (varname.endsWith("$")) {
						java.append("String");
					} else {
						java.append(DEF_NUM_TYPE);
					}
					java.append(" " + varname + " " + rest + ";\n");
					//} else if (token.equalsIgnoreCase("\n")) {
					//java.append(");\n");
				} else {
					throw new ParseException("Unknown token: " + token + " on line " + line_num, line_num);
					//java.append(token);
				}
			}
		}


		// boilerplate end
		java.append("\n}\n\n}");

		// Add imports
		Iterator<String> it = imports.iterator();
		while (it.hasNext()) {
			java.insert(0, it.next());
		}


		if (to_filename != null) {
			TextFile.QuickWrite(to_filename, java.toString(), true);
		}

		//System.out.println(java.toString());

		return java.toString();
	}


	/*private void addImport(String s) {
		if (this.imports.contains(s) == false) {
			this.imports.add(s);
		}
	}


	private void addVarName(String s) {
		if (this.vars.contains(s) == false) {
			this.vars.add(s);
		}
	}*/


}
