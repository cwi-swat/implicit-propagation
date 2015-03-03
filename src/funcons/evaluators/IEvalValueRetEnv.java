package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalValueRetEnv {
	Env<Value> eval(Value v);
}
