package funcons.core.impl.env;

import funcons.core.experiments.EmptyEnvAlg;
import funcons.entities.Env;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasicRetEnv;

class ConcreteEmptyEnvAlg implements EmptyEnvAlg<IEvalBasicRetEnv>{

	@Override
	public IEvalBasicRetEnv emptyEnv() {
		return  (none) -> new Env<Value>();
	}
	
}