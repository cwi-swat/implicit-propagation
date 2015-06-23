package funcons.core.impl.basic;

import funcons.core.experiments.MatchAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

public interface ConcreteMatchAlg extends MatchAlg<IEvalValue, IEvalValueRetEnv, IEvalBasic>,
	ConcreteApplyPattAlg
	{
}
