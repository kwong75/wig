package wig.compiler;

import java.util.Collection;
import java.util.Hashtable;

import wig.node.*;
import wig.analysis.DepthFirstAdapter;
import wig.node.Node;

/**
 * Pretty Printer for the Wig language
 */
@Deprecated
public class PrettyPrinter extends DepthFirstAdapter {

	/* (static) eval function */
	public static String print(Node ast) {
		PrettyPrinter e = new PrettyPrinter();
		ast.apply(e);

		String value = e.getValue(ast);

		return value;
	}

	/* Hashtable, holding intermediate values for AST nodes */
	private Hashtable values = new Hashtable();

	/* Utility method to set values for AST nodes */
	private void setValue(Node node, String value) {
		values.put(node, value);
	}

	/* Utility method to get values for AST nodes */
	private String getValue(Node node) {
		if (node == null) {
			return "";
		}

		String returnValue = (String) values.remove(node);
		return returnValue != null ? returnValue : "";
	}

	/* Utility to handle a list of node */
	private String getValues(Collection<?> nodes) {
		String returnValue = "";

		/* hacky, assume any element of a collection is a node */
		for (Object node : nodes) {
			returnValue = returnValue + getValue((Node) node);
		}

		return returnValue;
	}

	/* AST root (hidden [start = htmls;] production) */
	public void outStart(Start node) {
		setValue(node, getValue(node.getPService()));
	}

	/* service node */
	public void outAService(AService node) {
		setValue(node, node.getService().toString()
				+ node.getLBrace().toString() + '\n' + getValues(node.getHtml())
				+ getValues(node.getSchema()) + getValues(node.getVariable())
				+ getValues(node.getFunction()) + getValues(node.getSession())
				+ node.getRBrace().toString() + '\n' + '\n');
	}

	/* html node */
	public void outAHtml(AHtml node) {
		setValue(
				node,
				node.getConst().toString() + node.getHtml().toString()
						+ node.getIdentifier().toString()
						+ node.getAssign().toString()
						+ node.getHtmlTagStart().toString()
						+ getValues(node.getHtmlbody())
						+ node.getHtmlTagEnd().toString()
						+ node.getSemicolon().toString() + '\n' + '\n');
	}

	/* htmlbody nodes */
	public void outATagStartHtmlbody(ATagStartHtmlbody node) {
		setValue(node,
				node.getLt().toString() + node.getIdentifier().toString()
						+ getValues(node.getAttribute())
						+ node.getGt().toString());
	}

	public void outATagEndHtmlbody(ATagEndHtmlbody node) {
		setValue(node, node.getLtSlash().toString()
				+ node.getIdentifier().toString() + node.getGt().toString());
	}

	public void outAHoleHtmlbody(AHoleHtmlbody node) {
		setValue(node, node.getLtBracket().toString()
				+ node.getIdentifier().toString()
				+ node.getGtBracket().toString());
	}

	public void outAWhateverHtmlbody(AWhateverHtmlbody node) {
		setValue(node, node.getWhatever().toString());
	}

	public void outAMetaHtmlbody(AMetaHtmlbody node) {
		setValue(node, node.getMeta().toString());
	}

	public void outAInputHtmlbody(AInputHtmlbody node) {
		setValue(node, node.getLt().toString() + node.getInput().toString()
				+ getValues(node.getInputattr()) + node.getGt().toString());
	}

	public void outASelectHtmlbody(ASelectHtmlbody node) {
		setValue(node, node.getLt().toString() + node.getSelectTag().toString()
				+ getValues(node.getInputattr()) + node.getFirstGt().toString()
				+ getValues(node.getHtmlbody()) + node.getLtSlash()
				+ node.getSelect().toString() + node.getSecondGt().toString());
	}

	/* inputattr nodes */
	public void outANameInputattr(ANameInputattr node) {
		setValue(node, node.getName().toString() + node.getAssign().toString()
				+ getValue(node.getAttr()));
	}

	public void outATypeInputattr(ATypeInputattr node) {
		setValue(node, node.getType().toString() + node.getAssign().toString()
				+ getValue(node.getInputtype()));
	}

	public void outAAttributeInputattr(AAttributeInputattr node) {
		setValue(node, getValue(node.getAttribute()));
	}

	/* inputtype */
	public void outATextInputtype(ATextInputtype node) {
		setValue(node, node.getText().toString());
	}

	public void outARadioInputtype(ARadioInputtype node) {
		setValue(node, node.getRadio().toString());
	}

	/* attribute nodes */
	public void outAAttrAttribute(AAttrAttribute node) {
		setValue(node, node.getAttr().toString());
	}

	public void outAAssignAttribute(AAssignAttribute node) {
		setValue(node, getValue(node.getLeftAttr())
				+ node.getAssign().toString() + getValue(node.getRightAttr()));
	}

	/* attr nodes */
	public void outAIdAttr(AIdAttr node) {
		setValue(node, node.getIdentifier().toString());
	}

	public void outAIntAttr(AIntAttr node) {
		setValue(node, node.getIntconst().toString());
	}

	public void outAStrAttr(AStrAttr node) {
		setValue(node, node.getStringconst().toString());
	}

	/* schema node */
	public void outASchema(ASchema node) {
		setValue(node, node.getSchema().toString()
				+ node.getIdentifier().toString() + node.getLBrace().toString() + '\n'
				+ getValues(node.getField()) + node.getRBrace().toString() + '\n' + '\n');
	}

	/* field */
	public void outAField(AField node) {
		setValue(node, getValue(node.getSimpletype())
				+ node.getIdentifier().toString()
				+ node.getSemicolon().toString() + '\n');
	}

	/* variable node */
	public void outAVariable(AVariable node) {
		setValue(node,
				getValue(node.getType()) + getValue(node.getIdentifiers())
						+ node.getSemicolon().toString() + '\n');
	}

	/* identifiers nodes */
	public void outAOneIdentifiers(AOneIdentifiers node) {
		setValue(node, node.getIdentifier().toString());
	}

	public void outAManyIdentifiers(AManyIdentifiers node) {
		setValue(node, getValue(node.getIdentifiers())
				+ node.getComma().toString() + node.getIdentifier().toString());
	}

	/* simpletype nodes */
	public void outAIntSimpletype(AIntSimpletype node) {
		setValue(node, node.getInt().toString());
	}

	public void outABoolSimpletype(ABoolSimpletype node) {
		setValue(node, node.getBool().toString());
	}

	public void outAStringSimpletype(AStringSimpletype node) {
		setValue(node, node.getString().toString());
	}

	public void outAVoidSimpletype(AVoidSimpletype node) {
		setValue(node, node.getVoid().toString());
	}

	/* type nodes */
	public void outASimpleType(ASimpleType node) {
		setValue(node, getValue(node.getSimpletype()));
	}

	public void outATupleType(ATupleType node) {
		setValue(node, node.getTuple().toString()
				+ node.getIdentifier().toString());
	}

	/* function node */
	public void outAFunction(AFunction node) {
		setValue(node, getValue(node.getType())
				+ node.getIdentifier().toString() + node.getLPar().toString()
				+ getValue(node.getArguments()) + node.getRPar().toString()
				+ getValue(node.getCompoundstm()));
	}

	/* arguments nodes */
	public void outAOneArguments(AOneArguments node) {
		setValue(node, getValue(node.getArgument()));
	}

	public void outAManyArguments(AManyArguments node) {
		setValue(node, getValue(node.getArguments())
				+ node.getComma().toString() + getValue(node.getArgument()));
	}

	/* argument node */
	public void outAArgument(AArgument node) {
		setValue(node, getValue(node.getType())
				+ node.getIdentifier().toString());
	}

	/* session node */
	public void outASession(ASession node) {
		setValue(node, node.getSession().toString()
				+ node.getIdentifier().toString() + node.getLPar().toString()
				+ node.getRPar().toString() + getValue(node.getCompoundstm()));
	}

	/* stm nodes */
	public void outANoStm(ANoStm node) {
		setValue(node, node.getSemicolon().toString() + '\n');
	}

	public void outAShowStm(AShowStm node) {
		setValue(node, node.getShow().toString() + getValue(node.getDocument())
				+ getValue(node.getReceive()) + node.getSemicolon().toString() + '\n');
	}

	public void outAExitStm(AExitStm node) {
		setValue(node, node.getExit().toString() + getValue(node.getDocument())
				+ node.getSemicolon().toString() + '\n');
	}

	public void outAReturnStm(AReturnStm node) {
		setValue(node, node.getReturn().toString()
				+ node.getSemicolon().toString() + '\n');
	}

	public void outARetexpStm(ARetexpStm node) {
		setValue(node, node.getReturn().toString()
				+ getValue(node.getExp()) +  node.getSemicolon() + '\n');
	}

	public void outAIfStm(AIfStm node) {
		setValue(node, node.getIf().toString() + node.getLPar().toString()
				+ getValue(node.getExp()) + node.getRPar().toString()
				+ getValue(node.getStm()));
	}

	public void outAIfelseStm(AIfelseStm node) {
		setValue(node, node.getIf().toString() + node.getLPar().toString()
				+ getValue(node.getExp()) + node.getRPar().toString()
				+ getValue(node.getThenStm()) + node.getElse().toString()
				+ getValue(node.getElseStm()));
	}

	public void outAWhileStm(AWhileStm node) {
		setValue(node, node.getWhile().toString() + node.getLPar().toString()
				+ getValue(node.getExp()) + node.getRPar().toString()
				+ getValue(node.getStm()));
	}

	public void outACompStm(ACompStm node) {
		setValue(node, getValue(node.getCompoundstm()));
	}

	public void outAExpStm(AExpStm node) {
		setValue(node, getValue(node.getExp()) + node.getSemicolon().toString() + '\n');
	}

	/* stm_no_short_if */
	public void outANoStmNoShortIf(ANoStmNoShortIf node) {
		setValue(node, node.getSemicolon().toString());
	}

	public void outAShowStmNoShortIf(AShowStmNoShortIf node) {
		setValue(node, node.getShow().toString() + getValue(node.getDocument())
				+ getValue(node.getReceive()) + node.getSemicolon().toString() + '\n');
	}

	public void outAExitStmNoShortIf(AExitStmNoShortIf node) {
		setValue(node, node.getExit().toString() + getValue(node.getDocument())
				+ node.getSemicolon().toString() + '\n');
	}

	public void outAReturnStmNoShortIf(AReturnStmNoShortIf node) {
		setValue(node, node.getReturn().toString()
				+ node.getSemicolon().toString() + '\n');
	}

	public void outARetexpStmNoShortIf(ARetexpStmNoShortIf node) {
		setValue(node, getValue(node.getExp()) + node.getReturn().toString()
				+ node.getSemicolon() + '\n');
	}

	public void outAIfElseStmNoShortIf(AIfelseStmNoShortIf node) {
		setValue(node, node.getIf().toString() + node.getLPar().toString()
				+ getValue(node.getExp()) + node.getRPar().toString()
				+ getValue(node.getThenStm()) + node.getElse().toString()
				+ getValue(node.getElseStm()));
	}

	public void outAWhileStmNoShortIf(AWhileStmNoShortIf node) {
		setValue(node, node.getWhile().toString() + node.getLPar().toString()
				+ getValue(node.getExp()) + node.getRPar().toString()
				+ getValue(node.getStmNoShortIf()));
	}

	public void outACompStmNoShortIf(ACompStmNoShortIf node) {
		setValue(node, getValue(node.getCompoundstm()));
	}

	public void outAExpStmNoShortIf(AExpStmNoShortIf node) {
		setValue(node, getValue(node.getExp()) + node.getSemicolon().toString() + '\n');
	}

	/* document */
	public void outAIdDocument(AIdDocument node) {
		setValue(node, node.getIdentifier().toString());
	}

	public void outAPlugDocument(APlugDocument node) {
		setValue(node, node.getPlug().toString() 
				+ node.getIdentifier().toString()
				+ node.getLBracket().toString() + getValue(node.getPlugs())
				+ node.getRBracket());
	}

	/* receive */
	public void outAReceive(AReceive node) {
		setValue(node, node.getReceive().toString()
				+ node.getLBracket().toString() + getValue(node.getInputs())
				+ node.getRBracket().toString());
	}

	/* compoundstm */
	public void outACompoundstm(ACompoundstm node) {
		setValue(node,
				node.getLBrace().toString() + '\n' + getValues(node.getVariable())
						+ getValues(node.getStm())
						+ node.getRBrace().toString() + '\n' + '\n');
	}

	/* plugs nodes */
	public void outAOnePlugs(AOnePlugs node) {
		setValue(node, getValue(node.getPlug()));
	}

	public void outAManyPlugs(AManyPlugs node) {
		setValue(node, getValue(node.getPlugs()) + node.getComma().toString()
				+ getValue(node.getPlug()));
	}

	/* plug node */
	public void outAPlug(APlug node) {
		setValue(node, node.getIdentifier().toString()
				+ node.getAssign().toString() + getValue(node.getExp()));
	}

	/* inputs nodes */
	public void outAOneInputs(AOneInputs node) {
		setValue(node, getValue(node.getInput()));
	}

	public void outAManyInputs(AManyInputs node) {
		setValue(node, getValue(node.getInputs()) + node.getComma().toString()
				+ getValue(node.getInput()));
	}

	/* input node */
	public void outAInput(AInput node) {
		setValue(node, getValue(node.getLvalue()) + node.getAssign().toString()
				+ node.getIdentifier().toString());
	}

	/* exp */
	public void outAAssignExp(AAssignExp node) {
		setValue(node, getValue(node.getLvalue()) + node.getAssign().toString()
				+ getValue(node.getOrExp()));//
	}

	public void outADefaultExp(ADefaultExp node) {
		setValue(node, getValue(node.getOrExp()));
	}

	/* orexp */
	public void outAOrOrExp(AOrOrExp node) {
		setValue(node, getValue(node.getLeft()) + node.getOr().toString()
				+ getValue(node.getRight()));
	}

	public void outADefaultOrExp(ADefaultOrExp node) {
		setValue(node, getValue(node.getAndExp()));
	}

	/* andexp */

	public void outAAndAndExp(AAndAndExp node) {
		setValue(node, getValue(node.getLeft()) + node.getAnd().toString()
				+ getValue(node.getRight()));
	}

	public void outADefaultAndExp(ADefaultAndExp node) {
		setValue(node, getValue(node.getCmpExp()));
	}

	/* cmp_exp */
	public void outAEqCmpExp(AEqCmpExp node) {
		setValue(node, getValue(node.getLeft()) + node.getEq().toString()
				+ getValue(node.getRight()));
	}

	public void outANeqCmpExp(ANeqCmpExp node) {
		setValue(node, getValue(node.getLeft()) + node.getNeq().toString()
				+ getValue(node.getRight()));
	}

	public void outALtCmpExp(ALtCmpExp node) {
		setValue(node, getValue(node.getLeft()) + node.getLt().toString()
				+ getValue(node.getRight()));
	}

	public void outAGtCmpExp(AGtCmpExp node) {
		setValue(node, getValue(node.getLeft()) + node.getGt().toString()
				+ getValue(node.getRight()));
	}

	public void outALteqCmpExp(ALteqCmpExp node) {
		setValue(node, getValue(node.getLeft()) + node.getLteq().toString()
				+ getValue(node.getRight()));
	}

	public void outAGteqCmpExp(AGteqCmpExp node) {
		setValue(node, getValue(node.getLeft()) + node.getGteq().toString()
				+ getValue(node.getRight()));
	}

	public void outADefaultCmpExp(ADefaultCmpExp node) {
		setValue(node, getValue(node.getAddExp()));
	}

	/* addexp nodes */
	public void outAPlusAddExp(APlusAddExp node) {
		setValue(node, getValue(node.getLeft()) + node.getPlus().toString()
				+ getValue(node.getRight()));
	}

	public void outAMinusAddExp(AMinusAddExp node) {
		setValue(node, getValue(node.getLeft()) + node.getMinus().toString()
				+ getValue(node.getRight()));
	}

	public void outADefaultAddExp(ADefaultAddExp node) {
		setValue(node, getValue(node.getMultExp()));
	}

	/* multexp */
	public void outAMultMultExp(AMultMultExp node) {
		setValue(node, getValue(node.getLeft()) + node.getMult().toString()
				+ getValue(node.getRight()));
	}

	public void outADivMultExp(ADivMultExp node) {
		setValue(node, getValue(node.getLeft()) + node.getDiv().toString()
				+ getValue(node.getRight()));
	}

	public void outAModMultExp(AModMultExp node) {
		setValue(node, getValue(node.getLeft()) + node.getMod().toString()
				+ getValue(node.getRight()));
	}

	public void outADefaultMultExp(ADefaultMultExp node) {
		setValue(node, getValue(node.getJoinExp()));
	}

	/* joinexp nodes */
	public void outAJoinJoinExp(AJoinJoinExp node) {
		setValue(node, getValue(node.getLeft()) + node.getJoin().toString()
				+ getValue(node.getRight()));
	}

	public void outADefaultJoinExp(ADefaultJoinExp node) {
		setValue(node, getValue(node.getTupleExp()));
	}

	/* tupleexp nodes */
	public void outAKeepTupleExp(AKeepTupleExp node) {
		setValue(node, getValue(node.getTupleExp()) + node.getKeep().toString()
				+ node.getIdentifier().toString());
	}

	public void outARemoveTupleExp(ARemoveTupleExp node) {
		setValue(node, getValue(node.getTupleExp())
				+ node.getRemove().toString() + node.getIdentifier().toString());
	}

	public void outAKeepManyTupleExp(AKeepManyTupleExp node) {
		setValue(node, getValue(node.getTupleExp()) + node.getKeep().toString()
				+ node.getLPar().toString() + getValue(node.getIdentifiers())
				+ node.getRPar().toString());
	}

	public void outARemoveManyTupleExp(ARemoveManyTupleExp node) {
		setValue(node, getValue(node.getTupleExp())
				+ node.getRemove().toString() + node.getLPar().toString()
				+ getValue(node.getIdentifiers()) + node.getRPar().toString());
	}

	public void outADefaultTupleExp(ADefaultTupleExp node) {
		setValue(node, getValue(node.getUnaryExp()));
	}

	/* unaryexp */
	public void outANotUnaryExp(ANotUnaryExp node) {
		setValue(node, node.getNot().toString() + getValue(node.getBaseExp()));
	}

	public void outANegUnaryExp(ANegUnaryExp node) {
		setValue(node, node.getMinus().toString() + getValue(node.getBaseExp()));
	}

	public void outADefaultUnaryExp(ADefaultUnaryExp node) {
		setValue(node, getValue(node.getBaseExp()));
	}

	/* baseexp */
	public void outALvalueBaseExp(ALvalueBaseExp node) {
		setValue(node, getValue(node.getLvalue()));//getValue(node.getLvalue()));
	}

	public void outACallBaseExp(ACallBaseExp node) {
		setValue(node, node.getIdentifier().toString()
				+ node.getLPar().toString() + getValue(node.getExps())
				+ node.getRPar().toString());
	}

	public void outAIntBaseExp(AIntBaseExp node) {
		setValue(node, node.getIntconst().toString());
	}

	public void outATrueBaseExp(ATrueBaseExp node) {
		setValue(node, node.getTrue().toString());
	}

	public void outAFalseBaseExp(AFalseBaseExp node) {
		setValue(node, node.getFalse().toString());
	}

	public void outAStringBaseExp(AStringBaseExp node) {
		setValue(node, node.getStringconst().toString());
	}

	public void outATupleBaseExp(ATupleBaseExp node) {
		setValue(node, node.getTuple().toString() + node.getLBrace().toString()
				+ getValue(node.getFieldvalues()) + node.getRBrace());
	}

	public void outAParenBaseExp(AParenBaseExp node) {
		setValue(node, node.getLPar().toString() + getValue(node.getExp())
				+ node.getRPar().toString());
	}

	/* exps node */
	public void outAOneExps(AOneExps node) {
		setValue(node, getValue(node.getExp()));
	}

	public void outAManyExps(AManyExps node) {
		setValue(node, getValue(node.getExps()) + node.getComma().toString()
				+ getValue(node.getExp()));
	}

	/* lvalue node */
	public void outASimpleLvalue(ASimpleLvalue node) {
		setValue(node, node.getIdentifier().toString());
	}

	public void outAQualifiedLvalue(AQualifiedLvalue node) {
		setValue(node, node.getLeft().toString() + node.getDot().toString()
				+ node.getRight());
	}

	/* fieldvalues node */
	public void outAOneFieldvalues(AOneFieldvalues node) {
		setValue(node, getValue(node.getFieldvalue()));
	}

	public void outAManyFieldvalues(AManyFieldvalues node) {
		setValue(node, getValue(node.getFieldvalues())
				+ node.getComma().toString() + getValue(node.getFieldvalue()));
	}

	/* fieldvalue node */
	public void outAFieldvalue(AFieldvalue node) {
		setValue(node, node.getIdentifier().toString()
				+ node.getAssign().toString() + getValue(node.getExp()));
	}
}
