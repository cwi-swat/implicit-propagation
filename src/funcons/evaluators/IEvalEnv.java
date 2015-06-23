package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalEnv extends Value{
	Value eval(Env<Value> env);
}