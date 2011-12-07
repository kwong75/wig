package wig.compiler.typecheck;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.ast.exp.value.FieldValueNode;
import wig.compiler.symbol.SymbolTable;
import wig.compiler.symbolkind.Field;
import wig.compiler.symbolkind.Schema;
import wig.compiler.typecheck.rule.exp.TypedExpressionNode;

public class ExpressionType {
	private boolean isPrimitive;
	private String primitiveType;
	private List<FieldType> fieldTypes = new ArrayList<FieldType>();
	private SymbolTable table;

	public ExpressionType(final SymbolTable table) {
		this.table = table;
	}

	public boolean isPrimitive(String type) {
		return type.contentEquals("int") || type.contentEquals("string")
				|| type.contentEquals("bool") || type.contentEquals("void");

	}
	
	public boolean getIsPrimitive() {
		return isPrimitive;
	}

	public void setPrimitiveType(String primitiveType) {
		this.primitiveType = primitiveType;
		isPrimitive = true;
	}

	public String getPrimitiveType() {
		return primitiveType;
	}

	public void addFieldType(Field field) {
		isPrimitive = false;
		FieldType fieldType = new FieldType();
		fieldType.setName(field.getName());
		final ExpressionType type = new ExpressionType(table);

		if (isPrimitive(field.getType())) {
			type.setPrimitiveType(field.getType());
		} else {
			final Schema schema = (Schema) table.getSymbol(field.getType(),
					Schema.class).getKind();
			type.addFieldTypeAll(schema.getFields());
		}
		fieldType.setType(type);
	}

	/**
	 * Working from Symbol Resolution
	 * 
	 * @param type
	 */
	public void addFieldTypeAll(List<Field> type) {
		for (final Field field : type) {
			addFieldType(field);
		}
	}

	public List<FieldType> getType() {
		return fieldTypes;
	}

	public boolean compareTo(ExpressionType type) {
		if (type.isPrimitive) {
			if (isPrimitive)
				return type.getPrimitiveType()
						.contentEquals(getPrimitiveType());
		} else {
			if (!isPrimitive)
				return isPartialSubset(type.getType(), getType());
		}
		return false;
	}

	public void addFieldTypeAll(String schemaId) {
		Schema schema = (Schema) table.getSymbol(schemaId, Schema.class)
				.getKind();
		addFieldTypeAll(schema.getFields());
	}

	public boolean isPartialSubset(List<FieldType> field1,
			List<FieldType> field2) {
		List<FieldType> greaterList;
		List<FieldType> smallerList;
		if (field1.size() > field2.size()) {
			greaterList = field1;
			smallerList = field2;
		} else {
			greaterList = field2;
			smallerList = field1;
		}

		for (FieldType type : smallerList) {
			boolean found = false;
			for (FieldType type2 : greaterList) {
				if (type.getName().contentEquals(type2.getName())
						&& type.getType().compareTo(type2.getType())) {
					found = true;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	public void addFieldTypeAll(List<FieldValueNode> fieldValueNodes,
			final SymbolTable table) {
		isPrimitive = false;
		for (final FieldValueNode fieldValue : fieldValueNodes) {
			final TypedExpressionNode temp = new TypedExpressionNode();
			temp.checkExp(fieldValue.getNode(), table);
			final FieldType fieldType = new FieldType();
			fieldType.setName(fieldValue.getIdentifier());
			fieldType.setType(temp.getType());
			fieldTypes.add(fieldType);
		}

	}

}
