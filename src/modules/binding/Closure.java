package modules.binding;

import java.util.function.Function;

import modules.values.Value;

public class Closure implements Value {
	final private Function<Value, Value> func;

	public Closure(Function<Value, Value> func) {
		this.func = func;
	}
	
	public Value apply(Value v) {
		return func.apply(v);
	}
	
}
