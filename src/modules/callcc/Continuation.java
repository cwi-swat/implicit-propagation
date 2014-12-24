package modules.callcc;

import java.util.function.Consumer;

import modules.values.Value;

public class Continuation implements Value {
	private Consumer<Value> k;

	public Continuation(Consumer<Value> k) {
		this.k = k;
	}
}
