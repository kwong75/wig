package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.BaseExp;
import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.impl.ParenExpression;
import wig.compiler.ast.exp.value.FunctionCall;
import wig.compiler.ast.exp.value.PrimitiveValue;
import wig.compiler.ast.exp.value.TupleValue;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedBasedExpression extends TypedRule {
	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof BaseExp) {
			if (node instanceof ParenExpression) {
				TypedParenExpression result = new TypedParenExpression();
				result.checkExp(node, table);
				type = result.getType();
			} else if (node instanceof PrimitiveValue) {
				TypedToPrimitiveValue result = new TypedToPrimitiveValue();
				result.checkExp(node, table);
				type = result.getType();
			} else if (node instanceof TupleValue) {
				TypedTupleValue result = new TypedTupleValue();
				result.checkExp(node, table);
				type = result.getType();
			} else if (node instanceof FunctionCall) {
				TypedFunctionCall result = new TypedFunctionCall();
				result.checkExp(node, table);
				type = result.getType();
			} else {
				System.err.println("Unhandled event for " + node.toString());
				throw new RuntimeException();
			}
			
			BaseExp baseExp = (BaseExp) node;
			if (baseExp.getSign().contentEquals("!")) {
				if (type.getPrimitiveType() == null || !type.getPrimitiveType().contentEquals("bool")) {
					System.err.println("Expected to typed to bool after unary ! " + node.toString());
					throw new RuntimeException();
				}
			} else if (baseExp.getSign().contentEquals("-")) {
				if (type.getPrimitiveType() == null || !type.getPrimitiveType().contentEquals("int")) {
					System.err.println("Expected to typed to int after unary - " + node.toString());
					throw new RuntimeException();
				}
			}
			return this;
		}
		System.err.println("Expected TypedBasedExpression but got " + node.toString());
		throw new RuntimeException();
	}
}
