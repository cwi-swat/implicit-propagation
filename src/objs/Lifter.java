package objs;

import modules.arith.IEvalBase;
import modules.binding.Env;
import modules.binding.IEvalBinding;

@Lift(from=IEvalBase.class, to=IEvalBinding.class)
public class Lifter {
	private Env env;
	
	IEvalBinding lift(IEvalBase e) {
		return (env) -> { this.env = env; return e.eval(); };
	}
	
	IEvalBase lower(IEvalBinding e) {
		return () -> e.eval(this.env);
	}
}