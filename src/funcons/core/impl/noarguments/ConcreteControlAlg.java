package funcons.core.impl.noarguments;

import funcons.core.ControlAlg;
import funcons.entities.Bool;
import funcons.entities.Skip;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;

public interface ConcreteControlAlg extends ControlAlg<IEvalBasic, IEvalBasic>,
	ConcreteSeqAlg{

	@Override
	default IEvalBasic skip() {
		return  ()-> Skip.getInstance();
	}

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
