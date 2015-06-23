package funcons.core.impl.lifted;

import funcons.core.experiments.MatchAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;

public interface MatchAlgLiftedFromValueToEnv extends MatchAlg<IEvalEnvValue, IEvalEnvValueRetEnv, IEvalBasic>,
	ApplyPattAlgLiftedFromValueToEnv{
	
	
}