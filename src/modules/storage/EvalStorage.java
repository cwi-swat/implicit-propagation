package modules.storage;

import modules.arith.Int;

public interface EvalStorage extends StoreAlg<IEvalStore> {
	@Override
	default IEvalStore create() {
		return store -> store.update(store.newCell(), new Int(0));
	}
	
	@Override
	default IEvalStore deref(IEvalStore x) {
		return store -> store.deref((Cell)x.eval(store));
	}
	
	@Override
	default IEvalStore update(IEvalStore x, IEvalStore e) {
		return store -> store.update((Cell)x.eval(store), e.eval(store));
	}
	
	@Override
	default IEvalStore seq(IEvalStore l, IEvalStore r) {
		return (store) -> {
			l.eval(store);
			return r.eval(store);
		};
	}
	
}
