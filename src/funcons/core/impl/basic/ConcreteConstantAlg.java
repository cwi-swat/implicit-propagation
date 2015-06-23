package funcons.core.impl.basic;

import funcons.core.ConstantAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;

public interface ConcreteConstantAlg extends ConstantAlg<IEvalBasic>{

	@Override
	default IEvalBasic constant(Value v) {
		return () -> v;
	}

	
}