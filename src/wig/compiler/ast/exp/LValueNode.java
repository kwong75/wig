package wig.compiler.ast.exp;

import java.util.ArrayList;
import java.util.List;

public class LValueNode extends BaseExp {
	private List<String> identifiers = new ArrayList<String>();
	
	public void addIdentifier(final String id) {
		identifiers.add(id);
	}
	
	public List<String> getIdentifiers() {
		return identifiers;
	}

	@Override
	public String toString() {
		String returnValue = "";
		for (final String identifier : identifiers) {
			returnValue = returnValue + identifier + ".";
		}

		return returnValue.substring(0, returnValue.length() - 1);
	}
}
