package modules.binding;

import modules.values.Value;


public interface IEvalBinding {
	Value eval(Env env);
}
