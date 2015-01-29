package objs;

import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;


public interface EvalArithWithBinding extends ArithAlg<IEvalBinding> {
	ArithAlg<IEvalBase> alg();
	
	// 	return (env) -> alg().add(() -> l.eval(env), () -> r.eval(env)).eval(); 
	
	@Override
	default IEvalBinding add(IEvalBinding l, IEvalBinding r) {
		Lifter x = new Lifter(); // bit unfortunate that there's no single expression to do this.
		return x.lift(alg().add(x.lower(l), x.lower(l))); 
	}
	
	@Override
	default IEvalBinding lit(int n) {
		Lifter x = new Lifter();
		return x.lift(alg().lit(n)); 
	}
}


