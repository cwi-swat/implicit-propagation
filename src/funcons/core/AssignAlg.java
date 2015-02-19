package funcons.core;

import funcons.entities.Cell;
import funcons.entities.Skip;
import funcons.entities.Value;
import funcons.evaluators.IEvalStore;


public interface AssignAlg<E> {
	E assign(E e1, E e2);
	E assignedValue(E e);
}

class ConcreteAssignAlg implements AssignAlg<IEvalStore>{

	@Override
	public IEvalStore assign(IEvalStore e1, IEvalStore e2) {
		return store -> {
			Value v2 = e2.eval(store);
			Value v1 = e1.eval(store);
			store.update((Cell) v1, v2);
			return Skip.getInstance();
		};
	}

	@Override
	public IEvalStore assignedValue(IEvalStore e) {
		return store ->{
			Value v = e.eval(store);
			return store.deref((Cell) v);
		};
	}
	
}

