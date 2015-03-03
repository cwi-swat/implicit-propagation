package funcons.core.experiments;

import funcons.entities.None;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalEnv;

public interface ClosureAlg<X, D>{
	X closure(X x, D rho);
}
class ConcreteClosureAlg implements ClosureAlg<IEvalEnv, IEvalBasicRetEnv>{

	@Override
	public IEvalEnv closure(IEvalEnv x, IEvalBasicRetEnv rho) {
		return (none) -> {
			Value v = x.eval(rho.eval(new None()));
			return v;
		};
	}
	
}