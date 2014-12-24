package closures;

import modules.arith.ArithAlg;
import modules.binding.IEvalBinding;


public interface EvalArithWithBindingAndStorage extends ArithAlg<IEvalBindingAndStorage> {
	ArithAlg<IEvalBinding> alg();
	
	@Override
	default IEvalBindingAndStorage add(IEvalBindingAndStorage l, IEvalBindingAndStorage r) {
		return (env, store) -> alg().add((e) -> l.eval(e, store), (e) -> r.eval(e, store)).eval(env);
	}
	
	@Override
	default IEvalBindingAndStorage lit(int n) {
		return (env, store) -> alg().lit(n).eval(env);
	}
}
