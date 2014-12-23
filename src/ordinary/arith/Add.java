package ordinary.arith;

import modules.arith.Int;
import modules.values.Value;

public class Add implements IEvalBase {
	IEvalBase  l, r;
	
	public Add(IEvalBase l, IEvalBase r) {
		this.l = l;
		this.r = r;
	}
	
	
	@Override
	public Value eval() {
		return new Int(((Int)l.eval()).getValue() + ((Int)r.eval()).getValue());
	}

}
