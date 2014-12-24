package modules.callcc;

import modules.binding.Closure;
import modules.skip.Null;

public interface EvalCallcc extends CallccAlg<IEvalCPS> {

	@Override
	default IEvalCPS callcc(IEvalCPS e) {
		return (cc) -> e.eval(k -> ((Closure)k).apply(new Closure((v) -> {cc.accept(v); return new Null();} )));
	}
}
