package wig.compiler.ast.function;

import wig.compiler.ast.type.Type;

public class Argument {
	private Type type;
	private String id;

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return type + " " + id;
	}

}
