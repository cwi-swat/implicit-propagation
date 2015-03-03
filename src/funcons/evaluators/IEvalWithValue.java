package funcons.evaluators;

import funcons.entities.Abstraction;
import funcons.entities.Value;

public interface IEvalWithValue extends Abstraction<Value,Value>{
	Value eval(Value v);
}