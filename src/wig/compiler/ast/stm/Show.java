package wig.compiler.ast.stm;

import wig.compiler.ast.stm.html.Document;
import wig.compiler.ast.stm.html.Receive;

public class Show implements StmNode {
	private Document document;
	private Receive receive;

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Receive getReceive() {
		return receive;
	}

	public void setReceive(Receive receive) {
		this.receive = receive;
	}

	@Override
	public String toString() {
		String returnValue = "show " + document.toString();
		if (receive != null) {
			returnValue = returnValue + " " + receive.toString();
		}
		returnValue = returnValue + ";\n";
		return returnValue;
	}
	
}
