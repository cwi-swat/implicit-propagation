package funcons.languages.experiments;

import funcons.core.SupplyAlg;
import funcons.core.impl.basic.ConcreteFunctions2Alg;
import funcons.core.impl.basic.ConcreteSupplyFunAlg;
import funcons.evaluators.IEvalValue;

public class CompleteFunctions2Alg implements
	ConcreteFunctions2Alg{

	@Override
	public SupplyAlg<IEvalValue, IEvalValue> supplyAlg() {
		return new ConcreteSupplyFunAlg() {
		};
	}
}
