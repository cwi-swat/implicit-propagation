package extending;

import modules.arith.EvalArith;
import modules.arith.IEvalBase;
import modules.binding.Env;
import modules.values.Value;
import runtime.RT;


public interface EvalArithWithBinding extends EvalArith {

	class Wrap implements IEvalBaseAndBinding {
		private IEvalBase base;
		Wrap(IEvalBase base) { this.base = base; }
		public Value eval(Env env) { return RT.with("env", env, this::eval); }
		public Value eval() { return base.eval(); }
	}
	
	@Override 
	default IEvalBaseAndBinding add(IEvalBase l, IEvalBase r) {
		return new Wrap(EvalArith.super.add(l, r));
	}
	
	
	@Override 
	default IEvalBaseAndBinding lit(int n) {
		return new Wrap(EvalArith.super.lit(n));
	}
}
