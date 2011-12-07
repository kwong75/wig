package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.impl.AdditionExpression;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedAdditionOperation extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof AdditionExpression) {
			TypedExpressionNode type1 = new TypedExpressionNode();
			TypedExpressionNode type2 = new TypedExpressionNode();
			type1.checkExp(((AdditionExpression) node).getLeft(), table);
			type2.checkExp(((AdditionExpression) node).getRight(), table);
			if (("int".contentEquals(type1.getType().getPrimitiveType()) || "string"
					.contentEquals(type1.getType().getPrimitiveType()))
					&& ("int".contentEquals(type2.getType().getPrimitiveType()) || "string"
							.contentEquals(type2.getType().getPrimitiveType()))) {
				if ("string".contentEquals(type1.getType().getPrimitiveType())) {
					type = type1.getType();
				} else {
					type = type2.getType();
				}
				return this;
			}
		}
		System.err.println("Expection to be addition of string or int for " + node.toString());
		throw new RuntimeException();
	}
}
