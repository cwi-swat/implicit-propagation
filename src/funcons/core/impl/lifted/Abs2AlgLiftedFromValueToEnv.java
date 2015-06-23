package funcons.core.impl.lifted;

import funcons.core.experiments.Abs2Alg;
import funcons.core.impl.basic.ConcreteAbsPattAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;

public interface Abs2AlgLiftedFromValueToEnv extends 
	Abs2Alg<IEvalEnvValue, IEvalEnvValue, IEvalEnvValueRetEnv, IEvalBasic>,
	ConcreteAbsPattAlg,
	ScopeAlgLiftedFromEnvToValue,
	SupplyPattAlgLiftedFromValueToEnv{
	
	
}