package funcons.core.impl.basic;

import funcons.core.SupplyAlg;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

public interface ConcreteSupplyPattAlg extends SupplyAlg<IEvalValue, IEvalValueRetEnv> {

	@Override
	public default IEvalValueRetEnv supply(IEvalValue e, IEvalValueRetEnv x){
		return (v) -> x.eval(e.eval(v));
	}

}
