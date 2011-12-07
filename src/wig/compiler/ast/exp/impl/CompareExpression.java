package wig.compiler.ast.exp.impl;

import wig.compiler.ast.exp.LRExpressionNode;

public class CompareExpression extends LRExpressionNode {
	private String sign;

	private enum SIGN {
		EQ, NEQ, GT, LT, LE, GE
	};

	public String getSign() {
		return sign;
	}
	
	public SIGN getSIGN(String sign) {
		if (sign.contentEquals("==")) {
			return SIGN.EQ;
		} else if (sign.contentEquals("!=")) {
			return SIGN.NEQ;
		} else if (sign.contentEquals(">")) {
			return SIGN.GT;
		} else if (sign.contentEquals("<")) {
			return SIGN.LT;
		} else if (sign.contentEquals("<=")) {
			return SIGN.LE;
		} else if (sign.contentEquals(">=")) {
			return SIGN.GE;
		} 
		throw new RuntimeException("CompareExpression sign " + sign + " not found");
	}

	public void setSign(SIGN sign) {
		switch (sign) {
		case EQ:
			this.sign = "==";
			break;
		case NEQ:
			this.sign = "!=";
			break;
		case GT:
			this.sign = ">";
			break;
		case LT:
			this.sign = "<";
			break;
		case LE:
			this.sign = "<=";
			break;
		case GE:
			this.sign = ">=";
			break;
		default:
			throw new RuntimeException();
		}
	}

	public String toString() {
		return getLeft().toString() + sign + getRight().toString();
	}
}
