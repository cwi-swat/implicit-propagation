package funcons.core.impl.env;

import funcons.core.ArithAlg;
import funcons.entities.Int;
import funcons.evaluators.IEvalBasic;

public class ConcreteArithAlg implements ArithAlg<IEvalBasic> {

	@Override
	public IEvalBasic add(IEvalBasic e1, IEvalBasic e2) {
		return () -> new Int(((Int) e1.eval()).getValue() + ((Int) e1.eval()).getValue());
	}

}
