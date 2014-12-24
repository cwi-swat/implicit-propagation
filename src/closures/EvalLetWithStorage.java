package closures;

import modules.binding.IEvalBinding;
import modules.binding.LetAlg;

public interface EvalLetWithStorage extends LetAlg<IEvalBindingAndStorage> {
	LetAlg<IEvalBinding> letAlg();

	@Override
	default IEvalBindingAndStorage let(String x, IEvalBindingAndStorage e, IEvalBindingAndStorage body) {
		return (env, store) -> letAlg().let(x, (env1) -> e.eval(env1, store), (env1) -> body.eval(env1, store)).eval(env);
	}
	
	@Override
	default IEvalBindingAndStorage var(String x) {
		return (env, store) -> letAlg().var(x).eval(env);
	};
}
