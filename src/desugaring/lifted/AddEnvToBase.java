package desugaring.lifted;

import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;
import runtime.RT;

public interface AddEnvToBase {
	default IEvalBase removeEnvToBase(IEvalBinding e) {
		return () -> e.eval(RT.peek("env")); 
	}
	
	default IEvalBinding addEnvToBase(IEvalBase e) {
		return (env) -> RT.with("env", env, e::eval);
	}
}
