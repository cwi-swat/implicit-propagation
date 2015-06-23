package funcons.evaluators;


import funcons.entities.Value;

@FunctionalInterface
public interface IEvalValue extends Value{
	Value eval(Value v);
}