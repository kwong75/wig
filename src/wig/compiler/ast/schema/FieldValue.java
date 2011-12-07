package wig.compiler.ast.schema;

import wig.compiler.ast.type.PrimitiveType;

public class FieldValue {
	private PrimitiveType type;
	private String identifier;
	public void setType(PrimitiveType type) {
		this.type = type;
	}
	public PrimitiveType getType() {
		return type;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getIdentifier() {
		return identifier;
	}
	
	@Override
	public String toString() {
		return type.toString() + identifier + ";";
	}
}
