package util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import runtime.RT;

public class Morph {
	private static Map<Integer,Integer> match(Parameter[] in, Parameter[] out) {
		Map<Integer, Integer> mapping = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < out.length; j++) {
				Parameter p1 = in[i];
				Parameter p2 = out[j];
				if (p1.getName().equals(p2.getName())) {
					assert p1.getType().isAssignableFrom(p2.getType()); // TODO check direction
					mapping.put(i, j);
				}
			}
		}
		return mapping;
	}
	
	private static boolean isFunctional(Class<?> c) {
		return c.getAnnotation(FunctionalInterface.class) != null;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T, U> T morph(Class<T> t, Class<U> u, U e) {
		assert isFunctional(t); assert isFunctional(u);

		
		Method incoming = t.getMethods()[0]; 
		Method outgoing = u.getMethods()[0]; 

		Parameter[] params1 = incoming.getParameters();
		Parameter[] params2 = outgoing.getParameters();
		
		Map<Integer,Integer> mapping = match(params1, params2);
	
		return (T) Proxy.newProxyInstance(t.getClassLoader(), new Class<?>[] { t }, 
				new Morpher<U>(e, outgoing, mapping, params1, params2));
	}
	
	
	
//	public static void main(String[] args) {
//		Eval2 eval2 = morph(Eval2.class, Eval1.class, () -> 4);
//		System.out.println(eval2.eval("bla"));
//		
//		RT.push("x", "abc"); 
//		Eval1 eval1 = morph(Eval1.class, Eval2.class, (s) -> s.length());
//		System.out.println(eval1.eval());
//		RT.pop("x");
//		
//		Eval3 eval3 = morph(Eval3.class, Eval2.class, (s) -> s.length());
//		System.out.println(eval3.eval(23, "blaat"));
//		
//		RT.push("x", "some other string");
//		RT.push("i", 123456);
//		Eval1 eval4 = morph(Eval1.class, Eval3.class, eval3);
//		System.out.println(eval4.eval());
//		RT.pop("x");
//		RT.pop("i");
//		
//		RT.push("x", "some other string");
//		RT.push("i", 123456);
//		Eval1 eval5 = morph(Eval1.class, Eval3.class, (i, s) -> { System.out.println("i = " + i + "; x = " + s); return i; });
//		System.out.println(eval5.eval());
//		RT.pop("x");
//		RT.pop("i");
//	}
	
	
	public static class Morpher<U> implements InvocationHandler {
		private Parameter[] params1;
		private Parameter[] params2;
		private U delegate;
		private Method outgoing;
		private final Map<Integer, Integer> mapping;

		public Morpher(U delegate, Method outgoing, Map<Integer,Integer> mapping, Parameter params1[], Parameter params2[]) {
			this.delegate = delegate;
			this.outgoing = outgoing;
			this.mapping = mapping;
			this.params1 = params1;
			this.params2 = params2;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object[] delegateArgs = new Object[params2.length];
			
			int argsLength = (args != null) ? args.length : 0;
			String toPop[] = new String[argsLength - mapping.size()];
			int popIdx = 0;
			
			for (int i = 0; i < argsLength; i++) {
				if (mapping.containsKey(i)) {
					// if a parameter is defined by both interface and
					// the delegate object, propagate it.
					delegateArgs[mapping.get(i)] = args[i];
				}
				else {
					// otherwise the received argument (arg[i])
					// need to be stored for possible later use.
					String param = params1[i].getName();
					RT.push(param, args[i]);
					toPop[popIdx++] = param; 
				}
			}
			
			// fill the remaining slots with objects from the 
			// shadow stack
			for (int j = 0; j < delegateArgs.length; j++) {
				if (delegateArgs[j] == null) {
					delegateArgs[j] = RT.peek(params2[j].getName());
				}
			}
			
			try {
				// NB: ignore method parameter to invoke.
				outgoing.setAccessible(true);
				return outgoing.invoke(delegate, delegateArgs);
			}
			finally {
				for (String param: toPop) {
					RT.pop(param);
				}
			}
		}

		
	}
	
}
