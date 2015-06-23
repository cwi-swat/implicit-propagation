package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalEnvValue extends Value{
	Value eval(Env<Value> e, Value v);
}
