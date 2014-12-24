package cps;

import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.callcc.IEvalCPS;

public interface EvalArithWithCPS extends ArithAlg<IEvalCPS> {
	ArithAlg<IEvalBase> alg();
	
	@Override
	default IEvalCPS add(IEvalCPS l, IEvalCPS r) {
		return (k) -> l.eval(k2 -> r.eval(k3 -> k.accept(alg().add(() -> k2, () -> k3).eval())));
	}
	
	@Override
	default IEvalCPS lit(int n) {
		return (k) -> k.accept(alg().lit(n).eval());
	}
}
