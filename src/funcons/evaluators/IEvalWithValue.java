package funcons.evaluators;

import funcons.entities.Value;

@FunctionalInterface
public interface IEvalWithValue{
	Value eval(Value v);
}