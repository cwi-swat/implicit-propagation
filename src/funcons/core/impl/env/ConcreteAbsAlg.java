package funcons.core.impl.env;

import funcons.core.experiments.AbsAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalBasicRetSame;

class ConcreteAbsAlg implements AbsAlg<IEvalBasic, IEvalBasicRetSame>{

	@Override
	public IEvalBasicRetSame abs(IEvalBasic e) {
		return () -> e;
	}

	@Override
	public IEvalBasic getAbstracted(IEvalBasicRetSame f) {
		return f.eval();
	}
}