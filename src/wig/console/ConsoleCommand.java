package wig.console;

public enum ConsoleCommand {
	NOOUTPUT,REPEATTEST,NOSYMBOL,HELP,SUPPRESSERROR,NOTYPE, NODISPLAY;
	
	public static ConsoleCommand getCommand(final String args) {
		if (args.equals("--output")) {
			return NOOUTPUT;
		}
		else if (args.equals("--display")) {
			return NODISPLAY;
		}
		else if (args.equals("-t")) {
			return REPEATTEST;
		}
		else if (args.equals("--s")) {
			return NOSYMBOL;
		}
		else if (args.equals("-e")) {
			return SUPPRESSERROR;
		} 
		else if (args.equals("--type")) {
			return NOTYPE;
		}
		else return HELP;
	}
	
}
