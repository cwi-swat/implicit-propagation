package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Store;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalEnvStoreValMulti extends Value{
	Value eval(Env<Value> env, Store store, Value v);
}