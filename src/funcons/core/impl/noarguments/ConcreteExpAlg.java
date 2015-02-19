package funcons.core.impl.noarguments;

import funcons.core.ExpAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;

public interface ConcreteExpAlg extends ExpAlg<IEvalBasic>{

	@Override
	default IEvalBasic constant(Value v) {
		return () -> v;
	}

	
}