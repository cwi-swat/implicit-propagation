package funcons.core.impl.lifted;

import funcons.core.SupplyAlg;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalValue;

public interface SupplyFunAlgLiftedFromValueToEnv extends
		SupplyAlg<IEvalEnvValue, IEvalEnvValue> {
	SupplyAlg<IEvalValue, IEvalValue> supplyAlg();

	@Override
	public default IEvalEnvValue supply(IEvalEnvValue v, IEvalEnvValue x) {
		return (e, v_) -> supplyAlg().supply((i)->v.eval(e, i), (i)->x.eval(e, i)).eval(v_);
	}
}