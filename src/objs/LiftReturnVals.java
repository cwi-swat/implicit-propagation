package objs;

import modules.binding.Env;
import modules.binding.IEvalBinding;
import modules.jumps.Pair;
import modules.storage.Store;
import modules.values.Value;

interface IEvalStore {
	Pair<Store, Value> eval(Store store, Env env);
}

public class LiftReturnVals implements ILift<IEvalBinding,IEvalStore>{
	// TODO: this doesn't propagate the store correctly!!!
	private Store store;

	public IEvalStore lift(IEvalBinding e) {
		return (store, env) -> { 
			this.store = store; 
			return new Pair<>(store, e.eval(env)); 
		};
	}
	
	public IEvalBinding lower(IEvalStore e) {
		return (env) -> e.eval(this.store, env).b();
	}
	
}
