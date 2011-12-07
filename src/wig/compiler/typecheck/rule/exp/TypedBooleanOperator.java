package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.LRExpressionNode;
import wig.compiler.ast.exp.impl.AndExpression;
import wig.compiler.ast.exp.impl.OrExpression;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedBooleanOperator extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof AndExpression || node instanceof OrExpression) {
			TypedExpressionNode type1 = new TypedExpressionNode();
			TypedExpressionNode type2 = new TypedExpressionNode();
			type1.checkExp(((LRExpressionNode) node).getLeft(), table);
			type2.checkExp(((LRExpressionNode) node).getRight(), table);
			if ("bool".contentEquals(type1.getType().getPrimitiveType())
					&& ("bool".contentEquals(type2.getType().getPrimitiveType()))) {
				type = type1.getType();
				return this;
			}
		}
		System.err.println("Expecting for AndExpression or OrExpression 2 bool for " + node.toString());
		throw new RuntimeException();
	}
}
