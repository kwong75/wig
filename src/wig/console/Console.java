package wig.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import wig.command.Switch;
import wig.command.Help;
import wig.compiler.SingleFileToCompile;

public class Console {

	private List<ConsoleCommand> commands = new ArrayList<ConsoleCommand>();
	private List<File> files = new ArrayList<File>();
	private Switch switches;

	public Console(final String[] args) {
		if (args.length == 0) {
			commands.add(ConsoleCommand.HELP);
		}

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				commands.add(ConsoleCommand.getCommand(args[i]));
			} else {
				
				// TODO: handle more than ending with star
				if (args[i].endsWith("*")) {
					String dir = args[i].substring(0,args[i].length() - 1);
					File filedir = new File(dir);
					if (!filedir.isDirectory()) {
						System.err.println("Cannot handle * file properly " + args[i]);
						commands.add(ConsoleCommand.HELP);
						break;
					}
					for (final File file : filedir.listFiles()) {
						checkAndHandleFileString(file.getPath());
					}
				} else {
					checkAndHandleFileString(args[i]);
				}
				
			}
		}

		switches = new Switch(commands);
	}

	private void checkAndHandleFileString(final String filename) {
		if ((new File(filename)).isDirectory()) {
			return;
		}
		File file = new File(filename);
		files.add(file);
	}

	public void run() {
		if (switches.getError()) {
			for (final String msg : switches.getErrMsg()) {
				System.err.println(msg);
			}
			Help.display();
			return;
		}
		
		// TODO: Perhaps implement multi-thread job run 
		for (final File file : files) {
			SingleFileToCompile compile = new SingleFileToCompile(switches, file);
			compile.run();
		}
		
	}

}
