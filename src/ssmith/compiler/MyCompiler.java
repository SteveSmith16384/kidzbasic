package ssmith.compiler;

import java.io.IOException;
import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MyCompiler {


	public static void main(String args[]) throws IOException {
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList("./bin/foo.java")); // Must put it in bin directory
			JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
			boolean success = task.call();
			fileManager.close();
			System.out.println("Success: " + success);
			if (!success) {
				System.out.println(diagnostics.getDiagnostics().get(0));
			} else {
				// Run it
				Class.forName("foo").newInstance();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public static boolean CompileFile(String source_file) throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList("./bin/" + source_file)); // Must put it in bin directory
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
		boolean success = task.call();
		fileManager.close();
		//System.out.println("Success: " + success);
		if (!success) {
			throw new CompilerException(diagnostics.getDiagnostics().get(0).toString());
			//System.out.println(diagnostics.getDiagnostics().get(0));
		} else {
			// Run it
			//Class.forName("foo").newInstance();
		}
		return success;
		
	}


}

