package funcons.languages.experiments;

import funcons.core.SupplyAlg;
import funcons.core.impl.basic.ConcreteFunctionsAlg;
import funcons.core.impl.basic.ConcreteSupplyFunAlg;
import funcons.evaluators.IEvalValue;

public class CompleteFunctionsAlg implements
		ConcreteFunctionsAlg {

	@Override
	public SupplyAlg<IEvalValue, IEvalValue> supplyAlg() {
		return new ConcreteSupplyFunAlg() {
		};
	}
}
