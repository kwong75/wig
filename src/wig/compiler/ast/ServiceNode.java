package wig.compiler.ast;

import java.util.ArrayList;
import java.util.List;

import wig.compiler.ast.function.FunctionNode;
import wig.compiler.ast.html.HtmlNode;
import wig.compiler.ast.schema.SchemaNode;
import wig.compiler.ast.session.SessionNode;
import wig.compiler.ast.variable.VariableNode;
import wig.compiler.symbol.SymbolTable;

public class ServiceNode {
	private List<HtmlNode> htmls = new ArrayList<HtmlNode>();
	private List<SchemaNode> schemas = new ArrayList<SchemaNode>();
	private List<VariableNode> variables = new ArrayList<VariableNode>();
	private List<FunctionNode> functions = new ArrayList<FunctionNode>();
	private List<SessionNode> sessions = new ArrayList<SessionNode>();
	
	//SymbolTable
	private SymbolTable symbolTable;

	public List<HtmlNode> getHtmls() {
		return htmls;
	}

	public void addHtml(HtmlNode html) {
		this.htmls.add(html);
	}

	public List<SchemaNode> getSchemas() {
		return schemas;
	}

	public void addSchema(SchemaNode schema) {
		this.schemas.add(schema);
	}

	public List<VariableNode> getVariables() {
		return variables;
	}

	public void addVariable(VariableNode variable) {
		this.variables.add(variable);
	}

	public List<FunctionNode> getFunctions() {
		return functions;
	}

	public void addFunction(FunctionNode function) {
		this.functions.add(function);
	}

	public List<SessionNode> getSessions() {
		return sessions;
	}

	public void addSession(SessionNode session) {
		this.sessions.add(session);
	}
	
	@Override
	public String toString() {
		String returnValue = "service { \n";
		for (final HtmlNode html : htmls) {
			returnValue = returnValue + html.toString() + "\n";
		}
		for (final SchemaNode schema : schemas) {
			returnValue = returnValue + schema.toString() + "\n";
		}
		for (final VariableNode variable : variables) {
			returnValue = returnValue + variable.toString() + "\n";
		}
		for (final FunctionNode function : functions) {
			returnValue = returnValue + function.toString() + "\n";
		}
		for (final SessionNode session : sessions) {
			returnValue = returnValue + session.toString() + "\n";
		}
		return returnValue + "}";
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

}
