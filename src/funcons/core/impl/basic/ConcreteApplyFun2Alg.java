package funcons.core.impl.basic;

import funcons.core.experiments.ApplyAlg;
import funcons.core.impl.lifted.SupplyFunAlgLiftedFromValueToEnv;
import funcons.evaluators.IEvalEnvValue;

public interface ConcreteApplyFun2Alg extends ApplyAlg<IEvalEnvValue, IEvalEnvValue, IEvalEnvValue>,
	ConcreteAbsFun2Alg, SupplyFunAlgLiftedFromValueToEnv{

}
