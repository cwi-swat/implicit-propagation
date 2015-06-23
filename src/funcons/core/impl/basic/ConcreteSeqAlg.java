package funcons.core.impl.basic;

import funcons.core.SeqAlg;
import funcons.entities.Skip;
import funcons.evaluators.IEvalBasic;

public interface ConcreteSeqAlg extends SeqAlg<IEvalBasic, IEvalBasic>{


	@Override
	default IEvalBasic seq(IEvalBasic c1, IEvalBasic c2) {
		return () -> {
			if (c1.eval() instanceof Skip){
				return c2.eval();
			}
			else throw new RuntimeException("Stuck");
		};
	}
	
}