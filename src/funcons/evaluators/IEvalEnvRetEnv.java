package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalEnvRetEnv extends Value{
	Env<Value> eval(Env<Value> env);
}