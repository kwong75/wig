package wig.compiler.ast.exp;

public class AssignExpressionNode implements ExpressionNode {
	private LValueNode lvalue;
	private ExpressionNode value;

	public LValueNode getLvalue() {
		return lvalue;
	}

	public void setLvalue(LValueNode lvalue) {
		this.lvalue = lvalue;
	}

	public ExpressionNode getValue() {
		return value;
	}

	public void setValue(ExpressionNode value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return lvalue.toString() + " = " + value.toString();
	}
}
