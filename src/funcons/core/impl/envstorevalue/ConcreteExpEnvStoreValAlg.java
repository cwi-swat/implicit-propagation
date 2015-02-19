package funcons.core.impl.envstorevalue;

import funcons.core.ExpAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteExpEnvStoreValAlg extends ExpAlg<IEvalEnvStoreVal>{

	@Override
	default IEvalEnvStoreVal constant(Value v) {
		return (rho, sigma, value) -> v;
	}

	
}