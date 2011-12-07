package wig.compiler.symbolkind;

import wig.compiler.symbol.SymbolTable;
import wig.util.SimpleType;

public class Variable extends SymbolKind {
	private String name;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(SymbolTable table , String type) {
		if (SimpleType.isSimpleType(type)) {
			this.type = type;
			return;
		}
		if (table.getSymbol(type) != null) {
			if (table.getSymbol(type).getKind() instanceof Schema) {
				this.type = type;
				return;	
			}
		}
		throw new RuntimeException("Failed to find type " + type);
	}

	public String toString() {
		return "Variable: name = " + name + " type = " + type;
	}
	
}
