package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.impl.CompareExpression;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.ExpressionType;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedCompareExpression extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof CompareExpression) {
			TypedExpressionNode type1 = new TypedExpressionNode();
			TypedExpressionNode type2 = new TypedExpressionNode();
			type1.checkExp(((CompareExpression) node).getLeft(), table);
			type2.checkExp(((CompareExpression) node).getRight(), table);
			if (type1.getType().getPrimitiveType() != null
					&& type2.getType().getPrimitiveType() != null) {
				if (type1.getType().getPrimitiveType().contentEquals("int")
						&& type2.getType().getPrimitiveType()
								.contentEquals("int")) {
					type = new ExpressionType(table);
					type.setPrimitiveType("bool");
					return this;
				} else if (((CompareExpression) node).getSign().contentEquals(
						"==")
						|| ((CompareExpression) node).getSign().contentEquals(
								"!=")) {
					if (type1.getType().getPrimitiveType()
							.contentEquals("bool")
							&& type2.getType().getPrimitiveType()
									.contentEquals("bool")) {
						type = new ExpressionType(table);
						type.setPrimitiveType("bool");
						return this;
					} else if (type1.getType().getPrimitiveType()
							.contentEquals("int")
							&& type2.getType().getPrimitiveType()
									.contentEquals("int")) {
						type = new ExpressionType(table);
						type.setPrimitiveType("bool");
						return this;
					} else if (type1.getType().getPrimitiveType()
							.contentEquals("string")
							&& type2.getType().getPrimitiveType()
									.contentEquals("string")) {
						type = new ExpressionType(table);
						type.setPrimitiveType("bool");
						return this;
					}
				}
			}
		}
		System.err.println(
				"Can only compare 2 int for all sign except for == or != which can be either 2 int, 2 bool or 2 string, for "
						+ node.toString());
		throw new RuntimeException();
	}
}
