package wig.compiler.symbol.builder;

import wig.compiler.ast.exp.AssignExpressionNode;
import wig.compiler.ast.exp.BaseExp;
import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.LRExpressionNode;
import wig.compiler.ast.exp.LValueNode;
import wig.compiler.ast.exp.impl.ParenExpression;
import wig.compiler.ast.exp.impl.TupleExpression;
import wig.compiler.ast.exp.value.FunctionCall;
import wig.compiler.ast.stm.CompoundStm;
import wig.compiler.ast.stm.Exit;
import wig.compiler.ast.stm.Expression;
import wig.compiler.ast.stm.If;
import wig.compiler.ast.stm.Return;
import wig.compiler.ast.stm.Show;
import wig.compiler.ast.stm.StmNode;
import wig.compiler.ast.stm.While;
import wig.compiler.ast.stm.html.Document;
import wig.compiler.ast.stm.html.Input;
import wig.compiler.ast.stm.html.Plug;
import wig.compiler.ast.stm.html.Receive;
import wig.compiler.ast.variable.VariableNode;
import wig.compiler.symbol.Symbol;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.symbolkind.Field;
import wig.compiler.symbolkind.Function;
import wig.compiler.symbolkind.Schema;
import wig.compiler.symbolkind.Variable;
import wig.compiler.symbolkind.html.Html;
import wig.compiler.symbolkind.html.HtmlHole;
import wig.compiler.symbolkind.html.HtmlInput;

public class CompoundStmSymbolTable {

	public static void buildCompoundStmSymbolTable(final SymbolTable parent,
			final CompoundStm node, final Boolean display) {
		final SymbolTable table = new SymbolTable();
		node.setSymbolTable(table);
		table.scopeSymbolTable(parent);
		for (final VariableNode variable : node.getVariables()) {
			for (final String id : variable.getIdentifiers()) {
				final Variable kind = new Variable();
				kind.setName(id);
				kind.setType(table, variable.getType().getType());
				table.putSymbol(kind.getName(), kind);
				if (display)
					System.out.println(kind.toString());
			}
		}
		for (StmNode stm : node.getStms()) {
			checkStmSymbolTable(table, stm, display);
		}
	}

	public static void checkStmSymbolTable(final SymbolTable parent,
			final StmNode node, final Boolean display) {
		if (node instanceof Show) {
			final Show show = (Show) node;
			Html html = checkDocumentSymbolTable(parent, show.getDocument(),
					display);
			if (show.getReceive() != null) {
				checkReceiveSymbolTable(html, parent, show.getReceive(),
						display);
			}
		} else if (node instanceof Exit) {
			final Exit exit = (Exit) node;
			checkDocumentSymbolTable(parent, exit.getDocument(), display);
		} else if (node instanceof Return) {
			checkExpSymbolTable(parent, ((Return) node).getExp(), display);
		} else if (node instanceof If) {
			// Not scoping because we can assume that Variables can only be
			// declared inside compound statement. So, no variable can be
			// declared outside a non brace brackets
			checkExpSymbolTable(parent, ((If) node).getExp(), display);
			// final SymbolTable thenTable = new SymbolTable();
			// ((If) node).setThenTable(thenTable);
			// thenTable.scopeSymbolTable(parent);
			checkStmSymbolTable(parent, ((If) node).getStm(), display);
			if (((If) node).getElseStm() != null) {
				// final SymbolTable elseTable = new SymbolTable();
				// ((If) node).setThenTable(elseTable);
				// elseTable.scopeSymbolTable(parent);
				checkStmSymbolTable(parent, ((If) node).getElseStm(), display);
			}
		} else if (node instanceof While) {
			checkExpSymbolTable(parent, ((While) node).getExp(), display);
			// Same reasoning as above
			// SymbolTable table = new SymbolTable();
			// table.scopeSymbolTable(parent);
			checkStmSymbolTable(parent, ((While) node).getStm(), display);
		} else if (node instanceof CompoundStm) {
			buildCompoundStmSymbolTable(parent, (CompoundStm) node, display);
		} else if (node instanceof Expression) {
			checkExpSymbolTable(parent, ((Expression) node).getExp(), display);
		}
	}

	/* Has a return type because this html should build both plug and receive */
	public static Html checkDocumentSymbolTable(final SymbolTable parent,
			final Document node, final Boolean display) {
		Symbol htmlSymbol = parent.getSymbol(node.getIdentifier(), Html.class);
		if (htmlSymbol == null) {
			throw new RuntimeException(
					"Failed to find html, attempting to find: "
							+ node.getIdentifier());
		}
		// Analysis to make sure the parameter is proper
		Html htmlSymbolKind = (Html) htmlSymbol.getKind();
		for (final Plug plug : node.getPlugs()) {
			if (!htmlSymbolKind.validPlug(plug.getIdentifier())) {
				String exception = "Failed to find plug: "
						+ plug.getIdentifier() + " from list: ";
				for (final HtmlHole listItem : htmlSymbolKind.getPlugs()) {
					exception = exception + listItem.getName() + " ";
				}
				throw new RuntimeException(exception);
			}
		}
		return htmlSymbolKind;
	}

	private static void checkReceiveSymbolTable(final Html html,
			final SymbolTable parent, final Receive node, final Boolean display) {
		// Analysis to make sure the parameter is proper
		for (final Input input : node.getInputs()) {
			if (!html.validReceive(input.getIdentifier())) {
				String exception = "Failed to find receive: "
						+ input.getIdentifier() + " from list: ";
				for (final HtmlInput listItem : html.getReceives()) {
					exception = exception + listItem.getName() + " ";
				}
				throw new RuntimeException(exception);
			}

		}
		// Analysis to LValue of node
		for (final Input input : node.getInputs()) {
			checkLvalueSymbolTable(parent, input.getLvalue(), display);
		}
	}

	public static void checkExpSymbolTable(final SymbolTable table,
			final ExpressionNode exp, final Boolean display) {
		if (exp instanceof AssignExpressionNode) {
			checkLvalueSymbolTable(table,
					((AssignExpressionNode) exp).getLvalue(), display);
			checkExpSymbolTable(table, ((AssignExpressionNode) exp).getValue(),
					display);
		} else if (exp instanceof BaseExp) {
			checkBaseExpSymbolTable(table, (BaseExp) exp, display);
		} else if (exp instanceof LRExpressionNode) {
			checkLRExpressionNode(table, (LRExpressionNode) exp, display);
		} else if (exp instanceof LValueNode) {
			checkLvalueSymbolTable(table, (LValueNode) exp, display);
		} else if (exp instanceof TupleExpression) {
			checkTupleExpression(table, (TupleExpression) exp, display);
		}
	}

	// Tuple Value will be check in type check
	private static void checkBaseExpSymbolTable(final SymbolTable table,
			final BaseExp exp, final Boolean display) {
		if (exp instanceof FunctionCall) {
			final FunctionCall functionCall = (FunctionCall) exp;
			final Symbol symbol = table.getSymbol(functionCall.getIdentifier(),
					Function.class);
			if (symbol == null) {
				throw new RuntimeException(
						"Failed to find function, attempting to find: "
								+ functionCall.getIdentifier());
			}
		} else if (exp instanceof ParenExpression) {
			checkExpSymbolTable(table, ((ParenExpression) exp).getExp(),
					display);
		} // Will not check Value
	}

	private static void checkLRExpressionNode(final SymbolTable table,
			final LRExpressionNode exp, final Boolean display) {
		checkExpSymbolTable(table, exp.getLeft(), display);
		checkExpSymbolTable(table, exp.getRight(), display);
	}

	private static void checkLvalueSymbolTable(final SymbolTable table,
			final LValueNode lvalue, final Boolean display) {
		if (lvalue.getIdentifiers().size() == 1) {
			if (table.getSymbol(lvalue.getIdentifiers().get(0), Variable.class) != null) {
				return;
			}
		}
		if (lvalue.getIdentifiers().size() == 2) {
			try {
				Variable schemaVariable = (Variable) table.getSymbol(
						lvalue.getIdentifiers().get(0), Variable.class)
						.getKind();
				Schema schema = (Schema) table.getSymbol(
						schemaVariable.getType(), Schema.class).getKind();

				if (schema != null) {
					for (final Field field : schema.getFields()) {
						if (field.getName().contentEquals(
								lvalue.getIdentifiers().get(1))) {
							return;
						}
					}
				}
			} catch (ClassCastException e) {
				throw new RuntimeException(
						"Expected result to be variable of a Schema type");
			}
		}
		throw new RuntimeException("Failed to find symbol: "
				+ lvalue.toString());
	}

	// TODO: Handle properly
	private static void checkTupleExpression(final SymbolTable table,
			final TupleExpression exp, final Boolean display) {
		checkExpSymbolTable(table, exp.getLeft(), display);
	}
}
