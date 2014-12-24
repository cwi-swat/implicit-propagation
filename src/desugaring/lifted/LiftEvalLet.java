package desugaring.lifted;

import desugaring.IEvalBindingAndStorage;
import modules.binding.IEvalBinding;
import modules.binding.LetAlg;

public interface LiftEvalLet extends LetAlg<IEvalBindingAndStorage>, AddStoreToBinding {
	LetAlg<IEvalBinding> letAlg();
	
	@Override
	default IEvalBindingAndStorage let(String x, IEvalBindingAndStorage e, IEvalBindingAndStorage body) {
		return lift(letAlg().let(x, lower(e), lower(body)));
	}
	
	@Override
	default IEvalBindingAndStorage var(String x) {
		return lift(letAlg().var(x));
	}

}
