package funcons.core.impl.lifted;

import funcons.core.experiments.EmptyEnvAlg;
import funcons.evaluators.IEvalEnvValueRetEnv;
import funcons.evaluators.IEvalValueRetEnv;

public interface EmptyEnvAlgLiftedFromValueToEnv extends EmptyEnvAlg<IEvalEnvValueRetEnv>{

	EmptyEnvAlg<IEvalValueRetEnv> emptyEnvAlg();
	
	@Override
	public default IEvalEnvValueRetEnv emptyEnv() {
		return  (env, val) -> emptyEnvAlg().emptyEnv().eval(val);
	}
	
}