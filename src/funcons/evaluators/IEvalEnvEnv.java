package funcons.evaluators;

import funcons.entities.Abstraction;
import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalEnvEnv extends Abstraction<Env<Value>, Env<Value>>{
	Env<Value> eval(Env<Value> env);
}