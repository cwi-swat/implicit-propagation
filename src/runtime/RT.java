package runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Supplier;


@SuppressWarnings("unchecked")
public class RT {
	static Map<String, Stack<Object>> stacks = new HashMap<String, Stack<Object>>();
	
	public static <T> T peek(String name) {
		return (T) stacks.get(name).peek();
	}
	
	public static <T,U> U with(String name, T t, Supplier<U> f) {
		try {
			push(name, t);
			return f.get();
		}
		finally {
			pop(name);
		}
	}
	
	public static <T> void push(String name, T x) {
		if (!stacks.containsKey(name)) {
			stacks.put(name, new Stack<>());
		}
		stacks.get(name).push(x);
	}
	
	
	
	public static <T> void pop(String name) {
		stacks.get(name).pop();
	}
	

}
