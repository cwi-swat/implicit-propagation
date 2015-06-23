package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Store;
import funcons.entities.Value;

public interface IEvalEnvStoreValMultiRetEnv{
	Env<Value> eval(Env<Value> env, Store store, Value val);
}