package funcons.evaluators;

import funcons.entities.Abstraction;
import funcons.entities.Env;
import funcons.entities.Failure;
import funcons.entities.Store;
import funcons.entities.Value;
import funcons.util.Quadruple;

public interface IEvalEnvStoreValFailRetEnv extends Abstraction<Quadruple<Env<Value>, Store, Value, Failure>, Env<Value>> {

}
