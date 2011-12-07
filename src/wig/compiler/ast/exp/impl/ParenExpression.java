package wig.compiler.ast.exp.impl;

import wig.compiler.ast.exp.BaseExp;
import wig.compiler.ast.exp.ExpressionNode;

public class ParenExpression extends BaseExp {
	private ExpressionNode exp;

	public ExpressionNode getExp() {
		return exp;
	}

	public void setExp(ExpressionNode exp) {
		this.exp = exp;
	}
	
	@Override
	public String toString() {
		return "(" + exp.toString() + ")";
	}
}
