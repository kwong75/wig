package wig.compiler.symbolkind.html;

import java.util.ArrayList;
import java.util.List;

public class HtmlInput {
	private String name;
	private String type;
	private List<HtmlAttr> attrs = new ArrayList<HtmlAttr>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<HtmlAttr> getAttr() {
		return attrs;
	}

	public void addAttr(HtmlAttr attr) {
		attrs.add(attr);
	}
	
	public String toString(){
		String returnValue = "Input: name = " + name + "[\n";
		for (HtmlAttr attr : attrs) {
			returnValue = returnValue + attr.toString();
		}
		returnValue = returnValue + "]\n";
		return returnValue;
	}

}
