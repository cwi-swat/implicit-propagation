package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Store;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalEnvStoreVal{
	Value eval(Env<Value> e, Store s, Value v);
}