package wig.compiler.ast.exp;

/**
 * Look at package value for the possibility These contains the leaf of the exp
 * tree. It isn't necessarily leaf.
 * 
 * @author kwong75
 */
public class BaseExp implements ExpressionNode {
	private String sign = "";

	private enum SIGN {
		NOT, MINUS
	};

	public String getSign() {
		return sign;
	}

	public void setSign(SIGN sign) {
		switch (sign) {
		case NOT:
			this.sign = "!";
			break;
		case MINUS:
			this.sign = "-";
			break;
		}
	}
	
	public SIGN getSign(String sign) {
		if (sign.contentEquals("!")) {
			return SIGN.NOT;
		} else if (sign.contentEquals("-")) {
			return SIGN.MINUS;
		} else {
			throw new RuntimeException("Failed to find enum in BaseExp :" + sign);
		}
	}
}
