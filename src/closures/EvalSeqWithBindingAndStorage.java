package closures;

import modules.arith.IEvalBase;
import modules.sequence.SeqAlg;

public interface EvalSeqWithBindingAndStorage extends SeqAlg<IEvalBindingAndStorage> {
	SeqAlg<IEvalBase> seqAlg();
	
	@Override
	default IEvalBindingAndStorage seq(IEvalBindingAndStorage e1, IEvalBindingAndStorage e2) {
		return (env, store) -> seqAlg().seq(() -> e1.eval(env, store), () -> e2.eval(env, store)).eval();
	};
}
