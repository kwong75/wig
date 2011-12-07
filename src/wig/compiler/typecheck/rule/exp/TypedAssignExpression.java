package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.AssignExpressionNode;
import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedAssignExpression extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof AssignExpressionNode) {
			final TypedLValue ltype = new TypedLValue();
			ltype.checkExp(((AssignExpressionNode) node).getLvalue(), table);
			final TypedExpressionNode rtype = new TypedExpressionNode();
			rtype.checkExp(((AssignExpressionNode) node).getValue(),table);
			if (ltype.getType().compareTo(rtype.getType())) {
				type = ltype.getType();
				return this;
			}
		}
		System.err.println("For node " + node.toString() + " expected to be AssignExpression");
		throw new RuntimeException();
	}
}
