package desugaring.lifted;

import modules.storage.IEvalStore;
import modules.storage.StoreAlg;
import desugaring.IEvalBindingAndStorage;

public interface LiftEvalStorage extends StoreAlg<IEvalBindingAndStorage>, AddEnvToStorage {
	StoreAlg<IEvalStore> storeAlg();
	
	@Override
	default IEvalBindingAndStorage create() {
		return lift(storeAlg().create());
	}
	
	@Override
	default IEvalBindingAndStorage deref(IEvalBindingAndStorage x) {
		return lift(storeAlg().deref(lower(x)));
	}
	
	@Override
	default IEvalBindingAndStorage update(IEvalBindingAndStorage x, IEvalBindingAndStorage e) {
		return lift(storeAlg().update(lower(x), lower(e)));
	}
}
