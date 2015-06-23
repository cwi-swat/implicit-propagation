package funcons.core.impl.envstorevalue;

import modules.skip.SkipAlg;
import funcons.entities.Skip;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteSkipEnvStoreValAlg extends SkipAlg<IEvalEnvStoreVal>{
	@Override
	default IEvalEnvStoreVal skip() {
		return esv -> Skip.getInstance();
	}
}
