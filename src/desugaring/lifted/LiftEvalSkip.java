package desugaring.lifted;

import modules.arith.IEvalBase;
import modules.skip.SkipAlg;
import desugaring.IEvalBindingAndStorage;

public interface LiftEvalSkip extends SkipAlg<IEvalBindingAndStorage>, AddEnvToBase, AddStoreToBinding {
	SkipAlg<IEvalBase> skipAlg();
	
	@Override
	default IEvalBindingAndStorage skip() {
		return lift(liftEnv(skipAlg().skip()));
	};
}
