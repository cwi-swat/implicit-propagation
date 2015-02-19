package funcons.core.impl.envstorevalue;

import funcons.core.BoundAlg;
import funcons.entities.I;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteBoundEnvStoreValAlg extends BoundAlg<IEvalEnvStoreVal>{

	@Override
	default IEvalEnvStoreVal boundValue(I i) {
		return (env,s,v) -> env.lookup(i.toString());
	}
}