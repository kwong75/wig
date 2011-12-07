package wig.compiler.ast.exp.value;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.ast.exp.BaseExp;

public class TupleValue extends BaseExp {
	
	private List<FieldValueNode> fieldValues = new ArrayList<FieldValueNode>();
	
	public void addFieldValue(final FieldValueNode node) {
		fieldValues.add(node);
	}
	
	public List<FieldValueNode> getFieldValueNodes() {
		return fieldValues;
	}
	
	@Override
	public String toString() {
		String returnValue = "";
		for (FieldValueNode fieldValue : fieldValues) {
			returnValue = returnValue + fieldValue.toString() + ",";
		}
		if (fieldValues.size() > 0) {
			returnValue = returnValue.substring(0, fieldValues.size() - 1);
		}
		
		return getSign() + "tuple { " + returnValue + "}";
	}
	
}
