package extending;

import modules.binding.Env;
import modules.binding.EvalLet;
import modules.binding.IEvalBinding;
import modules.values.Value;
import runtime.RT;

public interface EvalLetWithBase extends EvalLet {
	
	class Wrap implements IEvalBaseAndBinding {
		private IEvalBinding base;
		Wrap(IEvalBinding base) { this.base = base; }
		public Value eval() { return eval(RT.peek("env")); }
		public Value eval(Env env) { return base.eval(env); }
	}
	
	@Override 
	default IEvalBaseAndBinding let(String x, IEvalBinding e, IEvalBinding body) {
		return new Wrap(EvalLet.super.let(x, e, body));
	}

	@Override 
	default IEvalBaseAndBinding var(String x) {
		return new Wrap(EvalLet.super.var(x));
	}
}
