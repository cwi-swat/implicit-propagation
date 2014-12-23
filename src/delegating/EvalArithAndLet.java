package delegating;

import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;
import runtime.RT;


interface EvalArithWithBinding extends ArithAlg<IEvalBinding> {
	ArithAlg<IEvalBase> alg();
	
	static IEvalBase lower(IEvalBinding e) {
		return () -> e.eval(RT.peek("env")); 
	}
	
	static IEvalBinding lift(IEvalBase e) {
		return (env) -> RT.with("env", env, e::eval);
	}
	
	@Override
	default IEvalBinding add(IEvalBinding l, IEvalBinding r) {
		return lift(alg().add(lower(l), lower(r)))::eval;
	}
	
	@Override
	default IEvalBinding lit(int n) {
		return lift(alg().lit(n))::eval;
	}
}


