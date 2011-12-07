package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.AssignExpressionNode;
import wig.compiler.ast.exp.BaseExp;
import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.LValueNode;
import wig.compiler.ast.exp.impl.AdditionExpression;
import wig.compiler.ast.exp.impl.AndExpression;
import wig.compiler.ast.exp.impl.CompareExpression;
import wig.compiler.ast.exp.impl.MultiplyExpression;
import wig.compiler.ast.exp.impl.OrExpression;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedExpressionNode extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof AssignExpressionNode) {
			TypedAssignExpression typeTo = new TypedAssignExpression();
			typeTo.checkExp(node, table);
			type = typeTo.getType();
		} else if (node instanceof LValueNode) {
			TypedLValue typeTo = new TypedLValue();
			typeTo.checkExp(node, table);
			type = typeTo.getType();
		} else if (node instanceof BaseExp) {
			TypedBasedExpression typeTo = new TypedBasedExpression();
			typeTo.checkExp(node, table);
			type = typeTo.getType();
		} else if (node instanceof AdditionExpression) {
			TypedAdditionOperation typeTo = new TypedAdditionOperation();
			typeTo.checkExp(node, table);
			type = typeTo.getType();
		} else if (node instanceof MultiplyExpression) {
			TypedMultiplyExpression typeTo = new TypedMultiplyExpression();
			typeTo.checkExp(node, table);
			type = typeTo.getType();
		} else if (node instanceof CompareExpression) {
			TypedCompareExpression typeTo = new TypedCompareExpression();
			typeTo.checkExp(node, table);
			type = typeTo.getType();
		} else if (node instanceof AndExpression || node instanceof OrExpression) {
			TypedBooleanOperator typeTo = new TypedBooleanOperator();
			typeTo.checkExp(node, table);
			type = typeTo.getType();
		} else {
			System.err.println("A Typed Expression not handled for " + node.toString());
			throw new RuntimeException();
		}
		return this;
	}
}
