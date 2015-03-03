package funcons.entities.experiments;

import funcons.entities.Value;

public class ConstantPattern implements Pattern {
	private Value val;
	
	public ConstantPattern(Value val) {
		this.val = val;
	}

	public Value getValue() {
		return val;
	}
	
	

}
