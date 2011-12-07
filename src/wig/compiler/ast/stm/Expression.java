package wig.compiler.ast.stm;

import wig.compiler.ast.exp.ExpressionNode;

public class Expression implements StmNode {
	private ExpressionNode exp;

	public void setExp(ExpressionNode exp) {
		this.exp = exp;
	}

	public ExpressionNode getExp() {
		return exp;
	}
	
	@Override
	public String toString() {
		return exp.toString() + ";\n";
	}
}
