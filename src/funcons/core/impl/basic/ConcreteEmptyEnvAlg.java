package funcons.core.impl.basic;

import funcons.core.experiments.EmptyEnvAlg;
import funcons.entities.Env;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasicRetEnv;

public interface ConcreteEmptyEnvAlg extends EmptyEnvAlg<IEvalBasicRetEnv>{

	@Override
	public default IEvalBasicRetEnv emptyEnv() {
		return  () -> new Env<Value>();
	}
	
}