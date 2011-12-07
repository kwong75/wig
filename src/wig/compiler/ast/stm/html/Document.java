package wig.compiler.ast.stm.html;

import java.util.ArrayList;
import java.util.List;

public class Document {
	private String identifier;
	private List<Plug> plugs = new ArrayList<Plug>();

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<Plug> getPlugs() {
		return plugs;
	}

	public void addPlug(Plug plug) {
		plugs.add(plug);
	}

	@Override
	public String toString() {
		String returnValue = "";
		if (plugs.size() > 0) {
			returnValue = returnValue + "plug ";
		}
		returnValue = returnValue + identifier;
		if (plugs.size() > 0 ) {
			returnValue = returnValue + " (";
			for (Plug plug : plugs) {
				returnValue = returnValue + plug.toString() + ",";
			}
			returnValue = returnValue.substring(0, returnValue.length() - 1);
			returnValue = returnValue + ")";
		}
		return returnValue;
	}
	
}
