package funcons.core.impl.basic;

import funcons.core.experiments.ScopeAlg;
import funcons.entities.Env;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalEnv;

public interface ConcreteScopeAlg extends ScopeAlg<IEvalBasicRetEnv, IEvalEnv>{
	@Override
	public default IEvalEnv scope(IEvalBasicRetEnv d, IEvalEnv e) {
		return rho -> {
			Env<Value> rho1 = d.eval();
			return e.eval(rho.overrideBy(rho1));
		};
	}
}