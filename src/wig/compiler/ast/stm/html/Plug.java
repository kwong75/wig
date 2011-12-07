package wig.compiler.ast.stm.html;

import wig.compiler.ast.exp.ExpressionNode;

public class Plug {
	private String identifier;
	private ExpressionNode exp;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public ExpressionNode getExp() {
		return exp;
	}

	public void setExp(ExpressionNode exp) {
		this.exp = exp;
	}

	@Override
	public String toString() {
		return identifier + " = " + exp.toString();
	}
}
