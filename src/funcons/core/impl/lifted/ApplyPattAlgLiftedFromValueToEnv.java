package funcons.core.impl.lifted;

import funcons.core.experiments.ApplyAlg;
import funcons.core.impl.basic.ConcreteAbsPattAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;

public interface ApplyPattAlgLiftedFromValueToEnv extends 
	ApplyAlg<IEvalEnvValue, IEvalEnvValueRetEnv, IEvalBasic>,
	SupplyPattAlgLiftedFromValueToEnv,
	ConcreteAbsPattAlg {
	
}