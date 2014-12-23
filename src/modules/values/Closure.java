package modules.values;

import modules.binding.Env;
import modules.binding.IEvalBinding;

public class Closure implements Value {
	final private Env env;
	final private IEvalBinding func;
	private String x;

	public Closure(Env env, String x, IEvalBinding func) {
		this.env = env;
		this.x = x;
		this.func = func;
	}
	
	public Value apply(Value v) {
		return func.eval(env.bind(x, v));
	}
	
}
