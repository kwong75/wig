package wig.compiler;

import wig.compiler.ast.ServiceNode;
import wig.compiler.ast.function.FunctionNode;
import wig.compiler.ast.html.HtmlNode;
import wig.compiler.ast.schema.SchemaNode;
import wig.compiler.ast.session.SessionNode;
import wig.compiler.ast.variable.VariableNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.symbol.builder.CompoundStmSymbolTable;
import wig.compiler.symbol.builder.FunctionSymbolTable;
import wig.compiler.symbol.builder.HtmlSymbolTable;
import wig.compiler.symbol.builder.SchemaSymbolTable;
import wig.compiler.symbol.builder.VariableSymbolTable;
import wig.compiler.symbolkind.Function;
import wig.compiler.symbolkind.Schema;
import wig.compiler.symbolkind.Session;
import wig.compiler.symbolkind.html.Html;

public class BuildSymbolTable {

	public static SymbolTable run(final ServiceNode service,
			final Boolean display) {
		final SymbolTable returnValue = new SymbolTable();
		service.setSymbolTable(returnValue);
		// Building Html SymbolTable
		for (HtmlNode html : service.getHtmls()) {
			final Html htmlKind = HtmlSymbolTable.buildHtmlSymbolKind(html);
			returnValue.putSymbol(html.getIdentifier(), htmlKind);
			if (display)
				System.out.println(htmlKind.toString());
		}

		for (SchemaNode schema : service.getSchemas()) {
			final Schema schemaKind = SchemaSymbolTable
					.buildSchemaSymbolKind(schema);
			returnValue.putSymbol(schema.getIdentifier(), schemaKind);
			if (display)
				System.out.println(schemaKind.toString());
		}

		for (VariableNode variable : service.getVariables()) {
			VariableSymbolTable.buildAndPutVariableSymbolKind(returnValue,
					variable, display);

		}

		// Will have to handle in 2 pass for compoundstm.
		// This will allow function to call all other functions
		for (FunctionNode function : service.getFunctions()) {
			final Function functionKind = FunctionSymbolTable
					.buildFunctionSymbolKind(returnValue, function);
			returnValue.putSymbol(function.getIdentifier(), functionKind);
		}

		for (FunctionNode function : service.getFunctions()) {
			final SymbolTable functionImpl = new SymbolTable();
			functionImpl.scopeSymbolTable(returnValue);
			function.setSymbolTable(functionImpl);
			Function functionHead = (Function) returnValue.getSymbol(
					function.getIdentifier()).getKind();
			if (display) {
				System.out.print(functionHead.toString());
				System.out.print("{\n");
			}
			FunctionSymbolTable.buildAndPutArgumentSymbols(functionHead,
					functionImpl, function.getArguments(), display);
			CompoundStmSymbolTable.buildCompoundStmSymbolTable(functionImpl,
					function.getCompoundStm(), display);
			if (display) {
				System.out.print("}\n\n");
			}

		}

		// Not going to build this in multipass
		for (final SessionNode session : service.getSessions()) {
			final Session sessionKind = new Session();
			sessionKind.setName(session.getIdentifier());
			if (display) {
				System.out.print(sessionKind.toString() + "{\n");
			}
			// Awkward to have SymbolTable, since there should be no symbols in it.
			final SymbolTable sessionImpl = new SymbolTable();
			sessionImpl.scopeSymbolTable(returnValue);
			CompoundStmSymbolTable.buildCompoundStmSymbolTable(sessionImpl,
					session.getCompoundStm(), display);
			if (display) {
				System.out.print("}\n\n");
			}

		}

		return returnValue;
	}
}
