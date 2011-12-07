package wig.compiler.typecheck.rule.stm;

import wig.compiler.ast.stm.StmNode;
import wig.compiler.ast.stm.While;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.exp.TypedExpressionNode;

public class TypedWhileStm implements TypedStm {

	@Override
	public void checkExp(StmNode node, SymbolTable table) {
		if (node instanceof While) {
			While while1 = (While) node;
			TypedExpressionNode exp = new TypedExpressionNode();
			exp.checkExp(while1.getExp(), table);
			if (!"bool".contentEquals(exp.getType().getPrimitiveType())) {
				System.err.println("Expression inside if was not bool for " + while1.toString());
				throw new RuntimeException();
			}
			TypeStmRunner.checkExp(((While) node).getStm(), table);
		}
		
	}

}
