package wig.compiler.ast.exp;

public class LRExpressionNode implements ExpressionNode {
	private ExpressionNode left;
	private ExpressionNode right;

	public ExpressionNode getLeft() {
		return left;
	}

	public void setLeft(ExpressionNode left) {
		this.left = left;
	}

	public ExpressionNode getRight() {
		return right;
	}

	public void setRight(ExpressionNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return null;
	}
}
