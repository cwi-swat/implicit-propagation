package modules.binding;

import java.util.HashMap;
import java.util.Map;

import modules.values.Value;

public class Env {
	private final Map<String, Value> map;
	
	public Env() { 
		this(new HashMap<>());
	}
	
	private Env(Map<String,Value> map) {
		this.map =  map;
	}
	
	public Env bind(String name, Value value) {
		Map<String,Value> m = new HashMap<>(map);
		m.put(name, value);
		return new Env(m);
	}
	
	public Value lookup(String name) {
		return map.get(name);
	}
}
