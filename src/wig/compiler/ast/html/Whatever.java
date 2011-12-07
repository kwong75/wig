package wig.compiler.ast.html;

public class Whatever implements HtmlBodyNode {
	private String whatever;

	public String getWhatever() {
		return whatever;
	}

	public void setWhatever(String whatever) {
		this.whatever = whatever;
	}

	@Override
	public String toString() {
		return whatever;
	}
}
