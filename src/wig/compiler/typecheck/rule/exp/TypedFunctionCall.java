package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.value.FunctionCall;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.symbolkind.Function;
import wig.compiler.typecheck.ExpressionType;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedFunctionCall extends TypedRule {
	@Override
	public TypedRule checkExp(ExpressionNode node, SymbolTable table) {
		if (node instanceof FunctionCall) {
			FunctionCall call = (FunctionCall) node;
			Function function = (Function) table.getSymbol(
					call.getIdentifier(), Function.class).getKind();
			for (int i = 0; i < function.getArgumentsType().size(); i++) {
				final ExpressionType type = new ExpressionType(table);
				final String typeStr = function.getArgumentsType().get(i)
						.getType();
				if (type.isPrimitive(typeStr)) {
					type.setPrimitiveType(typeStr);
				} else {
					type.addFieldTypeAll(typeStr);
				}
				final TypedExpressionNode toCompare = new TypedExpressionNode();
				toCompare.checkExp(call.getExpressionNodes().get(i), table);
				if (!toCompare.getType().compareTo(type)) {
					System.err.println("For node " + node.toString()
							+ " an argument expected type "
							+ function.getArgumentsType().get(i).getType());
					throw new RuntimeException();
				}
			}
			final ExpressionType type = new ExpressionType(table);
			if (type.isPrimitive(function.getType())) {
				type.setPrimitiveType(function.getType());
			} else {
				type.addFieldTypeAll(function.getName());
			}
			this.type = type;
			return this;
		}
		System.err.println("For node " + node.toString()
				+ " was expecting a FunctionCall");
		throw new RuntimeException();
	}
}
