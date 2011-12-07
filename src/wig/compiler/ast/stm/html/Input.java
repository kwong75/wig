package wig.compiler.ast.stm.html;

import wig.compiler.ast.exp.LValueNode;

public class Input {
	private LValueNode lvalue;
	private String identifier;

	public LValueNode getLvalue() {
		return lvalue;
	}

	public void setLvalue(LValueNode lvalue) {
		this.lvalue = lvalue;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return lvalue.toString() + " = " + identifier;
	}

}
