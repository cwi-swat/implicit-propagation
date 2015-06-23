package funcons.core.impl.basic;

import funcons.core.BoundAlg;
import funcons.entities.I;
import funcons.evaluators.IEvalEnv;

public interface ConcreteBoundAlg extends BoundAlg<IEvalEnv>{

	@Override
	public default IEvalEnv boundValue(I i) {
		return e -> e.lookup(i.toString());
	}
}