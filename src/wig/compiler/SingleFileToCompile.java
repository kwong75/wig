package wig.compiler;

import java.io.File;
import java.io.FileReader;
import java.io.PushbackReader;
import java.io.StringReader;

import wig.command.Switch;
import wig.compiler.ast.BuildServiceAst;
import wig.compiler.ast.ServiceNode;
import wig.compiler.typecheck.CheckType;
import wig.lexer.Lexer;
import wig.lexer.LexerException;
import wig.node.Start;
import wig.parser.Parser;
import wig.parser.ParserException;

public class SingleFileToCompile {
	private Switch switches;
	private File file;
	private String prettyprint;
	private ServiceNode ast;

	public SingleFileToCompile(final Switch switches, final File file) {
		this.switches = switches;
		this.file = file;
	}

	public void run() {
		try {
			FileReader fileReader = new FileReader(file);
			Parser p = new Parser(new Lexer(new PushbackReader(fileReader, 1024)));
			Start tree;
			try {
				 tree = p.parse();
				System.out.println("Parse successful for " + file.toString());
			} catch (Exception e) {
				throw e;
			}
			ast = BuildServiceAst.run(tree);
			prettyprint = ast.toString();

			/* Pretty Print */
			if (!switches.getNoOutput()) {
				output();
			}

			/* Repeat twice test */
			if (switches.getRepeatTest()) {
				repeatTest();
			}
			
			if (!switches.getNoSymbol()) {
				BuildSymbolTable.run(ast,!switches.getNoDisplay());
				System.out.println("Symbol Table created");
				if (!switches.getNoTypeCheck()) {
					try {
						CheckType.run(ast);
						System.out.println("Passed Typed Check");
					} catch(Exception e) {
//						System.out.println("Failed Typed Check");
						throw e;
					}
				}
			}
			/* Exception handling */
		} catch (Exception e) {
			handleException(e);
		}
	}

	private void handleException(Exception e) {
		if (e instanceof LexerException) {
			System.out.println("For file " + file.toString()
					+ " received Lexer Error");
			if (!switches.getSuppressError()) {
				e.printStackTrace();
			}
		} else if (e instanceof ParserException) {
			System.out.println("For file " + file.toString()
					+ " received Parse Error");
			if (!switches.getSuppressError()) {
				e.printStackTrace();
			}
		} else {
			e.printStackTrace();
		}

	}

	private void output() {
		System.out.println("Pretty printing: ");
		System.out.println(prettyprint);
	}

	private void repeatTest() throws Exception {
		StringReader read = new StringReader(prettyprint);
		Parser p2 = new Parser(new Lexer(new PushbackReader(read, 1024)));
		String string2;
		try {
			string2 = BuildServiceAst.run((Start) p2.parse()).toString();
		} catch (Exception e) {
			throw e;
		}

		// White space does not clean
		if (prettyprint.equals(string2)) {
			System.out
					.println("The result of parsing and prettyprinting twice is true for "
							+ file.toString());
		} else {
			System.out.println(string2);
		}
	}

}
