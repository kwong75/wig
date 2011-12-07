package wig.compiler.symbolkind;

import java.util.ArrayList;
import java.util.List;


public class Schema extends SymbolKind {
	private String name;
	private List<Field> fields = new ArrayList<Field>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void addField(Field field) {
		fields.add(field);
	}
	
	public String toString() {
		String returnValue = "Schema " + name + " [\n";
		for (Field field : fields) {
			returnValue = returnValue + field.toString();
		}
		returnValue = returnValue + "]\n\n";
		return returnValue;
	}

}
