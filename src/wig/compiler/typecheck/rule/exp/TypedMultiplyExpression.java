package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.impl.MultiplyExpression;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedMultiplyExpression extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof MultiplyExpression) {
			TypedExpressionNode type1 = new TypedExpressionNode();
			TypedExpressionNode type2 = new TypedExpressionNode();
			type1.checkExp(((MultiplyExpression) node).getLeft(), table);
			type2.checkExp(((MultiplyExpression) node).getRight(), table);
			if ("int".contentEquals(type1.getType().getPrimitiveType())
					&& ("int".contentEquals(type2.getType().getPrimitiveType()))) {
				type = type1.getType();
				return this;
			}
		}
		System.err.println("Expection to be multiplication of int for " + node.toString());
		throw new RuntimeException();
	}
}