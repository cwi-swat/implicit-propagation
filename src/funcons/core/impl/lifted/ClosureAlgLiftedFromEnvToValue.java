package funcons.core.impl.lifted;

import funcons.core.impl.basic.ClosureAlg;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalEnv;
import funcons.evaluators.IEvalEnvValue;

public interface ClosureAlgLiftedFromEnvToValue extends ClosureAlg<IEvalEnvValue, IEvalBasicRetEnv>{
	
	ClosureAlg<IEvalEnv, IEvalBasicRetEnv> closureAlg();
	
	public default IEvalEnvValue closure(IEvalEnvValue x, IEvalBasicRetEnv rho) {
		return (env, val) -> closureAlg().closure((e)->x.eval(e, val), rho).eval(env);
	}
}