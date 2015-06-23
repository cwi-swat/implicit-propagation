package funcons.core.impl.basic;

import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalEnv;


public interface ConcreteClosureAlg extends ClosureAlg<IEvalEnv, IEvalBasicRetEnv>{

	@Override
	public default IEvalEnv closure(IEvalEnv x, IEvalBasicRetEnv rho) {
		return (ignore) -> x.eval(rho.eval());
	}
	
}