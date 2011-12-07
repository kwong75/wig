package wig.compiler.typecheck.rule.stm;

import wig.compiler.ast.stm.StmNode;
import wig.compiler.symbol.SymbolTable;

public interface TypedStm {
	public void checkExp(final StmNode node, final SymbolTable table);
}
