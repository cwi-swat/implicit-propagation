package funcons.core.impl.basic;

import funcons.core.IfWhileTrueAlg;
import funcons.entities.Bool;
import funcons.entities.Skip;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;

public interface ConcreteIfWhileTrueAlg extends IfWhileTrueAlg<IEvalBasic, IEvalBasic>,
	ConcreteSeqAlg, ConcreteSkipAlg{

	@Override
	default IEvalBasic ifTrue(IEvalBasic e, IEvalBasic c1, IEvalBasic c2) {
		return () -> {
			Value v = e.eval();
			if (v instanceof Bool){
				if (((Bool) v).getValue())
					return c1.eval();
				else {
					return c2.eval();
				}
			}
			else 
				throw new RuntimeException("Stuck");
		};
	}

}
