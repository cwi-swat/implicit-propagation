package funcons.core.impl.lifted;

import funcons.core.experiments.ScopeAlg;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalEnv;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;

public interface ScopeAlgLiftedFromEnvToValue extends ScopeAlg<IEvalEnvValueRetEnv, IEvalEnvValue>{
	ScopeAlg<IEvalBasicRetEnv, IEvalEnv> scopeAlg();
	
	@Override
	public default IEvalEnvValue scope(IEvalEnvValueRetEnv rho, IEvalEnvValue e) {
		return (env, val) -> scopeAlg().scope(() -> rho.eval(env, val), (env_) -> e.eval(env_, val)).eval(env);
	}
	
}