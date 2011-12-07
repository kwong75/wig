package wig.compiler.ast.stm;

import wig.compiler.ast.exp.ExpressionNode;

public class Return implements StmNode {
	private ExpressionNode exp;

	public ExpressionNode getExp() {
		return exp;
	}

	public void setExp(ExpressionNode exp) {
		this.exp = exp;
	}
	
	@Override
	public String toString() {
		String returnValue = "return";
		if (exp != null) {
			returnValue = returnValue + " " + exp.toString();
		}
		return returnValue + ";";
	}

}
