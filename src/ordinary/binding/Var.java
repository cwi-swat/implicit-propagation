package ordinary.binding;

import modules.binding.Env;
import modules.values.Value;

public class Var implements BindingExpr {
	private String x;

	public Var(String x) {
		this.x = x;
	}
	
	@Override
	public Value eval(Env env) {
		return env.lookup(x);
	}

}
