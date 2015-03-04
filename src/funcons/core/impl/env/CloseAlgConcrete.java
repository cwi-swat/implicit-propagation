package funcons.core.impl.env;

import funcons.core.experiments.AbsAlg;
import funcons.core.experiments.CloseAlg;
import funcons.core.experiments.ClosureAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnv;
import funcons.evaluators.IEvalEnvEnv;

interface CloseAlgConcrete
		extends
		AbsAlg<Value, IEvalEnv>,
		ClosureAlg<IEvalEnv, IEvalEnvEnv>, CloseAlg<IEvalEnv> {
	default IEvalEnv close(IEvalEnv x) {
		return (rho) -> {
			return abs(closure(x, dummy -> rho));
		};
	}
}