package funcons.entities;

import java.util.HashMap;
import java.util.Map;

public class Env<T> implements EnvProvider<T>{
	private final Map<String, T> map;
	
	public Env() { 
		this(new HashMap<>());
	}
	
	private Env(Map<String,T> map) {
		this.map =  map;
	}
	
	public Env<T> bind(String name, T value) {
		Map<String,T> m = new HashMap<>(map);
		m.put(name, value);
		return new Env<T>(m);
	}
	
	public T lookup(String name) {
		return map.get(name);
	}
	
	public Env<T> overrideBy(Env<T> e){
		Map<String,T> m = new HashMap<String,T>(map);
		m.putAll(e.map);
		return new Env<T>(m);
	}

	@Override
	public Env<T> getEnv() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override	
	public String toString(){
		return this.map.toString();
	}
}
