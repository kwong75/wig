package wig.compiler.ast.session;

import wig.compiler.ast.stm.CompoundStm;

public class SessionNode {
	private String identifier;
	private CompoundStm compoundStm;
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}
	
	public CompoundStm getCompoundStm() {
		return compoundStm;
	}

	public void setCompoundStm(CompoundStm compoundStm) {
		this.compoundStm = compoundStm;
	}
	
	@Override
	public String toString() {
		String returnValue = "session " + identifier + " ()\n";
		returnValue = returnValue + compoundStm.toString() + "\n";
		return returnValue;
	}
}
