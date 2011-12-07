package wig.compiler.ast.stm;

import wig.compiler.ast.exp.ExpressionNode;

public class If implements StmNode {
	private ExpressionNode exp;
	private StmNode stm;
	private StmNode elseStm;

	public ExpressionNode getExp() {
		return exp;
	}

	public void setExp(ExpressionNode exp) {
		this.exp = exp;
	}

	public StmNode getStm() {
		return stm;
	}

	public void setStm(StmNode stm) {
		this.stm = stm;
	}

	public StmNode getElseStm() {
		return elseStm;
	}

	public void setElseStm(StmNode elseStm) {
		this.elseStm = elseStm;
	}

	@Override
	public String toString() {
		String returnValue = "if (" + exp.toString() + ") " + stm.toString();
		if (elseStm != null) {
			returnValue = returnValue + "else " + elseStm.toString();
		}
		return returnValue;
	}
}
