package funcons.evaluators;

import funcons.entities.Abstraction;
import funcons.entities.Env;
import funcons.entities.None;
import funcons.entities.Value;

public interface IEvalBasicRetEnv extends Abstraction<None, Env<Value>>{
	Env<Value> eval(None none);
}