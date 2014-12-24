package desugaring.lifted;

import modules.arith.IEvalBase;
import modules.conditional.ConditionalAlg;
import desugaring.IEvalBindingAndStorage;

public interface LiftEvalCond extends ConditionalAlg<IEvalBindingAndStorage>, AddEnvToBase, AddStoreToBinding {
	ConditionalAlg<IEvalBase> condAlg();
	
	default IEvalBindingAndStorage ifThen(IEvalBindingAndStorage cond, IEvalBindingAndStorage body, IEvalBindingAndStorage els) {
		return lift(liftEnv(condAlg().ifThen(lowerEnv(lower(cond)), lowerEnv(lower(body)), lowerEnv(lower(els)))));
	}
}
