package wig.compiler.ast.exp.value;

import wig.compiler.ast.exp.ExpressionNode;

public class FieldValueNode {
	private String identifier;
	private ExpressionNode node;
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public ExpressionNode getNode() {
		return node;
	}

	public void setNode(ExpressionNode node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return identifier + " = " + node.toString();
	}
}
