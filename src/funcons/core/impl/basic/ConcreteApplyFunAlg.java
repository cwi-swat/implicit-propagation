package funcons.core.impl.basic;

import funcons.core.experiments.ApplyAlg;
import funcons.core.impl.lifted.SupplyFunAlgLiftedFromValueToEnv;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;

public interface ConcreteApplyFunAlg extends ApplyAlg<IEvalEnvValue, IEvalEnvValue, IEvalBasic>,
	ConcreteAbsFunAlg, SupplyFunAlgLiftedFromValueToEnv{

}
