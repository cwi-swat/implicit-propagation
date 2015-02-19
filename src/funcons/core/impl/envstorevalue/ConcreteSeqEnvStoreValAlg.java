package funcons.core.impl.envstorevalue;

import funcons.core.SeqAlg;
import funcons.entities.Skip;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteSeqEnvStoreValAlg extends SeqAlg<IEvalEnvStoreVal, IEvalEnvStoreVal>{


	@Override
	default IEvalEnvStoreVal seq(IEvalEnvStoreVal c1, IEvalEnvStoreVal c2) {
		return (rho, store, value) -> {
			if (c1.eval(rho, store, value) instanceof Skip){
				return c2.eval(rho, store, value);
			}
			else throw new RuntimeException("Stuck");
		};
	}


}