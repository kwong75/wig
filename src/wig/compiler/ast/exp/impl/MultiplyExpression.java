package wig.compiler.ast.exp.impl;

import wig.compiler.ast.exp.LRExpressionNode;

public class MultiplyExpression extends LRExpressionNode {
	private String sign;

	private enum SIGN {
		TIMES, DIVIDE, MOD
	};

	public String getSign() {
		return sign;
	}
	
	public SIGN getSIGN(String sign) {
		if (sign.contentEquals("*")) {
			return SIGN.TIMES;
		} else if (sign.contentEquals("/")) {
			return SIGN.DIVIDE;
		} else if (sign.contentEquals("%")) {
			return SIGN.MOD;
		}
		throw new RuntimeException("MultiplyExpression sign " + sign + " not found");
	}

	public void setSign(SIGN sign) {
		switch (sign) {
		case TIMES:
			this.sign = "*";
			break;
		case DIVIDE:
			this.sign = "/";
			break;
		case MOD:
			this.sign = "%";
			break;
		default:
			throw new RuntimeException();
		}
	}

	@Override
	public String toString() {
		return getLeft().toString() + sign + getRight().toString();
	}
}
