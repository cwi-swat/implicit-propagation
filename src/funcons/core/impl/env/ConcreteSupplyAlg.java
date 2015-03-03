package funcons.core.impl.env;

import funcons.core.SupplyAlg;
import funcons.evaluators.IEvalWithValue;

public class ConcreteSupplyAlg implements SupplyAlg<IEvalWithValue, IEvalWithValue> {

	@Override
	public IEvalWithValue supply(IEvalWithValue e, IEvalWithValue x) {
		return v -> x.eval(e.eval(v));
	}

}
