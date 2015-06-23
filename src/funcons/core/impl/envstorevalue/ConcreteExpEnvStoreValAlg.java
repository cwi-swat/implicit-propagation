package funcons.core.impl.envstorevalue;

import funcons.core.ConstantAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteExpEnvStoreValAlg extends ConstantAlg<IEvalEnvStoreVal>{

	@Override
	default IEvalEnvStoreVal constant(Value v) {
		return esv -> v;
	}

	
}