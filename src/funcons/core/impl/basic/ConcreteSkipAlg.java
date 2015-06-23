package funcons.core.impl.basic;

import modules.skip.SkipAlg;
import funcons.entities.Skip;
import funcons.evaluators.IEvalBasic;

public interface ConcreteSkipAlg extends SkipAlg<IEvalBasic>{
	@Override
	default IEvalBasic skip() {
		return  ()-> Skip.getInstance();
	}


}
