package wig.compiler.ast.exp.value;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.ast.exp.ExpressionNode;
import wig.compiler.ast.exp.BaseExp;

public class FunctionCall extends BaseExp {
	private String identifier;
	private List<ExpressionNode> expressionNodes = new ArrayList<ExpressionNode>();
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getIdentifier() {
		return identifier;
	}

	public void addExpressionNode(final ExpressionNode node) {
		expressionNodes.add(node);
	}
	
	public List<ExpressionNode> getExpressionNodes() {
		return expressionNodes;
	}
	
	@Override
	public String toString() {
		String returnValue = "";
		for (final ExpressionNode node : expressionNodes) {
			returnValue = returnValue + node.toString() + " , ";
		}
		if (expressionNodes.size() > 0) {
			returnValue = returnValue.substring(0, returnValue.length());
		}

		return getSign() + identifier + "(" + returnValue + ")";
	}
	
}
