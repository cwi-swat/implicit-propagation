package modules.lambda;

import modules.binding.IEvalBinding;
import modules.values.Closure;

public interface EvalLambda extends LambdaAlg<IEvalBinding> {
	@Override
	default IEvalBinding lambda(String x, IEvalBinding body) {
		return env -> new Closure(env, x, body);
	}
	
	@Override
	default IEvalBinding apply(IEvalBinding e1, IEvalBinding e2) {
		return env -> ((Closure)e1.eval(env)).apply(e2.eval(env));
	}

}
