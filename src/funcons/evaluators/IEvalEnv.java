package funcons.evaluators;

import funcons.entities.Abstraction;
import funcons.entities.Env;
import funcons.entities.Value;

public interface IEvalEnv extends Abstraction<Env<Value>, Value>{
	@Override
	Value eval(Env<Value> env);
}