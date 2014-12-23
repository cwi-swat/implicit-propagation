package ordinary.binding;

import runtime.RT;
import modules.values.Value;

public interface BindingExpr extends IEvalBinding {
	@Override
	default Value eval() {
		return eval(RT.peek("env"));
	}
}
