package funcons.core.impl.envstorevalue;

import funcons.core.SeqAlg;
import funcons.entities.Skip;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteSeqEnvStoreValAlg extends SeqAlg<IEvalEnvStoreVal, IEvalEnvStoreVal>{


	@Override
	default IEvalEnvStoreVal seq(IEvalEnvStoreVal c1, IEvalEnvStoreVal c2) {
		return (esv) -> {
			if (c1.eval(esv) instanceof Skip){
				return c2.eval(esv);
			}
			else throw new RuntimeException("Stuck");
		};
	}


}