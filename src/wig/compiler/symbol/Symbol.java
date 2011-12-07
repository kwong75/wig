package wig.compiler.symbol;

import wig.compiler.symbolkind.SymbolKind;

/**
 * For now making Symbol values immutable;
 * 
 * @author kwong75
 */
public class Symbol {
	private String name;
	private SymbolKind kind;
	
	public Symbol(final String name, final SymbolKind kind) {
		this.name = name;
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public SymbolKind getKind() {
		return kind;
	}

	public Boolean compare(final String name) {
		if (name.contentEquals(this.name)) {
			return true;
		}
		return false;
	}
	
}
