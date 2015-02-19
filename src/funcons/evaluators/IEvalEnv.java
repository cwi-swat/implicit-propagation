package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalEnv{
	Value eval(Env<Value> env);
}