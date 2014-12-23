package modules.binding;

import modules.values.Value;


@FunctionalInterface
public interface IEvalBinding {
	Value eval(Env env);
}
