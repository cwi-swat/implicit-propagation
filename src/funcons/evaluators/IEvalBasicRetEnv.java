package funcons.evaluators;


import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalBasicRetEnv extends Value{
	Env<Value> eval();
}