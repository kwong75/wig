package wig.compiler.ast.html;

public class Attribute {
	private String left;
	// TODO: This holds String, int or identifier
	private String right;

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	@Override
	public String toString() {
		String returnValue = left;
		if (right != null) {
			returnValue = returnValue + " = " + right;
		}
		return returnValue;
	}
}
