package funcons.languages.experiments;

import funcons.core.SupplyAlg;
import funcons.core.impl.basic.ConcreteFunctions3Alg;
import funcons.core.impl.basic.ConcreteSupplyFunAlg;
import funcons.evaluators.IEvalValue;

public class CompleteFunctions3Alg implements
	ConcreteFunctions3Alg{

	@Override
	public SupplyAlg<IEvalValue, IEvalValue> supplyAlg() {
		return new ConcreteSupplyFunAlg() {
		};
	}
}
