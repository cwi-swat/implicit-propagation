package modules.values;

public class Int implements Value {
	private final int value;

	public Int(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}
