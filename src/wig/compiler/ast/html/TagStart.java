package wig.compiler.ast.html;

import java.util.ArrayList;
import java.util.List;

public class TagStart implements HtmlBodyNode {
	private String identifier;
	private List<Attribute> attributes = new ArrayList<Attribute>();
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	public void addAttribute(final Attribute node) {
		attributes.add(node);
	}
	
	@Override
	public String toString() {
		String returnValue = "<" + identifier;
		for (Attribute attribute : attributes) {
			returnValue = returnValue + " " + attribute.toString();
		}
		return returnValue + ">";
	}
}
