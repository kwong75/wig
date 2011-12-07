package wig.compiler.ast.exp.impl;

import wig.compiler.ast.exp.LRExpressionNode;

public class JoinExpression extends LRExpressionNode {
	@Override
	public String toString() {
		return getLeft().toString() + " join " + getRight().toString();
	}
}
