package modules.storage;


public interface EvalStorage extends StoreAlg<IEvalStore> {
	@Override
	default IEvalStore create() {
		return store -> store.newCell();
	}
	
	@Override
	default IEvalStore deref(IEvalStore x) {
		return store -> store.deref((Cell)x.eval(store));
	}
	
	@Override
	default IEvalStore update(IEvalStore x, IEvalStore e) {
		return store -> store.update((Cell)x.eval(store), e.eval(store));
	}
	
}
