package ordinary.binding;

import modules.binding.Env;
import modules.values.Value;

public class Let implements BindingExpr {
	private String x;
	private IEvalBinding e;
	private IEvalBinding body;
	
	public Let(String x, IEvalBinding e, IEvalBinding body) {
		this.x = x;
		this.e = e;
		this.body = body;
	}
	
	@Override
	public Value eval(Env env) {
		return body.eval(env.bind(x, e.eval(env)));
	}

}
