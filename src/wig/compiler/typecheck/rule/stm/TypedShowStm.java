package wig.compiler.typecheck.rule.stm;

import wig.compiler.ast.stm.Show;
import wig.compiler.ast.stm.StmNode;
import wig.compiler.ast.stm.html.Input;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.exp.TypedExpressionNode;

public class TypedShowStm implements TypedStm {

	@Override
	public void checkExp(StmNode node, SymbolTable table) {
		if (node instanceof Show) {
			Show show = (Show) node;
			TypedDocumentStm.checkExp(show.getDocument(), table);
			if (show.getReceive() != null) {
				for (final Input input : ((Show) node).getReceive().getInputs()) {
					final TypedExpressionNode exp = new TypedExpressionNode();
					exp.checkExp(input.getLvalue(), table);
					if (!exp.getType().getIsPrimitive()) {
						System.err.println(
								"Attempting to plug a non Primitive value " + node.toString());
						throw new RuntimeException();
					}
				}
			}
			return;
		}
		throw new RuntimeException();
	}

}
