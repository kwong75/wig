package wig.command;

public class Help {

	public static void display() {
		System.err.println("format java wig.Main options \n" +
				" options: \n" +
				" --display : Compress result, does not show pretty print\n" +
				" -t : Test result by parsing and prettyprinting again \n" +
				" --s : Suppress SymbolTable creation and display \n" + 
				" filename : The file to parsescan");
	}

}
