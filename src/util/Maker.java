package util;

import java.lang.reflect.Proxy;

import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;
import proxying.EvalArithWithBinding;

public class Maker {

	static <T, U, Alg> EvalArithWithBinding make(ArithAlg<IEvalBase> alg) {
		return (EvalArithWithBinding) Proxy.newProxyInstance(EvalArithWithBinding.class.getClassLoader(), 
				new Class<?>[] { EvalArithWithBinding.class }, 
				(prox, method, args) -> {
					Object[] args2 = new Object[args.length];
					for (int i = 0; i < args.length; i++) {
						if (args[i] instanceof IEvalBinding) {
							args2[i] = Morph.morph(IEvalBase.class, IEvalBinding.class, (IEvalBinding)args[i]);
						}
						else {
							args2[i] = args[i];
						}
					}
					return Morph.morph(IEvalBinding.class, IEvalBase.class, (IEvalBase)method.invoke(alg, args2));
				});
	}
}
