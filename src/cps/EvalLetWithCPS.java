package cps;

import modules.binding.IEvalBinding;
import modules.binding.LetAlg;

public interface EvalLetWithCPS extends LetAlg<IEvalBindingCPS> {
	LetAlg<IEvalBinding> alg();
	
	@Override
	default IEvalBindingCPS let(String x, IEvalBindingCPS e, IEvalBindingCPS body) {
		return (env, k) -> e.eval(env, k2 -> alg().let(x, env2 -> k2, env3 -> body.eval(env3, k)).eval(env));
	}
	
	@Override
	default IEvalBindingCPS var(String x) {
		return (env, k) -> k.apply(alg().var(x).eval(env));
	}
}
