package wig.compiler.ast.function;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.ast.stm.CompoundStm;
import wig.compiler.ast.type.Type;
import wig.compiler.symbol.SymbolTable;

public class FunctionNode {
	private Type type;
	private String identifier;
	private List<Argument> arguments = new ArrayList<Argument>();
	private CompoundStm compoundStm;
	
	//SymbolTable
	private SymbolTable symbolTable;

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void addArgument(Argument argument) {
		this.arguments.add(argument);
	}

	public List<Argument> getArguments() {
		return arguments;
	}

	public CompoundStm getCompoundStm() {
		return compoundStm;
	}

	public void setCompoundStm(CompoundStm compoundStm) {
		this.compoundStm = compoundStm;
	}
	
	@Override
	public String toString() {
		 String returnValue = type.toString() + " " + identifier.toString() + " (";
		 
		 for (final Argument argument : arguments) {
			 returnValue = returnValue + argument.toString() + ",";
		 }
		 
		 if (arguments.size() > 0) {
			 returnValue = returnValue.substring(0, returnValue.length() - 1);
		 }
		 
		 returnValue = returnValue + ")\n";
		 returnValue = returnValue + compoundStm.toString();
		 return returnValue + "\n";
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}



}
