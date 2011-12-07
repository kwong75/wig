package wig.compiler.ast.html;

public class Meta implements HtmlBodyNode {
	private String meta;

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return meta;
	}
}
