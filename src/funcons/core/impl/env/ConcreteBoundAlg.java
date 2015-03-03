package funcons.core.impl.env;

import funcons.core.BoundAlg;
import funcons.entities.I;
import funcons.evaluators.IEvalEnv;

public class ConcreteBoundAlg implements BoundAlg<IEvalEnv>{

	@Override
	public IEvalEnv boundValue(I i) {
		return e -> e.lookup(i.toString());
	}
}