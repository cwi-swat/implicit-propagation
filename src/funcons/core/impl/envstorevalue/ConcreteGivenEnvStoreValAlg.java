package funcons.core.impl.envstorevalue;

import funcons.core.GivenAlg;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteGivenEnvStoreValAlg extends GivenAlg<IEvalEnvStoreVal>{
	@Override
	default IEvalEnvStoreVal given() {
		return (e,s,v) -> v;
	}
}