package desugaring;

import modules.binding.Env;
import modules.storage.Store;
import modules.values.Value;

public interface IEvalBindingAndStorage {
	Value eval(Env env, Store store);
}
