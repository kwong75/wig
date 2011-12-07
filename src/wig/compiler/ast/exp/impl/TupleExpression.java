package wig.compiler.ast.exp.impl;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.ast.exp.ExpressionNode;

public class TupleExpression implements ExpressionNode {
	private String sign;

	private enum SIGN {
		KEEP, REMOVE
	};

	private ExpressionNode left;
	private List<String> identifiers = new ArrayList<String>();

	public String getSign() {
		return sign;
	}

	public SIGN getSIGN(String sign) {
		if (sign.contentEquals("keep")) {
			return SIGN.KEEP;
		} else if (sign.contentEquals("remove")) {
			return SIGN.REMOVE;
		}
		throw new RuntimeException("TupleExpression sign " + sign
				+ " not found");
	}

	public void setSign(SIGN sign) {
		switch (sign) {
		case KEEP:
			this.sign = "keep";
			break;
		case REMOVE:
			this.sign = "remove";
			break;
		default:
			throw new RuntimeException();
		}
	}

	public ExpressionNode getLeft() {
		return left;
	}

	public void setLeft(ExpressionNode left) {
		this.left = left;
	}

	public void addIdentifier(final String id) {
		identifiers.add(id);
	}

	public List<String> getIdentifiers() {
		return identifiers;
	}

	public boolean hasMultiple() {
		return identifiers.size() > 1;
	}

	@Override
	public String toString() {
		String rightString = "";

		if (hasMultiple()) {
			rightString = "(" + rightString;
		}

		for (String identifier : identifiers) {
			rightString = rightString + identifier + ",";
		}

		rightString = rightString.substring(0, rightString.length() - 1);

		if (hasMultiple()) {
			rightString = ")" + rightString;
		}

		return left.toString() + sign + rightString;
	}
}
