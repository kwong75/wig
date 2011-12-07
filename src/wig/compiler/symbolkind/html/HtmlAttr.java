package wig.compiler.symbolkind.html;

public class HtmlAttr {
	private String write;
	private String read;

	public String getWrite() {
		return write;
	}

	public void setWrite(String write) {
		this.write = write;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}
	
	public String toString() {
		String assign = "";
		if (write != null) {
			assign = "write = " + write;
		}
		return "Attribute: read = " + read + " " + assign + "\n"; 
	}
}
