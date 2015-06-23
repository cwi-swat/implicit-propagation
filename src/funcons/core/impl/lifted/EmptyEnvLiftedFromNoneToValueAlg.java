package funcons.core.impl.lifted;

import funcons.core.experiments.EmptyEnvAlg;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalValueRetEnv;

public interface EmptyEnvLiftedFromNoneToValueAlg extends EmptyEnvAlg<IEvalValueRetEnv>{

	EmptyEnvAlg<IEvalBasicRetEnv> emptyEnvAlg();
	
	@Override
	public default IEvalValueRetEnv emptyEnv() {
		return  (val) -> emptyEnvAlg().emptyEnv().eval();
	}
	
}