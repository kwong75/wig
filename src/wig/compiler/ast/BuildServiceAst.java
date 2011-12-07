package wig.compiler.ast;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import wig.node.*;
import wig.analysis.DepthFirstAdapter;
import wig.compiler.ast.exp.AssignExpressionNode;
import wig.compiler.ast.exp.BaseExp;
import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.LValueNode;
import wig.compiler.ast.exp.impl.AdditionExpression;
import wig.compiler.ast.exp.impl.AndExpression;
import wig.compiler.ast.exp.impl.CompareExpression;
import wig.compiler.ast.exp.impl.JoinExpression;
import wig.compiler.ast.exp.impl.MultiplyExpression;
import wig.compiler.ast.exp.impl.OrExpression;
import wig.compiler.ast.exp.impl.ParenExpression;
import wig.compiler.ast.exp.impl.TupleExpression;
import wig.compiler.ast.exp.value.BooleanValue;
import wig.compiler.ast.exp.value.FieldValueNode;
import wig.compiler.ast.exp.value.FunctionCall;
import wig.compiler.ast.exp.value.IntegerValue;
import wig.compiler.ast.exp.value.StringValue;
import wig.compiler.ast.exp.value.TupleValue;
import wig.compiler.ast.function.Argument;
import wig.compiler.ast.function.FunctionNode;
import wig.compiler.ast.html.Attribute;
import wig.compiler.ast.html.Hole;
import wig.compiler.ast.html.HtmlBodyNode;
import wig.compiler.ast.html.HtmlNode;
import wig.compiler.ast.html.Input;
import wig.compiler.ast.html.Meta;
import wig.compiler.ast.html.Select;
import wig.compiler.ast.html.TagEnd;
import wig.compiler.ast.html.TagStart;
import wig.compiler.ast.html.Whatever;
import wig.compiler.ast.schema.FieldValue;
import wig.compiler.ast.schema.SchemaNode;
import wig.compiler.ast.session.SessionNode;
import wig.compiler.ast.stm.CompoundStm;
import wig.compiler.ast.stm.Exit;
import wig.compiler.ast.stm.Expression;
import wig.compiler.ast.stm.If;
import wig.compiler.ast.stm.Noop;
import wig.compiler.ast.stm.Return;
import wig.compiler.ast.stm.Show;
import wig.compiler.ast.stm.StmNode;
import wig.compiler.ast.stm.While;
import wig.compiler.ast.stm.html.Document;
import wig.compiler.ast.stm.html.Plug;
import wig.compiler.ast.stm.html.Receive;
import wig.compiler.ast.type.PrimitiveType;
import wig.compiler.ast.type.TupleType;
import wig.compiler.ast.type.Type;
import wig.compiler.ast.variable.VariableNode;
import wig.node.Node;

/**
 * AST generator design for the Wig language
 */
@SuppressWarnings("unchecked")
public class BuildServiceAst extends DepthFirstAdapter {

	/* (static) eval function */
	public static ServiceNode run(Node ast) {
		BuildServiceAst e = new BuildServiceAst();
		ast.apply(e);
		ServiceNode head = (ServiceNode) e.getNode(ast);
		return head;
	}

	/* Hashtable, holding intermediate values for AST nodes */
	private Hashtable values = new Hashtable();

	/* Utility method to set values for AST nodes */
	private void createNode(Node node, Object putNode) {
		values.put(node, putNode);
	}

	/* Utility method to get values for AST nodes */
	private Object getNode(Node node) {
		return values.remove(node);
	}

	/* AST root (hidden [start = htmls;] production) */
	public void outStart(Start node) {
		createNode(node, getNode(node.getPService()));
	}

	private String cleanString(Token node) {
		return node.toString().replaceAll(" ", "");
	}

	/* service node */
	public void outAService(AService node) {
		ServiceNode putNode = new ServiceNode();
		for (final Object n : node.getHtml()) {
			putNode.addHtml((HtmlNode) getNode((PHtml) n));
		}
		for (final Object n : node.getSchema()) {
			putNode.addSchema((SchemaNode) getNode((PSchema) n));
		}
		for (final Object n : node.getVariable()) {
			putNode.addVariable((VariableNode) getNode((PVariable) n));
		}
		for (final Object n : node.getFunction()) {
			putNode.addFunction((FunctionNode) getNode((PFunction) n));
		}
		for (final Object n : node.getSession()) {
			putNode.addSession((SessionNode) getNode((PSession) n));
		}
		createNode(node, putNode);
	}

	/* html node */
	public void outAHtml(AHtml node) {
		final HtmlNode putNode = new HtmlNode();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		for (final Object n : node.getHtmlbody()) {
			putNode.addNode((HtmlBodyNode) getNode((PHtmlbody) n));
		}
		createNode(node, putNode);
	}

	/* htmlbody nodes */
	public void outATagStartHtmlbody(ATagStartHtmlbody node) {
		final TagStart putNode = new TagStart();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		for (final Object n : node.getAttribute()) {
			putNode.addAttribute((Attribute) getNode((PAttribute) n));
		}
		createNode(node, putNode);
	}

	public void outATagEndHtmlbody(ATagEndHtmlbody node) {
		final TagEnd putNode = new TagEnd();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	public void outAHoleHtmlbody(AHoleHtmlbody node) {
		final Hole putNode = new Hole();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	public void outAWhateverHtmlbody(AWhateverHtmlbody node) {
		final Whatever putNode = new Whatever();
		putNode.setWhatever(node.getWhatever().toString());
		createNode(node, putNode);
	}

	public void outAMetaHtmlbody(AMetaHtmlbody node) {
		final Meta putNode = new Meta();
		putNode.setMeta(node.getMeta().toString());
		createNode(node, putNode);
	}

	public void outAInputHtmlbody(AInputHtmlbody node) {
		final Input putNode = new Input();
		for (final Object n : node.getInputattr()) {
			putNode.addAttribute((Attribute) getNode((PInputattr) n));
		}
		createNode(node, putNode);
	}

	public void outASelectHtmlbody(ASelectHtmlbody node) {
		final Select putNode = new Select();
		for (final Object n : node.getInputattr()) {
			putNode.addAttribute((Attribute) getNode((PInputattr) n));
		}
		for (final Object n : node.getHtmlbody()) {
			putNode.addNode((HtmlBodyNode) getNode((PHtmlbody) n));
		}
		createNode(node, putNode);
	}

	/* inputattr nodes */
	public void outANameInputattr(ANameInputattr node) {
		final Attribute putNode = new Attribute();
		putNode.setLeft(cleanString(node.getName()));
		putNode.setRight((String) getNode(node.getAttr()));
		createNode(node, putNode);
	}

	public void outATypeInputattr(ATypeInputattr node) {
		final Attribute putNode = new Attribute();
		putNode.setLeft(cleanString(node.getType()));
		putNode.setRight((String) getNode(node.getInputtype()));
		createNode(node, putNode);
	}

	public void outAAttributeInputattr(AAttributeInputattr node) {
		createNode(node, getNode(node.getAttribute()));
	}

	/* inputtype */
	public void outATextInputtype(ATextInputtype node) {
		createNode(node, cleanString(node.getText()));
	}

	public void outARadioInputtype(ARadioInputtype node) {
		createNode(node, cleanString(node.getRadio()));
	}

	/* attribute nodes */
	public void outAAttrAttribute(AAttrAttribute node) {
		final Attribute putNode = new Attribute();
		putNode.setLeft((String) getNode(node.getAttr()));
		createNode(node, putNode);
	}

	public void outAAssignAttribute(AAssignAttribute node) {
		final Attribute putNode = new Attribute();
		putNode.setLeft((String) getNode(node.getLeftAttr()));
		putNode.setRight((String) getNode(node.getRightAttr()));
		createNode(node, putNode);
	}

	/* attr nodes */
	public void outAIdAttr(AIdAttr node) {
		createNode(node, node.getIdentifier().toString());
	}

	public void outAIntAttr(AIntAttr node) {
		createNode(node, node.getIntconst().toString());
	}

	public void outAStrAttr(AStrAttr node) {
		createNode(node, node.getStringconst().toString());
	}

	/* schema node */
	public void outASchema(ASchema node) {
		final SchemaNode putNode = new SchemaNode();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		for (Object n : node.getField()) {
			putNode.addField((FieldValue) getNode((PField) n));
		}
		createNode(node, putNode);
	}

	/* field */
	public void outAField(AField node) {
		final FieldValue putNode = new FieldValue();
		putNode.setType((PrimitiveType) getNode(node.getSimpletype()));
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	/* variable node */
	public void outAVariable(AVariable node) {
		final VariableNode putNode = new VariableNode();
		putNode.setType((Type) getNode(node.getType()));
		putNode.getIdentifiers().addAll(
				(List<String>) getNode(node.getIdentifiers()));
		createNode(node, putNode);
	}

	/* identifiers nodes */
	public void outAOneIdentifiers(AOneIdentifiers node) {
		final List<String> putNode = new ArrayList<String>();
		putNode.add(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	public void outAManyIdentifiers(AManyIdentifiers node) {
		final List<String> putNode = new ArrayList<String>();
		putNode.addAll((List<String>) getNode(node.getIdentifiers()));
		putNode.add(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	/* simpletype nodes */
	public void outAIntSimpletype(AIntSimpletype node) {
		final PrimitiveType putNode = new PrimitiveType();
		putNode.setSign(putNode.getSIGN(cleanString(node.getInt())));
		createNode(node, putNode);
	}

	public void outABoolSimpletype(ABoolSimpletype node) {
		final PrimitiveType putNode = new PrimitiveType();
		putNode.setSign(putNode.getSIGN(cleanString(node.getBool())));
		createNode(node, putNode);
	}

	public void outAStringSimpletype(AStringSimpletype node) {
		final PrimitiveType putNode = new PrimitiveType();
		putNode.setSign(putNode.getSIGN(cleanString(node.getString())));
		createNode(node, putNode);
	}

	public void outAVoidSimpletype(AVoidSimpletype node) {
		final PrimitiveType putNode = new PrimitiveType();
		putNode.setSign(putNode.getSIGN(cleanString(node.getVoid())));
		createNode(node, putNode);
	}

	/* type nodes */
	public void outASimpleType(ASimpleType node) {
		createNode(node, getNode(node.getSimpletype()));
	}

	public void outATupleType(ATupleType node) {
		final TupleType putNode = new TupleType();
		putNode.setType(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	/* function node */
	public void outAFunction(AFunction node) {
		final FunctionNode putNode = new FunctionNode();
		putNode.setType((Type) getNode(node.getType()));
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		if (node.getArguments() != null) {
			putNode.getArguments().addAll(
					(List<Argument>) getNode(node.getArguments()));
		}
		putNode.setCompoundStm((CompoundStm) getNode(node.getCompoundstm()));
		createNode(node, putNode);
	}

	/* arguments nodes */
	public void outAOneArguments(AOneArguments node) {
		final List<Argument> putNode = new ArrayList<Argument>();
		putNode.add((Argument) getNode(node.getArgument()));
		createNode(node, putNode);
	}

	public void outAManyArguments(AManyArguments node) {
		final List<Argument> putNode = new ArrayList<Argument>();
		putNode.addAll((List<Argument>) getNode(node.getArguments()));
		putNode.add((Argument) getNode(node.getArgument()));
		createNode(node, putNode);
	}

	/* argument node */
	public void outAArgument(AArgument node) {
		final Argument putNode = new Argument();
		putNode.setType((Type) getNode(node.getType()));
		putNode.setId(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	/* session node */
	public void outASession(ASession node) {
		final SessionNode putNode = new SessionNode();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		putNode.setCompoundStm((CompoundStm) getNode(node.getCompoundstm()));
		createNode(node, putNode);
	}

	/* stm nodes */
	public void outANoStm(ANoStm node) {
		final Noop putNode = new Noop();
		createNode(node, putNode);
	}

	public void outAShowStm(AShowStm node) {
		final Show putNode = new Show();
		putNode.setDocument((Document) getNode(node.getDocument()));
		if (node.getReceive() != null) {
			putNode.setReceive((Receive) getNode(node.getReceive()));
		}
		createNode(node, putNode);
	}

	public void outAExitStm(AExitStm node) {
		final Exit putNode = new Exit();
		putNode.setDocument((Document) getNode(node.getDocument()));
		createNode(node, putNode);
	}

	public void outAReturnStm(AReturnStm node) {
		final Return putNode = new Return();
		createNode(node, putNode);
	}

	public void outARetexpStm(ARetexpStm node) {
		final Return putNode = new Return();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}

	public void outAIfStm(AIfStm node) {
		final If putNode = new If();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		putNode.setStm((StmNode) getNode(node.getStm()));
		createNode(node, putNode);
	}

	public void outAIfelseStm(AIfelseStm node) {
		final If putNode = new If();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		putNode.setStm((StmNode) getNode(node.getThenStm()));
		putNode.setElseStm((StmNode) getNode(node.getElseStm()));
		createNode(node, putNode);
	}

	public void outAWhileStm(AWhileStm node) {
		final While putNode = new While();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		putNode.setStm((StmNode) getNode(node.getStm()));
		createNode(node, putNode);
	}

	public void outACompStm(ACompStm node) {
		createNode(node, getNode(node.getCompoundstm()));
	}

	public void outAExpStm(AExpStm node) {
		final Expression putNode = new Expression();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}

	/* stm_no_short_if */
	public void outANoStmNoShortIf(ANoStmNoShortIf node) {
		final Noop putNode = new Noop();
		createNode(node, putNode);
	}

	public void outAShowStmNoShortIf(AShowStmNoShortIf node) {
		final Show putNode = new Show();
		putNode.setDocument((Document) getNode(node.getDocument()));
		putNode.setReceive((Receive) getNode(node.getReceive()));
		createNode(node, putNode);
	}

	public void outAExitStmNoShortIf(AExitStmNoShortIf node) {
		final Exit putNode = new Exit();
		putNode.setDocument((Document) getNode(node.getDocument()));
		createNode(node, putNode);
	}

	public void outAReturnStmNoShortIf(AReturnStmNoShortIf node) {
		final Return putNode = new Return();
		createNode(node, putNode);
	}

	public void outARetexpStmNoShortIf(ARetexpStmNoShortIf node) {
		final Return putNode = new Return();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}

	public void outAIfElseStmNoShortIf(AIfelseStmNoShortIf node) {
		final If putNode = new If();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		putNode.setStm((StmNode) getNode(node.getThenStm()));
		putNode.setElseStm((StmNode) getNode(node.getElseStm()));
		createNode(node, putNode);
	}

	public void outAWhileStmNoShortIf(AWhileStmNoShortIf node) {
		final While putNode = new While();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		putNode.setStm((StmNode) getNode(node.getStmNoShortIf()));
		createNode(node, putNode);
	}

	public void outACompStmNoShortIf(ACompStmNoShortIf node) {
		createNode(node, getNode(node.getCompoundstm()));
	}

	public void outAExpStmNoShortIf(AExpStmNoShortIf node) {
		final Expression putNode = new Expression();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}

	/* document */
	public void outAIdDocument(AIdDocument node) {
		final Document putNode = new Document();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	public void outAPlugDocument(APlugDocument node) {
		final Document putNode = new Document();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		if (node.getPlugs() != null) {
			putNode.getPlugs().addAll((List<Plug>) getNode(node.getPlugs()));
		}
		createNode(node, putNode);
	}

	/* receive */
	public void outAReceive(AReceive node) {
		final Receive putNode = new Receive();
		if (node.getInputs() != null) {
			putNode.getInputs().addAll(
					(List<wig.compiler.ast.stm.html.Input>) getNode(node
							.getInputs()));
		}
		createNode(node, putNode);
	}

	/* compoundstm */
	public void outACompoundstm(ACompoundstm node) {
		final CompoundStm putNode = new CompoundStm();
		for (final Object n : node.getVariable()) {
			putNode.addVariable((VariableNode) getNode((PVariable) n));
		}
		for (final Object n : node.getStm()) {
			putNode.addStm((StmNode) getNode((PStm) n));
		}
		createNode(node, putNode);
	}

	/* plugs nodes */
	public void outAOnePlugs(AOnePlugs node) {
		final List<Plug> putNode = new ArrayList<Plug>();
		final Plug plug = (Plug) getNode(node.getPlug());
		putNode.add(plug);
		createNode(node, putNode);
	}

	public void outAManyPlugs(AManyPlugs node) {
		final List<Plug> putNode = new ArrayList<Plug>();
		final List<Plug> nodes = (List<Plug>) getNode(node.getPlugs());
		final Plug plug = (Plug) getNode(node.getPlug());
		putNode.addAll(nodes);
		putNode.add(plug);
		createNode(node, putNode);
	}

	/* plug node */
	public void outAPlug(APlug node) {
		final Plug putNode = new Plug();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}

	/* inputs nodes */
	public void outAOneInputs(AOneInputs node) {
		final List<wig.compiler.ast.stm.html.Input> putNode = new ArrayList<wig.compiler.ast.stm.html.Input>();
		putNode.add((wig.compiler.ast.stm.html.Input) getNode(node.getInput()));
		createNode(node, putNode);
	}

	public void outAManyInputs(AManyInputs node) {
		final List<wig.compiler.ast.stm.html.Input> putNode = new ArrayList<wig.compiler.ast.stm.html.Input>();
		putNode.addAll((List<wig.compiler.ast.stm.html.Input>) getNode(node
				.getInputs()));
		putNode.add((wig.compiler.ast.stm.html.Input) getNode(node.getInput()));
		createNode(node, putNode);
	}

	/* input node */
	public void outAInput(AInput node) {
		final wig.compiler.ast.stm.html.Input putNode = new wig.compiler.ast.stm.html.Input();
		putNode.setLvalue((LValueNode) getNode(node.getLvalue()));
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	/* exp */
	public void outAAssignExp(AAssignExp node) {
		final AssignExpressionNode putNode = new AssignExpressionNode();
		putNode.setLvalue((LValueNode) getNode(node.getLvalue()));
		putNode.setValue((ExpressionNode) getNode(node.getOrExp()));
		createNode(node, putNode);
	}

	public void outADefaultExp(ADefaultExp node) {
		createNode(node, getNode(node.getOrExp()));
	}

	/* orexp */
	public void outAOrOrExp(AOrOrExp node) {
		final OrExpression putNode = new OrExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		createNode(node, putNode);
	}

	public void outADefaultOrExp(ADefaultOrExp node) {
		createNode(node, getNode(node.getAndExp()));
	}

	/* andexp */

	public void outAAndAndExp(AAndAndExp node) {
		final AndExpression putNode = new AndExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		createNode(node, putNode);
	}

	public void outADefaultAndExp(ADefaultAndExp node) {
		createNode(node, getNode(node.getCmpExp()));
	}

	/* cmp_exp */
	public void outAEqCmpExp(AEqCmpExp node) {
		final CompareExpression putNode = new CompareExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getEq())));
		createNode(node, putNode);
	}

	public void outANeqCmpExp(ANeqCmpExp node) {
		final CompareExpression putNode = new CompareExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getNeq())));
		createNode(node, putNode);
	}

	public void outALtCmpExp(ALtCmpExp node) {
		final CompareExpression putNode = new CompareExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getLt())));
		createNode(node, putNode);
	}

	public void outAGtCmpExp(AGtCmpExp node) {
		final CompareExpression putNode = new CompareExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getGt())));
		createNode(node, putNode);
	}

	public void outALteqCmpExp(ALteqCmpExp node) {
		final CompareExpression putNode = new CompareExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getLteq())));
		createNode(node, putNode);
	}

	public void outAGteqCmpExp(AGteqCmpExp node) {
		final CompareExpression putNode = new CompareExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getGteq())));
		createNode(node, putNode);
	}

	public void outADefaultCmpExp(ADefaultCmpExp node) {
		createNode(node, getNode(node.getAddExp()));
	}

	/* addexp nodes */
	public void outAPlusAddExp(APlusAddExp node) {
		final AdditionExpression putNode = new AdditionExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getPlus())));
		createNode(node, putNode);
	}

	public void outAMinusAddExp(AMinusAddExp node) {
		final AdditionExpression putNode = new AdditionExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getMinus())));
		createNode(node, putNode);
	}

	public void outADefaultAddExp(ADefaultAddExp node) {
		createNode(node, getNode(node.getMultExp()));
	}

	/* multexp */
	public void outAMultMultExp(AMultMultExp node) {
		final MultiplyExpression putNode = new MultiplyExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getMult())));
		createNode(node, putNode);
	}

	public void outADivMultExp(ADivMultExp node) {
		final MultiplyExpression putNode = new MultiplyExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getDiv())));
		createNode(node, putNode);
	}

	public void outAModMultExp(AModMultExp node) {
		final MultiplyExpression putNode = new MultiplyExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getMod())));
		createNode(node, putNode);
	}

	public void outADefaultMultExp(ADefaultMultExp node) {
		createNode(node, getNode(node.getJoinExp()));
	}

	/* joinexp nodes */
	public void outAJoinJoinExp(AJoinJoinExp node) {
		final JoinExpression putNode = new JoinExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getLeft()));
		putNode.setRight((ExpressionNode) getNode(node.getRight()));
		createNode(node, putNode);
	}

	public void outADefaultJoinExp(ADefaultJoinExp node) {
		createNode(node, getNode(node.getTupleExp()));
	}

	/* tupleexp nodes */
	public void outAKeepTupleExp(AKeepTupleExp node) {
		final TupleExpression putNode = new TupleExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getTupleExp()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getKeep())));
		putNode.addIdentifier(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	public void outARemoveTupleExp(ARemoveTupleExp node) {
		final TupleExpression putNode = new TupleExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getTupleExp()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getRemove())));
		putNode.addIdentifier(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	public void outAKeepManyTupleExp(AKeepManyTupleExp node) {
		final TupleExpression putNode = new TupleExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getTupleExp()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getKeep())));
		putNode.getIdentifiers().addAll(
				(List<String>) getNode(node.getIdentifiers()));
		createNode(node, putNode);
	}

	public void outARemoveManyTupleExp(ARemoveManyTupleExp node) {
		final TupleExpression putNode = new TupleExpression();
		putNode.setLeft((ExpressionNode) getNode(node.getTupleExp()));
		putNode.setSign(putNode.getSIGN(cleanString(node.getRemove())));
		putNode.getIdentifiers().addAll(
				(List<String>) getNode(node.getIdentifiers()));
		createNode(node, putNode);
	}

	public void outADefaultTupleExp(ADefaultTupleExp node) {
		createNode(node, getNode(node.getUnaryExp()));
	}

	/* unaryexp */
	public void outANotUnaryExp(ANotUnaryExp node) {
		final BaseExp putNode = (BaseExp) getNode(node.getBaseExp());
		putNode.setSign(putNode.getSign(cleanString(node.getNot())));
		createNode(node, putNode);
	}

	public void outANegUnaryExp(ANegUnaryExp node) {
		final BaseExp putNode = (BaseExp) getNode(node.getBaseExp());
		putNode.setSign(putNode.getSign(cleanString(node.getMinus())));
		createNode(node, putNode);
	}

	public void outADefaultUnaryExp(ADefaultUnaryExp node) {
		createNode(node, getNode(node.getBaseExp()));
	}

	/* baseexp */
	public void outALvalueBaseExp(ALvalueBaseExp node) {
		createNode(node, getNode(node.getLvalue()));
	}

	public void outACallBaseExp(ACallBaseExp node) {
		final FunctionCall putNode = new FunctionCall();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		if (node.getExps() != null) {
			putNode.getExpressionNodes().addAll(
					(List<ExpressionNode>) getNode(node.getExps()));
		}
		createNode(node, putNode);
	}

	public void outAIntBaseExp(AIntBaseExp node) {
		final IntegerValue putNode = new IntegerValue();
		putNode.setValue(cleanString(node.getIntconst()));
		createNode(node, putNode);
	}

	public void outATrueBaseExp(ATrueBaseExp node) {
		final BooleanValue putNode = new BooleanValue();
		putNode.setValue(putNode.getBOOLEANVALUE(cleanString(node.getTrue())));
		createNode(node, putNode);
	}

	public void outAFalseBaseExp(AFalseBaseExp node) {
		final BooleanValue putNode = new BooleanValue();
		putNode.setValue(putNode.getBOOLEANVALUE(cleanString(node.getFalse())));
		createNode(node, putNode);
	}

	public void outAStringBaseExp(AStringBaseExp node) {
		final StringValue putNode = new StringValue();
		putNode.setValue(cleanString(node.getStringconst()));
		createNode(node, putNode);
	}

	public void outATupleBaseExp(ATupleBaseExp node) {
		final TupleValue putNode = new TupleValue();
		if (node.getFieldvalues() != null) {
			putNode.getFieldValueNodes().addAll(
					(List<FieldValueNode>) getNode(node.getFieldvalues()));
		}
		createNode(node, putNode);
	}

	public void outAParenBaseExp(AParenBaseExp node) {
		final ParenExpression putNode = new ParenExpression();
		putNode.setExp((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}

	/* exps node */
	public void outAOneExps(AOneExps node) {
		final List<ExpressionNode> putNode = new ArrayList<ExpressionNode>();
		putNode.add((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}

	public void outAManyExps(AManyExps node) {
		final List<ExpressionNode> putNode = new ArrayList<ExpressionNode>();
		putNode.addAll((List<ExpressionNode>) getNode(node.getExps()));
		putNode.add((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}

	/* lvalue node */
	public void outASimpleLvalue(ASimpleLvalue node) {
		final LValueNode putNode = new LValueNode();
		putNode.addIdentifier(cleanString(node.getIdentifier()));
		createNode(node, putNode);
	}

	public void outAQualifiedLvalue(AQualifiedLvalue node) {
		final LValueNode putNode = new LValueNode();
		putNode.addIdentifier(cleanString(node.getLeft()));
		putNode.addIdentifier(cleanString(node.getRight()));
		createNode(node, putNode);
	}

	/* fieldvalues node */
	public void outAOneFieldvalues(AOneFieldvalues node) {
		final List<FieldValueNode> putNode = new ArrayList<FieldValueNode>();
		putNode.add((FieldValueNode) getNode(node.getFieldvalue()));
		createNode(node, putNode);
	}

	public void outAManyFieldvalues(AManyFieldvalues node) {
		final List<FieldValueNode> putNode = new ArrayList<FieldValueNode>();
		putNode.addAll((List<FieldValueNode>) getNode(node.getFieldvalues()));
		putNode.add((FieldValueNode) getNode(node.getFieldvalue()));
		createNode(node, putNode);
	}

	/* fieldvalue node */
	public void outAFieldvalue(AFieldvalue node) {
		final FieldValueNode putNode = new FieldValueNode();
		putNode.setIdentifier(cleanString(node.getIdentifier()));
		putNode.setNode((ExpressionNode) getNode(node.getExp()));
		createNode(node, putNode);
	}
}
