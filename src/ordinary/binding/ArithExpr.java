package ordinary.binding;

import runtime.RT;
import modules.binding.Env;
import modules.values.Value;

public interface ArithExpr extends IEvalBinding {
	default Value eval(Env env) {
		return RT.with("env", env, this::eval);
	};
}
