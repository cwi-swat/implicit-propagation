package objs;

import modules.arith.IEvalBase;
import modules.binding.Env;
import modules.binding.IEvalBinding;

public class Lifter implements ILift<IEvalBase, IEvalBinding> {
	private Env env;
	
	public IEvalBinding lift(IEvalBase e) {
		return (env) -> { this.env = env; return e.eval(); };
	}
	
	public IEvalBase lower(IEvalBinding e) {
		return () -> e.eval(this.env);
	}
}