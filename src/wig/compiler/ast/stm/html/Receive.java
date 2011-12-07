package wig.compiler.ast.stm.html;

import java.util.ArrayList;
import java.util.List;

public class Receive {
	private List<Input> inputs = new ArrayList<Input>();

	public void addInput(Input input) {
		inputs.add(input);
	}

	public List<Input> getInputs() {
		return inputs;
	}
	
	@Override
	public String toString() {
		String returnValue = "receive (";
		for (Input input : inputs) {
			returnValue = returnValue + input.toString() + ",";
		}
		returnValue = returnValue.substring(0, returnValue.length() - 1);
		return returnValue + ")";
	}
}
