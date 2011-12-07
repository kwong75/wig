package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.value.PrimitiveValue;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.ExpressionType;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedToPrimitiveValue extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof PrimitiveValue) {
			type = new ExpressionType(table);
			type.setPrimitiveType(((PrimitiveValue) node).getType());
			return this;
		}
		System.err.println("Expected Primitive Value, but got " + node.toString());
		throw new RuntimeException();
	}
}
