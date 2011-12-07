package wig.compiler.typecheck.rule.stm;

import wig.compiler.ast.stm.Return;
import wig.compiler.ast.stm.StmNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.typecheck.ExpressionType;
import wig.compiler.typecheck.rule.exp.TypedExpressionNode;

public class TypedReturnStm implements TypedStm {
	private ExpressionType expected;
	
	public TypedReturnStm(final ExpressionType expected) {
		this.expected = expected;
	}
	
	@Override
	public void checkExp(StmNode node, SymbolTable table) {
		if (node instanceof Return) {
			Return return1 = (Return) node;
			if (return1.getExp() == null && "void".contentEquals(expected.getPrimitiveType())) {
				return;
			} else {
				final TypedExpressionNode exp = new TypedExpressionNode();
				if (return1.getExp() != null) {
					exp.checkExp(return1.getExp(), table);
					if (exp.getType().compareTo(expected)) {
						return;
					}
				} else {
					if ("void".contentEquals(expected.getPrimitiveType())) {
						return;
					}
				}
				
			}
		}
		System.err.println("Function has return of wrong type for " + node.toString());
		throw new RuntimeException();
	}

}
