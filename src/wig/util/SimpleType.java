package wig.util;

public class SimpleType {
	public static Boolean isSimpleType(final String type) {
		String ctype = type;
		ctype = ctype.replaceAll(" ", "");
		if (ctype.contentEquals("int") || ctype.contentEquals("bool") || ctype.contentEquals("string")
				|| ctype.contentEquals("void")) {
			return true;
		}
		return false;
	}
}
