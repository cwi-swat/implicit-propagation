package funcons.core.impl.lifted;

import funcons.core.experiments.AbsAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalValueRetEnv;

// cannot lift this
public interface PattAbsAlgLiftedFromNoneToValue extends
		AbsAlg<IEvalValueRetEnv, IEvalBasic> {

	//AbsAlg<IEvalBasicRetEnv, IEvalBasic> absAlg();

	@Override
	public default IEvalBasic abs(IEvalValueRetEnv x) {
		return () -> x;
	}

	@Override
	public default IEvalValueRetEnv getAbstracted(IEvalBasic x) {
		return  (IEvalValueRetEnv) x.eval();
	}

}