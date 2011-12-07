package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.value.TupleValue;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.ExpressionType;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedTupleValue extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof TupleValue) {
			type = new ExpressionType(table);
			type.addFieldTypeAll(((TupleValue) node).getFieldValueNodes(),table);
			return this;
		}
		System.err.println("Expected Primitive Value, but got " + node.toString());
		throw new RuntimeException();
	}
}
