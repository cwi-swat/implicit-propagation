package funcons.evaluators;

import funcons.entities.Env;
import funcons.entities.Value;

@FunctionalInterface
interface IEvalBasicEnv{
	Env<Value> eval();
}