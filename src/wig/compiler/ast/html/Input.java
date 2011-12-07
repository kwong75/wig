package wig.compiler.ast.html;

import java.util.ArrayList;
import java.util.List;

public class Input implements HtmlBodyNode {
	private List<Attribute> attributes = new ArrayList<Attribute>();
	private String inputType;
	private String name;
	private enum TYPE {RADIO, TEXT};
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	public void addAttribute(final Attribute node) {
		if (node.getLeft().contentEquals("type") || node.getLeft().contentEquals("\"type\"")) {
			setInputType(getType(node.getRight()));
		} else if (node.getLeft().contentEquals("name") || node.getLeft().contentEquals("\"name\"")) {
			setName(node.getRight());
		}
		attributes.add(node);
	}
	
	public void setInputType(TYPE type) {
		switch (type) {
			case RADIO: inputType = "radio"; break;
			case TEXT: inputType = "text"; break;
			default : throw new RuntimeException("Select.java input type does not exist: " + type);
		}
	}
	
	public TYPE getType(final String type) {
		if (type.contentEquals("radio") || type.contentEquals("\"radio\"")) {
			return TYPE.RADIO;
		}
		else if (type.contentEquals("text") || type.contentEquals("\"text\"")) {
			return TYPE.TEXT;
		}
		throw new RuntimeException("Select.java input type does not exist: " + type);
	}

	public String getInputType() {
		return inputType;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		String returnValue = "<input";
		returnValue = returnValue + "type = " + getInputType();
		for (Attribute attribute : attributes) {
			returnValue = returnValue + " " + attribute.toString();
		}
		return returnValue + ">";
	}

	public void setName(String name) {
		this.name = name;
	}
}
