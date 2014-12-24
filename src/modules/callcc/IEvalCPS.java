package modules.callcc;

import java.util.function.Consumer;

import modules.values.Value;

public interface IEvalCPS {
	void eval(Consumer<Value> k);
}
