package wig.compiler.symbol.builder;

import wig.compiler.ast.html.Attribute;
import wig.compiler.ast.html.Hole;
import wig.compiler.ast.html.HtmlBodyNode;
import wig.compiler.ast.html.HtmlNode;
import wig.compiler.ast.html.Input;
import wig.compiler.ast.html.Select;
import wig.compiler.ast.html.TagEnd;
import wig.compiler.ast.html.TagStart;
import wig.compiler.symbolkind.html.Html;
import wig.compiler.symbolkind.html.HtmlAttr;
import wig.compiler.symbolkind.html.HtmlHole;
import wig.compiler.symbolkind.html.HtmlInput;
import wig.compiler.symbolkind.html.HtmlTag;

public class HtmlSymbolTable {
	
	public static Html buildHtmlSymbolKind(final HtmlNode node) {
		final Html returnValue = new Html();
		returnValue.setName(node.getIdentifier());
		for (final HtmlBodyNode body : node.getNodes()) {
			symAHtml(returnValue, body);
		}
		return returnValue;
	}

	private static void symAHtml(final Html returnValue, final HtmlBodyNode node) {
		if (node instanceof TagStart) {
			symATagStartHtmlbody(returnValue,(TagStart)node);
		} else if (node instanceof TagEnd) {
			symATagEndHtmlbody(returnValue,(TagEnd)node);
		} else if (node instanceof Hole) {
			symAHoleHtmlbody(returnValue,(Hole)node);
		} else if (node instanceof Input) {
			symAInputHtmlbody(returnValue,(Input)node);
		} else if (node instanceof Select) {
			symASelectHtmlbody(returnValue,(Select)node);
		}
	}

	//TODO Will handle in the future
	private static void symASelectHtmlbody(Html returnValue, Select node) {
		// TODO Auto-generated method stub
	}

	private static void symAInputHtmlbody(Html returnValue, Input node) {
		final HtmlInput htmlInput = new HtmlInput();
		htmlInput.setName(node.getName());
		htmlInput.setType(node.getInputType());
		for (Attribute inputAttr : node.getAttributes()) {
			final HtmlAttr attr = new HtmlAttr();
			attr.setWrite(inputAttr.getLeft());
			attr.setRead(inputAttr.getRight());
			htmlInput.addAttr(attr);
		}
		// Only adding a receive if it is define
		if (node.getName() != null) {
			returnValue.addReceive(htmlInput);
		}
	}

	private static void symAHoleHtmlbody(Html returnValue, Hole node) {
		final HtmlHole hole = new HtmlHole();
		hole.setName(node.getIdentifier().toString());
		returnValue.addPlug(hole);
	}

	/**
	 * TODO
	 * Suppose to be related to {@link HtmlTag}
	 * @param returnValue
	 * @param node
	 */
	private static void symATagEndHtmlbody(Html returnValue, TagEnd node) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * TODO
	 * Suppose to be related to {@link HtmlTag}
	 * @param returnValue
	 * @param node
	 */
	private static void symATagStartHtmlbody(Html returnValue,
			TagStart node) {
		// TODO Auto-generated method stub
	}
}
