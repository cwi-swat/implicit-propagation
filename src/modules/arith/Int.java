package modules.arith;

import modules.values.Value;

public class Int implements Value {
	private final int value;

	public Int(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value + "";
	}
}
