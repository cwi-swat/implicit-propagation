package desugaring.lifted;

import modules.binding.IEvalBinding;
import runtime.RT;
import desugaring.IEvalBindingAndStorage;

public interface AddStoreToBinding {
	default IEvalBinding removeStoreToBinding(IEvalBindingAndStorage e) {
		return (env) -> e.eval(env, RT.peek("store"));
	}

	default IEvalBindingAndStorage addStoreToBinding(IEvalBinding e) {
		return (env, store) -> RT.with("store", store, () -> e.eval(env));
	}

}
