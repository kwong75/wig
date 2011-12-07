package wig.compiler.symbol;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import wig.compiler.symbolkind.SymbolKind;

public class SymbolTable {

	// Just a precaution to prevent overwriting values;
	private Hashtable<String, List<Symbol>> table = new Hashtable<String, List<Symbol>>();
	private SymbolTable next;

	// Used primarily for String printing and Symbol analysis
	public SymbolTable() {

	}

	public void scopeSymbolTable(SymbolTable parentScope) {
		next = parentScope;
	}

	public Symbol putSymbol(final String name, SymbolKind kind) {
		List<Symbol> symbols = table.get(name);
		if (symbols != null) {
			for (final Symbol symbol : symbols) {
				if (symbol.compare(name)
						&& kind.getClass().toString()
								.contentEquals(symbol.getClass().toString())) {
					return symbol;
				}
			}
		}
		if (symbols == null) {
			symbols = new LinkedList<Symbol>();
			table.put(name, symbols);
		}
		final Symbol newSymbol = new Symbol(name, kind);
		symbols.add(newSymbol);
		return newSymbol;
	}

	/**
	 * Search through this table and all its scoping ancestor
	 * 
	 * @param name
	 *            This should be the identifier of the symbol
	 * @return the matching symbol
	 */
	public Symbol getSymbol(final String name) {
		return getSymbol(this, name);
	}

	private Symbol getSymbol(final SymbolTable symTable, final String name) {
		List<Symbol> symbols = symTable.table.get(name);

		if (symbols != null) {
			for (final Symbol symbol : symbols) {
				if (symbol.compare(name)) {
					return symbol;
				}
			}
		}

		if (symTable.next == null) {
			return null;
		} else {
			return getSymbol(symTable.next, name);
		}
	}

	public Symbol getSymbol(final String name, final Class<?> kind) {
		return getSymbol(this, name, kind);
	}

	private Symbol getSymbol(final SymbolTable symTable, final String name,
			final Class<?> kind) {
		List<Symbol> symbols = symTable.table.get(name);

		if (symbols != null) {
			for (final Symbol symbol : symbols) {
				if (symbol.compare(name)
						&& symbol.getKind().getClass().toString()
								.contentEquals(kind.toString())) {
					return symbol;
				}
			}
		}

		if (symTable.next == null) {
			return null;
		} else {
			return getSymbol(symTable.next, name);
		}
	}

	/**
	 * Check if a symbol with a given name is define within this scope.
	 * 
	 * @param name
	 *            This should be the identifier of the symbol
	 * @return
	 */
	public Boolean defSymbol(final String name) {
		final List<Symbol> symbols = table.get(name);
		if (symbols != null) {
			for (final Symbol symbol : symbols) {
				if (symbol.compare(name)) {
					return true;
				}
			}
		}
		return false;
	}

	public String toString() {
		String returnValue = "";
		Enumeration<List<Symbol>> elements = table.elements();
		while (elements.hasMoreElements()) {
			for (final Symbol symbol : elements.nextElement()) {
				returnValue = returnValue + symbol.getName() + symbol.getKind();
			}
		}
		return returnValue;
	}
}
