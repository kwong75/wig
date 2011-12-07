package wig.compiler.typecheck;

public class FieldType {
	private ExpressionType type;
	private String name;
	
	public void setType(ExpressionType type) {
		this.type = type;
	}
	public ExpressionType getType() {
		return type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
}
