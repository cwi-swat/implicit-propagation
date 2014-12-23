package modules.binding;

import java.util.function.Function;

import modules.values.Value;

public class Closure implements Value {
	final private Env env;
	final private Function<Env, Value> func;
	private String x;

	public Closure(Env env, String x, Function<Env, Value> func) {
		this.env = env;
		this.x = x;
		this.func = func;
	}
	
	public Value apply(Value v) {
		return func.apply(env.bind(x, v));
	}
	
}
