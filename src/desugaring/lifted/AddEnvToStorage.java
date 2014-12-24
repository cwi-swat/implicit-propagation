package desugaring.lifted;

import modules.storage.IEvalStore;
import runtime.RT;
import desugaring.IEvalBindingAndStorage;

public interface AddEnvToStorage {
	default IEvalStore removeEnvToStorage(IEvalBindingAndStorage e) {
		return (store) -> e.eval(RT.peek("env"), store);
	}

	default IEvalBindingAndStorage addEnvToStorage(IEvalStore e) {
		return (env, store) -> RT.with("env", env, () -> e.eval(store));
	}

}
