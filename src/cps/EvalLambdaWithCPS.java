package cps;

import modules.binding.IEvalBinding;
import modules.lambda.LambdaAlg;

public interface EvalLambdaWithCPS extends LambdaAlg<IEvalBindingCPS>{
	LambdaAlg<IEvalBinding> alg();
	
	@Override
	default IEvalBindingCPS lambda(String x, IEvalBindingCPS body) {
//		return (env, k) -> body.eval(env, (env2, k2) -> k.accept(env2, alg().lambda(x, en -> k2).eval(env)));
		//return (env, k) -> k.accept(alg().lambda(x, env2 -> body.eval(env2, k)).eval(env);
		
		return (env, k) -> alg().lambda(x, e -> body.eval(e, k)).eval(env);
	}
	
//	default IEvalBindingCPS pos(IEvalBindingCPS arg) {
//		return (env, k) -> alg().pos(e -> arg.eval(e, k)).eval(env);
//	}
	
	@Override
	default IEvalBindingCPS apply(IEvalBindingCPS e1, IEvalBindingCPS e2) {
		//return (env, k) -> e1.eval(env, (env2, k2) -> e2.eval(env2, (env3, k3) -> k.accept(env3, alg().apply(en1 -> k2, en2 -> k3).eval(env3))));
		return (env, k) -> e1.eval(env, k2 -> e2.eval(env, k3 -> k.apply(alg().apply(en1 -> k2, en2 -> k3).eval(env))));
		//return (env, k) -> k.apply(alg().apply(x, y).eval(env));
	}
}
