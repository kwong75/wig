package wig.compiler.ast.variable;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.ast.type.Type;

public class VariableNode {
	private Type type;
	private List<String> identifiers = new ArrayList<String>();

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void addIdentifier(String identifier) {
		this.identifiers.add(identifier);
	}

	public List<String> getIdentifiers() {
		return identifiers;
	}

	@Override
	public String toString() {
		String returnValue = type.toString() + " ";
		
		for (final String identifier : identifiers) {
			returnValue = returnValue + identifier + ",";
		}
		returnValue = returnValue.substring(0,returnValue.length() -1);
		returnValue = returnValue + ";\n";
		return returnValue;
	}
}
