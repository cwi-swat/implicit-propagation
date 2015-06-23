package funcons.core.impl.basic;

import funcons.core.impl.lifted.Abs2AlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.AnyAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.ApplyPattAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.ArithAlgLiftedFromEnvToValue;
import funcons.core.impl.lifted.BindAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.BoundAlgLiftedFromEnvToValue;
import funcons.core.impl.lifted.ConstantAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.ScopeAlgLiftedFromEnvToValue;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;

public interface ConcretePatternsAlg extends
		PatternsAlg<IEvalEnvValue, IEvalEnvValueRetEnv, IEvalBasic>,
		ApplyPattAlgLiftedFromValueToEnv,
		AnyAlgLiftedFromValueToEnv,
		BindAlgLiftedFromValueToEnv,
		ConstantAlgLiftedFromValueToEnv,
		Abs2AlgLiftedFromValueToEnv,
		ScopeAlgLiftedFromEnvToValue,
		BoundAlgLiftedFromEnvToValue,
		ArithAlgLiftedFromEnvToValue
		{
	
}
