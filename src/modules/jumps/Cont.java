package modules.jumps;

import modules.binding.Env;
import modules.binding.IEvalBinding;
import modules.values.Value;

public class Cont implements Value {

	private IEvalBinding body;

	public Cont(IEvalBinding body) {
		this.body = body;
	}

	public Value  eval(Env env) {
		return body.eval(env);
	}

}
