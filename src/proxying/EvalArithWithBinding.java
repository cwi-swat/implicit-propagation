package proxying;

import java.lang.reflect.Proxy;


import util.Morph;
import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;

public interface EvalArithWithBinding extends ArithAlg<IEvalBinding> {
	static EvalArithWithBinding make(ArithAlg<IEvalBase> alg) {
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
