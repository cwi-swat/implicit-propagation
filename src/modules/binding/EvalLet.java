package modules.binding;


public interface EvalLet extends LetAlg<IEvalBinding> {

	@Override
	default IEvalBinding let(String x, IEvalBinding e, IEvalBinding body) {
		return env -> body.eval(env.bind(x, e.eval(env)));
	}
	
	@Override
	default IEvalBinding var(String x) {
		return env -> env.lookup(x);
	}
}
