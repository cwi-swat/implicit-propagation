package cps;

import java.util.function.Function;

import modules.binding.Env;
import modules.values.Value;

public interface IEvalBindingCPS {
	Value eval(Env env, Function<Value, Value> k);
}
