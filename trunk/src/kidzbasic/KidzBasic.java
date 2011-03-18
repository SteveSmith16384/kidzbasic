package kidzbasic;

import ssmith.compiler.MyCompiler;
import ssmith.io.TextFile;
import kidzbasic.compiler.Convertor;
import kidzbasic.gui.MainWindow;

public class KidzBasic {
	
	public KidzBasic() {
		new MainWindow();
	}

	
/*	public static void main(String args[]) {
		new KidzBasic();
	}
	*/
	
	public static void main(String args[]) {
		Convertor c = new Convertor();
		try {
			String basic = TextFile.ReadAll("programs/test.bas", "\n", true);
			String s = c.convert(basic, "./bin/basic.java");
			System.out.println(s);
			if (MyCompiler.CompileFile("basic.java")) {
				Class.forName("basic.class").newInstance();
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
