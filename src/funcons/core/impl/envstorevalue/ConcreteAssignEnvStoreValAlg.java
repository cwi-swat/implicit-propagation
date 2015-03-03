package funcons.core.impl.envstorevalue;

import funcons.core.AssignAlg;
import funcons.entities.Cell;
import funcons.entities.Skip;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteAssignEnvStoreValAlg extends AssignAlg<IEvalEnvStoreVal>{

	@Override
	default IEvalEnvStoreVal assign(IEvalEnvStoreVal e1, IEvalEnvStoreVal e2) {
		return (esv) -> {
			Value v2 = e2.eval(esv);
			Value v1 = e1.eval(esv);
			esv._2().update((Cell) v1, v2);
			return Skip.getInstance();
		};
	}

	@Override
	default IEvalEnvStoreVal assignedValue(IEvalEnvStoreVal e) {
		return (esv) ->{
			Value v = e.eval(esv);
			return esv._2().deref((Cell) v);
		};
	}
	
}