package funcons.core.impl.lifted;

import funcons.core.BoundAlg;
import funcons.entities.I;
import funcons.evaluators.IEvalEnv;
import funcons.evaluators.IEvalEnvValue;

public interface BoundAlgLiftedFromEnvToValue extends BoundAlg<IEvalEnvValue>{
	
	BoundAlg<IEvalEnv> boundAlg();
	
	@Override
	public default IEvalEnvValue boundValue(I i) {
		return (e,v) -> boundAlg().boundValue(i).eval(e);
	}
}