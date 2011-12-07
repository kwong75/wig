package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.impl.ParenExpression;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedParenExpression extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof ParenExpression) {
			TypedExpressionNode nodeToCompare = new TypedExpressionNode();
			nodeToCompare.checkExp(((ParenExpression) node).getExp(), table);
			type = nodeToCompare.getType();
			return this;
		}
		System.err.println("Expected a ParenExpression but got" + node.toString());
		throw new RuntimeException();
	}
}
