package wig.compiler.ast.schema;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.symbol.SymbolTable;

public class SchemaNode {
	private String identifier;
	private List<FieldValue> fields = new ArrayList<FieldValue>();
	
	//SymbolTable
	private SymbolTable symbolTable;
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	public void addField(FieldValue field) {
		fields.add(field);
	}
	public List<FieldValue> getFields() {
		return fields;
	}
	
	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	@Override
	public String toString() {
		String returnValue = "schema " + identifier + "{ \n";
		for (final FieldValue field : fields) {
			returnValue = returnValue + field.toString() + "\n";
		}
		return returnValue;
	}
	
	
}
