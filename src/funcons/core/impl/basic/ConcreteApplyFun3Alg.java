package funcons.core.impl.basic;

import funcons.core.experiments.ApplyAlg;
import funcons.core.impl.lifted.SupplyFunAlgLiftedFromValueToEnv;
import funcons.entities.AbsValue;
import funcons.evaluators.IEvalEnvValue;

public interface ConcreteApplyFun3Alg extends ApplyAlg<IEvalEnvValue, IEvalEnvValue, AbsValue<IEvalEnvValue>>,
	ConcreteAbsFun3Alg, SupplyFunAlgLiftedFromValueToEnv{

}
