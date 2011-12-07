package wig.compiler.ast.exp.impl;

import wig.compiler.ast.exp.LRExpressionNode;

public class AdditionExpression extends LRExpressionNode {
	private String sign;
	private enum SIGN {PLUS, MINUS};
	
	public String getSign() {
		return sign;
	}
	
	public SIGN getSIGN(String sign) {
		if (sign.contentEquals("+")) {
			return SIGN.PLUS;
		} else if (sign.contentEquals("-")) {
			return SIGN.MINUS;
		} 
		throw new RuntimeException("AdditionExpression sign " + sign + " not found");
	}

	public void setSign(SIGN sign) {
		switch(sign) {
			case PLUS: this.sign = "+"; break;
			case MINUS: this.sign = "-"; break;
			default: throw new RuntimeException();
		}
	}

	@Override 
	public String toString() {
		return getLeft().toString() + sign + getRight().toString();
	}
}
