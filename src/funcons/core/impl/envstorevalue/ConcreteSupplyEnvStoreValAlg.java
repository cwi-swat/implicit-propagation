package funcons.core.impl.envstorevalue;

import funcons.core.SupplyAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteSupplyEnvStoreValAlg extends SupplyAlg<IEvalEnvStoreVal, IEvalEnvStoreVal>{
	@Override
	default IEvalEnvStoreVal supply(IEvalEnvStoreVal e1, IEvalEnvStoreVal e2) {
		return (esv) ->{
			Value v1 = e1.eval(esv);
			Value v2 = e2.eval(esv.replace3(v1));
			return v2;
		};
	}
}