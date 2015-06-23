package funcons.core.impl.basic;

import funcons.core.SupplyAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalValue;

public interface ConcreteSupplyFunAlg extends SupplyAlg<IEvalValue, IEvalValue> {

	@Override
	public default IEvalValue supply(IEvalValue v, IEvalValue x) {
		return (ignore) -> {
			Value val = v.eval(ignore);
			Value r = x.eval(val);
			return r;
		};
	}

}