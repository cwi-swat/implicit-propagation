package modules.conditional;

import modules.values.Value;


public class Bool implements Value {

	private boolean value;

	public Bool(boolean value) {
		this.value = value;
	}
	
	boolean isTrue() {
		return value;
	}
	
}
