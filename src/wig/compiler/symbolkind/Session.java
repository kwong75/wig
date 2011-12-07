package wig.compiler.symbolkind;

public class Session extends SymbolKind {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "Session: name = " + name; 
	}
}
