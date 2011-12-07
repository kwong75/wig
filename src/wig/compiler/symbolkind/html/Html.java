package wig.compiler.symbolkind.html;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.symbolkind.SymbolKind;
import wig.node.ASelectHtmlbody;

/**
 * TODO: Did not handle {@link ASelectHtmlbody}
 * 
 * @author kwong75
 */
public class Html extends SymbolKind {
	private String name;
	private List<HtmlInput> receives = new ArrayList<HtmlInput>();
	private List<HtmlHole> plugs = new ArrayList<HtmlHole>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HtmlInput> getReceives() {
		return receives;
	}

	public void addReceive(final HtmlInput htmlInput) {
		for (HtmlInput htmlin : receives) {
			if (htmlin.getName().equals(htmlInput.getName())) {
				htmlin.getAttr().addAll(htmlInput.getAttr());
				return;
			}
		}
		receives.add(htmlInput);
	}

	public List<HtmlHole> getPlugs() {
		return plugs;
	}

	public void addPlug(final HtmlHole htmlHole) {
		plugs.add(htmlHole);
	}

	public String toString() {
		String returnValue = "";
		returnValue = returnValue + "Html " + name + " [ \n";
		for (final HtmlInput hole : receives) {
			returnValue = returnValue + hole.toString();
		}
		for (final HtmlHole plug : plugs) {
			returnValue = returnValue + plug.toString();
		}
		returnValue = returnValue + "] \n\n";
		return returnValue;
	}

	public Boolean validPlug(String identifier) {
		String toCompare = identifier.replaceAll(" ", "");
		for (final HtmlHole plug : plugs) {
			String compare = plug.getName().replaceAll("\"", "");
			compare = compare.replaceAll(" ", "");
			if (compare.contentEquals(toCompare)) {
				return true;
			}
		}
		return false;
	}

	public Boolean validReceive(String identifier) {
		String toCompare = identifier.replaceAll(" ", "");
		for (final HtmlInput receive : receives) {
			String compare = receive.getName().replaceAll("\"", "");
			compare = compare.replaceAll(" ", "");
			if (compare.contentEquals(toCompare)) {
				return true;
			}
		}
		return false;
	}
}
