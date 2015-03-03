package funcons.evaluators;

import funcons.entities.Abstraction;
import funcons.entities.Env;
import funcons.entities.Store;
import funcons.entities.Value;
import funcons.util.Triple;

public interface IEvalEnvStoreVal extends Abstraction<Triple<Env<Value>, Store, Value>, Value>{

}