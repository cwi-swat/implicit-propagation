package funcons.core.impl.basic;

import funcons.core.experiments.CloseAlg;
import funcons.core.impl.lifted.ClosureAlgLiftedFromEnvToValue;
import funcons.evaluators.IEvalEnvValue;

public interface ConcreteCloseAlg
		extends
		ConcreteAbsFunAlg,
		ClosureAlgLiftedFromEnvToValue, CloseAlg<IEvalEnvValue> {
	@Override
	public default IEvalEnvValue close(IEvalEnvValue x ) {
		return (rho, v) -> abs(closure(x, ()-> rho));
	}
}