package wig.compiler.ast.exp.value;

import wig.compiler.ast.exp.BaseExp;

public class PrimitiveValue extends BaseExp {

	public String getType() {
		if (this instanceof BooleanValue) {
			return "bool";
		} else if (this instanceof StringValue) {
			return "string";
		} else if (this instanceof IntegerValue) {
			return "int";
		}
		throw new RuntimeException("PrimitiveValue should not be constructed alone");
	}
	
}
