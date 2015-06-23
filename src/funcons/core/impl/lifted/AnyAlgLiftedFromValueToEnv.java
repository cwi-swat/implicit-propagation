package funcons.core.impl.lifted;

import funcons.core.experiments.AnyAlg;
import funcons.core.impl.basic.ConcreteAbsPattAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValueRetEnv;

public interface AnyAlgLiftedFromValueToEnv extends AnyAlg<IEvalEnvValueRetEnv, IEvalBasic>,
	ConcreteAbsPattAlg,
	EmptyEnvAlgLiftedFromValueToEnv{
	
}