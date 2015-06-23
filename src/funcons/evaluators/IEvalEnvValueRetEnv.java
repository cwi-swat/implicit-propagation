package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalEnvValueRetEnv extends Value {
	Env<Value> eval(Env<Value> e, Value v);
}
