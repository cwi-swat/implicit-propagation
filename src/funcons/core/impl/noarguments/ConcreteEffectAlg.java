package funcons.core.impl.noarguments;

import funcons.core.EffectAlg;
import funcons.entities.Skip;
import funcons.evaluators.IEvalBasic;

public interface ConcreteEffectAlg extends EffectAlg<IEvalBasic, IEvalBasic> {


	@Override
	public default IEvalBasic effect(IEvalBasic e) {
		return () -> {
			e.eval();
			return Skip.getInstance();
		};
	}


}
