package funcons.core.impl.lifted;

import funcons.core.SupplyAlg;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

public interface SupplyPattAlgLiftedFromValueToEnv extends
		SupplyAlg<IEvalEnvValue, IEvalEnvValueRetEnv> {
	SupplyAlg<IEvalValue, IEvalValueRetEnv> supplyAlg();

	@Override
	public default IEvalEnvValueRetEnv supply(IEvalEnvValue v, IEvalEnvValueRetEnv x) {
		return (e, v_) -> supplyAlg().supply((i)->v.eval(e, i), (i)->x.eval(e, i)).eval(v_);
	}
}