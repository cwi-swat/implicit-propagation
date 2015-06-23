package funcons.core.impl.basic;

import funcons.core.experiments.AbsAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValueRetEnv;

// cannot lift this
public interface ConcreteAbsPattAlg extends
		AbsAlg<IEvalEnvValueRetEnv, IEvalBasic> {

	// AbsAlg<IEvalBasicRetEnv, IEvalBasic> absAlg();

	@Override
	public default IEvalBasic abs(IEvalEnvValueRetEnv x) {
		return () -> {
			return x;
		};
	}

	@Override
	public default IEvalEnvValueRetEnv getAbstracted(IEvalBasic x) {
		return (IEvalEnvValueRetEnv) x.eval();
	}
}