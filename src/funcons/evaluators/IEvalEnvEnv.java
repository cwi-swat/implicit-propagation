package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

public interface IEvalEnvEnv{
	Env<Value> eval(Env<Value> env);
}