package wig.compiler.typecheck.rule.stm;

import wig.compiler.ast.stm.Exit;
import wig.compiler.ast.stm.StmNode;
import wig.compiler.symbol.SymbolTable;

public class TypedExitStm implements TypedStm {

	@Override
	public void checkExp(StmNode node, SymbolTable table) {
		if (node instanceof Exit) {
			Exit exit = (Exit) node;
			TypedDocumentStm.checkExp(exit.getDocument(), table);
			return;
		}
		throw new RuntimeException();
		
	}
}
