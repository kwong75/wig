package wig.compiler.ast.exp.value;

public class BooleanValue extends PrimitiveValue {
	private String value;
	private enum BOOLEANVALUE {TRUE, FALSE};
	
	public void setValue(BOOLEANVALUE value) {
		switch(value) {
			case TRUE: this.value = "true";break;
			case FALSE: this.value = "false";break;
			default: throw new RuntimeException();
		}
	}
	
	public BOOLEANVALUE getBOOLEANVALUE(final String value) {
		if (value.contentEquals("true")) {
			return BOOLEANVALUE.TRUE;
		} else if (value.contentEquals("false")) {
			return BOOLEANVALUE.FALSE;
		}
		throw new RuntimeException("No BooleanValue of " + value);
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getSign() + value;
	}
}
