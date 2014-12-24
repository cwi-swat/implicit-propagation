package closures;

import modules.storage.IEvalStore;
import modules.storage.StoreAlg;

public interface EvalStorageWithBinding extends StoreAlg<IEvalBindingAndStorage> {
	StoreAlg<IEvalStore> storeAlg();

	@Override
	default IEvalBindingAndStorage create() {
		return (env, store) -> storeAlg().create().eval(store);
	}
	
	@Override
	default IEvalBindingAndStorage deref(IEvalBindingAndStorage x) {
		return (env, store) -> storeAlg().deref((s) -> x.eval(env, s)).eval(store);
	}
	
	@Override
	default IEvalBindingAndStorage update(IEvalBindingAndStorage x, IEvalBindingAndStorage e) {
		return (env, store) -> storeAlg().update(s -> x.eval(env, s), s -> e.eval(env, s)).eval(store);
	}
}
