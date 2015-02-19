package funcons.core.impl.envstorevalue;

import funcons.core.AssignAlg;
import funcons.entities.Cell;
import funcons.entities.Skip;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteAssignEnvStoreValAlg extends AssignAlg<IEvalEnvStoreVal>{

	@Override
	default IEvalEnvStoreVal assign(IEvalEnvStoreVal e1, IEvalEnvStoreVal e2) {
		return (env, store, value) -> {
			Value v2 = e2.eval(env, store, value);
			Value v1 = e1.eval(env, store, value);
			store.update((Cell) v1, v2);
			return Skip.getInstance();
		};
	}

	@Override
	default IEvalEnvStoreVal assignedValue(IEvalEnvStoreVal e) {
		return (env,store, value) ->{
			Value v = e.eval(env, store, value);
			return store.deref((Cell) v);
		};
	}
	
}