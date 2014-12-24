package modules.jumps;

import modules.binding.IEvalBinding;

public interface EvalGoto extends GotoAlg<IEvalBinding> {

	@Override
	default IEvalBinding jump(String label) {
		return (env) -> ((Cont)env.lookup(label)).eval(env);
	}
	
	@Override
	default IEvalBinding label(String label, IEvalBinding body) {
		return (env) -> body.eval(env.bind(label, new Cont(body)));
	}
	
}
