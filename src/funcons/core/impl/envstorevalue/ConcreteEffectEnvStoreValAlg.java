package funcons.core.impl.envstorevalue;

import funcons.core.EffectAlg;
import funcons.entities.Skip;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteEffectEnvStoreValAlg extends EffectAlg<IEvalEnvStoreVal, IEvalEnvStoreVal> {
	@Override
	default IEvalEnvStoreVal effect(IEvalEnvStoreVal e) {
		return (rho, store, value) -> {
			e.eval(rho, store, value);
			return Skip.getInstance();
		};
	}

}
