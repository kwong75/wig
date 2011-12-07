package wig.compiler.ast.stm;

import wig.compiler.ast.stm.html.Document;

public class Exit implements StmNode {
	private Document document;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "exit " + document.toString() + ";\n";
	}
}
