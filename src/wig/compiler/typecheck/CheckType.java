package wig.compiler.typecheck;

import wig.compiler.ast.ServiceNode;
import wig.compiler.ast.function.FunctionNode;
import wig.compiler.ast.session.SessionNode;
import wig.compiler.ast.stm.StmNode;
import wig.compiler.symbolkind.Schema;
import wig.compiler.typecheck.rule.stm.TypeStmRunner;

public class CheckType {

	public static void run(final ServiceNode node) throws Exception {
		try {
			for (final FunctionNode function : node.getFunctions()) {
				final ExpressionType expected = new ExpressionType(
						node.getSymbolTable());
				if (expected.isPrimitive(function.getType().getType())) {
					expected.setPrimitiveType(function.getType().getType());
				} else {
					Schema schema = (Schema) node
							.getSymbolTable()
							.getSymbol(function.getType().getType(),
									Schema.class).getKind();
					expected.addFieldTypeAll(schema.getFields());
				}
				TypeStmRunner.expected = expected;
				for (StmNode stm : function.getCompoundStm().getStms()) {
					TypeStmRunner.checkExp(stm, function.getSymbolTable());
				}
			}
			TypeStmRunner.expected = null;
			for (final SessionNode session : node.getSessions()) {
				for (StmNode stm : session.getCompoundStm().getStms()) {
					TypeStmRunner.checkExp(stm, session.getCompoundStm()
							.getSymbolTable());
				}
			}
		} catch (Exception e) {
			System.out.println("Typed Check Failed");
			throw e;
		}
	}
}
