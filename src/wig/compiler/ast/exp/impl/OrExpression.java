package wig.compiler.ast.exp.impl;

import wig.compiler.ast.exp.LRExpressionNode;

public class OrExpression extends LRExpressionNode{
	@Override
	public String toString() {
		return getLeft().toString() + " || " + getRight().toString();
	}
}
