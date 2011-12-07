package wig.compiler.ast.exp.value;

public class StringValue extends PrimitiveValue {
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getSign() + value;
	}
}
