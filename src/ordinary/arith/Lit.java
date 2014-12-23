package ordinary.arith;

import modules.arith.Int;
import modules.values.Value;

public class Lit implements IEvalBase {
	private int value;

	public Lit(int value) {
		this.value = value;
	}
	
	@Override
	public Value eval() {
		return new Int(value);
	}

}
