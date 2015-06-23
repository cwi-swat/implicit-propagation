package funcons.core.impl.basic;

import funcons.core.experiments.Abs2Alg;
import funcons.core.impl.lifted.GivenAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.MatchAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.ScopeAlgLiftedFromEnvToValue;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;

public interface ConcreteAbs2Alg 
	extends Abs2Alg<IEvalBasic, IEvalEnvValue, IEvalEnvValueRetEnv, IEvalBasic>, 
		ScopeAlgLiftedFromEnvToValue, MatchAlgLiftedFromValueToEnv, GivenAlgLiftedFromValueToEnv{

}
