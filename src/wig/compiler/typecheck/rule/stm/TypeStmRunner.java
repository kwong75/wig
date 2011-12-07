package wig.compiler.typecheck.rule.stm;

import wig.compiler.ast.stm.CompoundStm;
import wig.compiler.ast.stm.Exit;
import wig.compiler.ast.stm.Expression;
import wig.compiler.ast.stm.If;
import wig.compiler.ast.stm.Return;
import wig.compiler.ast.stm.Show;
import wig.compiler.ast.stm.StmNode;
import wig.compiler.ast.stm.While;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.ExpressionType;
import wig.compiler.typecheck.rule.exp.TypedExpressionNode;

public class TypeStmRunner {
	//Hacky
	public static ExpressionType expected;
	
	public static void checkExp(StmNode node, SymbolTable table) {
		if (node == null) {
			return;
		}
		if (node instanceof Show) {
			final TypedShowStm stm = new TypedShowStm();
			stm.checkExp(node, table);
		} else if (node instanceof Exit) {
			final TypedExitStm stm = new TypedExitStm();
			stm.checkExp(node, table);
		} else if (node instanceof If) {
			final TypedIfStm stm = new TypedIfStm();
			stm.checkExp(node, table);
		} else if (node instanceof Return) {
			final TypedReturnStm stm = new TypedReturnStm(expected);
			stm.checkExp(node, table);
		} else if (node instanceof While) {
			final TypedWhileStm stm = new TypedWhileStm();
			stm.checkExp(node, table);
		} else if (node instanceof CompoundStm) {
			for (StmNode stm : ((CompoundStm) node).getStms()) {
				checkExp(stm, ((CompoundStm) node).getSymbolTable());
			}
		} else if (node instanceof Expression) {
			final TypedExpressionNode type = new TypedExpressionNode();
			type.checkExp(((Expression) node).getExp(), table);
		}
		else {
			//Do nothing
		}
	}
}
