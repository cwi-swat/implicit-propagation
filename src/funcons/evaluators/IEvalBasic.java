package funcons.evaluators;

import funcons.entities.Value;

@FunctionalInterface
public interface IEvalBasic extends Value{
	Value eval();
}