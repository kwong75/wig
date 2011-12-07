package wig.compiler.ast.stm;

public class Noop implements StmNode {
	@Override
	public String toString() {
		return ";\n";
	}
}
