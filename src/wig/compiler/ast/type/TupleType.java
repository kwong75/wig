package wig.compiler.ast.type;

public class TupleType implements Type {
	private String type;

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "tuple " + type;
	}
}
