package wig.compiler.typecheck.rule.exp;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.LValueNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.symbolkind.Argument;
import wig.compiler.symbolkind.Field;
import wig.compiler.symbolkind.Schema;
import wig.compiler.symbolkind.SymbolKind;
import wig.compiler.symbolkind.Variable;
import wig.compiler.typecheck.ExpressionType;
import wig.compiler.typecheck.rule.TypedRule;

public class TypedLValue extends TypedRule {

	@Override
	public TypedRule checkExp(final ExpressionNode node, final SymbolTable table) {
		if (node instanceof LValueNode) {
			type = getLValueType(node, table);
		}
		return this;
	}

	public ExpressionType getLValueType(final ExpressionNode node,
			final SymbolTable table) {
		final LValueNode lvalue = (LValueNode) node;
		final ExpressionType type = new ExpressionType(table);
		if (lvalue.getIdentifiers().size() == 1) {
			try {
			SymbolKind kind = table.getSymbol(lvalue.getIdentifiers().get(0))
					.getKind();
			if (kind instanceof Variable) {
				Variable variable = (Variable) kind;
				if (type.isPrimitive(variable.getType())) {
					type.setPrimitiveType(variable.getType());
				} else {
					Schema schema = (Schema) table.getSymbol(
							variable.getType(), Schema.class).getKind();
					type.addFieldTypeAll(schema.getFields());
				}
			} else if (kind instanceof Argument) {
				Argument variable = (Argument) kind;
				if (type.isPrimitive(variable.getType())) {
					type.setPrimitiveType(variable.getType());
				} else {
					Schema schema = (Schema) table.getSymbol(
							variable.getType(), Schema.class).getKind();
					type.addFieldTypeAll(schema.getFields());
				}
			}
			}catch (NullPointerException e) {
				throw new NullPointerException(node.toString());
			}
		} else if (lvalue.getIdentifiers().size() == 2) {
			Schema schema = null;
			SymbolKind kind = table.getSymbol(
					lvalue.getIdentifiers().get(0)).getKind();
			if (kind instanceof Variable) {
			Variable variable = (Variable) kind;
			schema = (Schema) table.getSymbol(variable.getType(),
					Schema.class).getKind();
			} else if (kind instanceof Argument) {
				Argument variable = (Argument) kind;
				schema = (Schema) table.getSymbol(variable.getType(),
						Schema.class).getKind();
			}

			for (final Field field : schema.getFields()) {
				if (field.getName().contentEquals(
						lvalue.getIdentifiers().get(1))) {
					if (type.isPrimitive(field.getType())) {
						type.setPrimitiveType(field.getType());
					} else {
						Schema schema2 = (Schema) table.getSymbol(
								field.getType(), Schema.class).getKind();
						type.addFieldTypeAll(schema2.getFields());
					}
				}
			}
		}
		return type;
	}

}
