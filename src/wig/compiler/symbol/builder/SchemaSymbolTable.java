package wig.compiler.symbol.builder;

import wig.compiler.ast.schema.FieldValue;
import wig.compiler.ast.schema.SchemaNode;
import wig.compiler.symbolkind.Field;
import wig.compiler.symbolkind.Schema;

public class SchemaSymbolTable {

	public static Schema buildSchemaSymbolKind(final SchemaNode node) {
		final Schema returnValue = new Schema();
		returnValue.setName(node.getIdentifier());
		for (FieldValue fieldNode : node.getFields()) {
			final Field field = new Field();
			field.setName(fieldNode.getIdentifier().toString());
			field.setType(fieldNode.getType().getType());
			returnValue.addField(field);
		}
		return returnValue;
	}
}
