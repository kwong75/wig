package wig.compiler.symbol.builder;

import java.util.List;

import wig.compiler.ast.function.FunctionNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.symbolkind.Argument;
import wig.compiler.symbolkind.Function;

public class FunctionSymbolTable {

	public static Function buildFunctionSymbolKind(final SymbolTable table,
			final FunctionNode node) {
		final Function returnValue = new Function();
		returnValue.setName(node.getIdentifier().toString());
		returnValue.setType(table, node.getType().getType());
		return returnValue;
	}

	public static void buildAndPutArgumentSymbols(final Function functionHead,
			final SymbolTable table, final List<wig.compiler.ast.function.Argument> arguments,
			final Boolean display) {
		for (final wig.compiler.ast.function.Argument node : arguments) {
			final Argument argument = new Argument();
			argument.setName(node.getId());
			argument.setType(table, node.getType().getType());
			table.putSymbol(argument.getName(), argument);
			
			if (display)
				System.out.print(argument.toString());
		}
	}
}
