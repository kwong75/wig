package wig.compiler.symbol.builder;

import wig.compiler.ast.variable.VariableNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.symbolkind.Variable;

public class VariableSymbolTable {

	public static void buildAndPutVariableSymbolKind(final SymbolTable table,
			final VariableNode node,final Boolean display) {
		for (final String id : node.getIdentifiers()) {
			final Variable variable = new Variable();
			variable.setName(id);
			variable.setType(table, node.getType().getType());
			table.putSymbol(variable.getName(), variable);
			if (display)
				System.out.println(variable.toString());
		}
	}
}
