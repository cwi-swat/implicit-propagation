package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalWithValueEnv{
	Env<Value> eval(Value v);
}