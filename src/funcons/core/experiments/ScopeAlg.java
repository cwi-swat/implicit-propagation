package funcons.core.experiments;

import funcons.entities.Env;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnv;
import funcons.evaluators.IEvalEnvEnv;

public interface ScopeAlg<D,X> {
	// Caml Light
	X scope(D rho, X e);
	
}

class ConcreteScopeAlg implements ScopeAlg<IEvalEnvEnv, IEvalEnv>{


	@Override
	public IEvalEnv scope(IEvalEnvEnv d, IEvalEnv e) {
		return rho -> {
			Env<Value> rho1 = d.eval(rho);
			return e.eval(rho.overrideBy(rho1));
		};
	}

	
}