package wig.compiler.typecheck.rule;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.ExpressionType;

public class TypedRule {
	protected ExpressionType type;
	
	public ExpressionType getType() {
		return type;
	}
	
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		return this;
	}
	
}
