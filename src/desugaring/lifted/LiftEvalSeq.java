package desugaring.lifted;

import modules.arith.IEvalBase;
import modules.sequence.SeqAlg;
import desugaring.IEvalBindingAndStorage;

public interface LiftEvalSeq extends SeqAlg<IEvalBindingAndStorage>, AddEnvToBase, AddStoreToBinding {
	SeqAlg<IEvalBase> seqAlg();
	
	@Override
	default IEvalBindingAndStorage seq(IEvalBindingAndStorage e1, IEvalBindingAndStorage e2) {
		return lift(liftEnv(seqAlg().seq(lowerEnv(lower(e1)), lowerEnv(lower(e2)))));
	}
}
