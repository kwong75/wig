package wig.command;

import java.util.ArrayList;
import java.util.List;

import wig.console.ConsoleCommand;

public class Switch {
	private Boolean noOutput = false;
	private Boolean repeatTest = false;
	private Boolean noSymbol = false;
	private Boolean suppressError = false;
	private Boolean noType = false;
	private Boolean error = false;
	private Boolean noDisplay = false;
	private List<String> errMsg = new ArrayList<String>();

	public Switch(List<ConsoleCommand> commands) {
		for (ConsoleCommand command : commands) {
			if (command == ConsoleCommand.HELP) {
				error = true;
				break;
			} else if (command == ConsoleCommand.NOOUTPUT) {
				noOutput = true;
			} else if (command == ConsoleCommand.REPEATTEST) {
				repeatTest = true;
			} else if (command == ConsoleCommand.NOSYMBOL) {
				noSymbol = true;
			} else if (command == ConsoleCommand.SUPPRESSERROR) {
				suppressError = true;
			} else if (command == ConsoleCommand.NOTYPE) {
				noType = true;
			} else if (command == ConsoleCommand.NODISPLAY) {
				noDisplay = true;
			}
			else {
				error = true;
				errMsg.add("A command was introduced but not handled in Command.java");
				break;
			}
		}
	}

	public Boolean getNoOutput() {
		return noOutput;
	}

	public Boolean getRepeatTest() {
		return repeatTest;
	}

	public Boolean getNoSymbol() {
		return noSymbol;
	}

	public Boolean getError() {
		return error;
	}
	
	public Boolean getSuppressError() {
		return suppressError;
	}

	public List<String> getErrMsg() {
		return errMsg;
	}

	public boolean getNoTypeCheck() {
		return noType;
	}

	public Boolean getNoDisplay() {
		return noDisplay;
	}

}
