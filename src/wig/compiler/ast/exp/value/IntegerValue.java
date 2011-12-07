package wig.compiler.ast.exp.value;

public class IntegerValue extends PrimitiveValue {
	private int value;

	public void setValue(final String value) {
		this.value = Integer.valueOf(value);
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getSign() + String.valueOf(value);
	}
}
