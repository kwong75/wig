package wig.compiler.codegeneration.code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wig.compiler.codegeneration.FileTree;

public class Template extends FileTree {
	private String imports;
	private FileTree variableDeclaration;
	private FileTree functionDeclarations;
	private List<FileTree> htmlDeclarations = new ArrayList<FileTree>();
	private List<FileTree> schemaDeclarations = new ArrayList<FileTree>();
	private List<FileTree> sessionDeclarations = new ArrayList<FileTree>();
	private FileTree main;

	private void generateFTWigLibrary(final String file) {
		try {
			final FileWriter writer = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public FileTree run() {
		final FileTree tree = new FileTree();
		imports = "#include <stdio.h>" +
				"\n#include <stdlib.h>" +
				"\n#include <string.h>" +
				"\n#include <time.h>" +
				"\n#include \"ftwig.c\"" +
				"\\\\To allow for bool values" +
				"\n#define true 1" +
				"\n#define false 0" +
				"\ntypedef int bool ;";
		final File file = new File("ftwig.c");
		if (!file.exists()) {
			generateFTWigLibrary("ftwig.c");
		}
		return tree;
	}

	public void setCode() {
		final StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(imports);
		stringbuilder.append(variableDeclaration.getCode());
		stringbuilder.append(functionDeclarations.getCode());

		for (final FileTree node : htmlDeclarations) {
			stringbuilder.append(node);
		}

		for (final FileTree node : schemaDeclarations) {
			stringbuilder.append(node);
		}

		for (final FileTree node : sessionDeclarations) {
			stringbuilder.append(node);
		}

		stringbuilder.append(main);

		setCode(stringbuilder.toString());
	}
}
