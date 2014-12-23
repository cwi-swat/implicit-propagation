package morphing;

import static util.Morph.morph;
import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.arith.EvalArith;
import modules.binding.IEvalBinding;

public interface EvalArithWithBindingManual extends ArithAlg<IEvalBinding> {
	static ArithAlg<IEvalBase> alg = new EvalArith() {};
	
	@Override
	default IEvalBinding add(IEvalBinding l, IEvalBinding r) {
		return morph(IEvalBinding.class, IEvalBase.class, 
				alg.add(morph(IEvalBase.class, IEvalBinding.class, l), 
						morph(IEvalBase.class, IEvalBinding.class, r)));
	}
	
	@Override
	default IEvalBinding lit(int n) {
		return morph(IEvalBinding.class, IEvalBase.class, alg.lit(n));
	}
}
