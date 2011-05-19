package kidzbasic;

/**
 * http://www.worldofspectrum.org/faq/reference/BASICReference.htm
 * 
 */
import ssmith.compiler.MyCompiler;
import ssmith.io.EasyIn;
import ssmith.io.TextFile;
import ssmith.lang.Functions;
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
		/*double a = 1;
		while (a != 5) {
			System.out.println("Enter the number 5");
			a = EasyIn.readDouble();
			System.out.println(a);
		}
		System.out.println("Thank you");*/

		Run("programs/while.bas");
	}

public static void Run(String prog) {
	Convertor c = new Convertor();
	try {
		Functions.p("Loading program...");
		String basic = TextFile.ReadAll(prog, "\n", true);
		Functions.p("Converting...");
		String s = c.convert(basic, "./bin/basic.java");
		System.out.println(s);
		Functions.p("Compiling...");
		if (MyCompiler.CompileFile("basic.java")) {
			Functions.p("Running...");
			Class.forName("basic").newInstance();
			Functions.p("0 OK");
		}
		/*} catch (java.text.ParseException e) {
			Functions.p(e.getMessage());
			e.printStackTrace();*/
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}
