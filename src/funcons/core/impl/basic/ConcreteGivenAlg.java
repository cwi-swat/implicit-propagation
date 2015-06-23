package funcons.core.impl.basic;

import funcons.core.GivenAlg;
import funcons.evaluators.IEvalValue;

public interface ConcreteGivenAlg extends GivenAlg<IEvalValue>{
	@Override
	default IEvalValue given() {
		return (val) -> val;
	}
}