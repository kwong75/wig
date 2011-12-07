package wig.compiler.typecheck.rule.stm;

import wig.compiler.ast.stm.html.Document;
import wig.compiler.ast.stm.html.Plug;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.exp.TypedExpressionNode;

public class TypedDocumentStm {

	public static void checkExp(Document docs, SymbolTable table) {
		if (docs != null) {
			for (final Plug plug : docs.getPlugs()) {
				final TypedExpressionNode exp = new TypedExpressionNode();
				exp.checkExp(plug.getExp(), table);
				if (!exp.getType().getIsPrimitive()) {
					System.err.println(
							"Attempting to plug a non Primitive value");
					throw new RuntimeException();
				}
			}
		}
	}
}
