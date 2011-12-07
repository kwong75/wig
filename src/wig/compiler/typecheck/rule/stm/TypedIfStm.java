package wig.compiler.typecheck.rule.stm;

import wig.compiler.ast.stm.If;
import wig.compiler.ast.stm.StmNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.rule.exp.TypedExpressionNode;

public class TypedIfStm implements TypedStm {

	@Override
	public void checkExp(StmNode node, SymbolTable table) {
		if (node instanceof If) {
			If if1 = (If) node;
			TypedExpressionNode exp = new TypedExpressionNode();
			exp.checkExp(if1.getExp(), table);
			if (!"bool".contentEquals(exp.getType().getPrimitiveType())) {
				System.err.print("Expression inside if was not bool for " + if1.toString());
				throw new RuntimeException();
			}
			TypeStmRunner.checkExp(((If) node).getStm(), table);
			TypeStmRunner.checkExp(if1.getElseStm(), table);
		}
		
	}

}
