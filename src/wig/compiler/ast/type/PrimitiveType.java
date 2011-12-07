package wig.compiler.ast.type;

public class PrimitiveType implements Type {
	private String type;

	private enum PRIMITIVETYPE {
		INT, BOOL, STRING, VOID
	};
	
	public String getType() {
		return type;
	}
	
	public PRIMITIVETYPE getSIGN(String type) {
		if (type.contentEquals("int")) {
			return PRIMITIVETYPE.INT;
		} else if (type.contentEquals("bool")) {
			return PRIMITIVETYPE.BOOL;
		} else if (type.contentEquals("string")) {
			return PRIMITIVETYPE.STRING;
		} else if (type.contentEquals("void")) {
			return PRIMITIVETYPE.VOID;
		}
		throw new RuntimeException("PrimitiveType sign " + type + " not found");
	}

	public void setSign(PRIMITIVETYPE type) {
		switch(type) {
			case INT: this.type = "int"; break;
			case BOOL: this.type = "bool"; break;
			case STRING: this.type = "string"; break;
			case VOID: this.type = "void"; break;
			default: throw new RuntimeException();
		}
	}
	
	@Override
	public String toString() {
		return type;
	}
}
