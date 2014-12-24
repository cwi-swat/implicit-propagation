package closures;

import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;


public interface EvalArithWithBinding extends ArithAlg<IEvalBinding> {
	ArithAlg<IEvalBase> alg();
	
	@Override
	default IEvalBinding add(IEvalBinding l, IEvalBinding r) {
		return (env) -> alg().add(() -> l.eval(env), () -> r.eval(env)).eval(); 
	}
	
	@Override
	default IEvalBinding lit(int n) {
		return (env) -> alg().lit(n).eval();
	}
}


