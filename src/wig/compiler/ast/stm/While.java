package wig.compiler.ast.stm;

import wig.compiler.ast.exp.ExpressionNode;

public class While implements StmNode {
	private ExpressionNode exp;
	private StmNode stm;
	
	public void setExp(ExpressionNode exp) {
		this.exp = exp;
	}
	public ExpressionNode getExp() {
		return exp;
	}
	public StmNode getStm() {
		return stm;
	}
	public void setStm(StmNode stm) {
		this.stm = stm;
	}
	
	@Override
	public String toString() {
		return "while(" + exp.toString() + ") " + stm.toString();
	}
}
