package annos;

import java.lang.reflect.Proxy;

import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;
import proxying.EvalArithWithBinding;
import util.Morph;

public class Union {

//	@SuppressWarnings("unchecked")
//	static <Alg, T> Alg make(Class<?>[] interfaces) {
//		return (Alg) Proxy.newProxyInstance(Union.class.getClassLoader(), 
//				interfaces, 
//				(prox, method, args) -> {
//					Class<?> decl = method.getDeclaringClass();
//					Propagate anno = decl.getAnnotation(Propagate.class);
//					Object[] args2;
//					if (anno != null) {
//						Class<?> base = anno.value();
//						args2 = new Object[args.length];
//						for (int i = 0; i < args.length; i++) {
//							if (args[i] instanceof IEvalBinding) {
//								args2[i] = Morph.unsafeMorph(base, method.getReturnType(), args[i]);
//							}
//							else {
//								args2[i] = args[i];
//							}
//						}
//						return Morph.unsafeMorph(method.getReturnType(), base, method.invoke(alg, args2));
//					}
//					else {
//						args2 = args;
//					}
//					
//					
//				});
//	}
}
