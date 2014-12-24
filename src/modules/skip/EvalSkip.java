package modules.skip;

import modules.arith.IEvalBase;

public interface EvalSkip extends SkipAlg<IEvalBase> {
	@Override
	default IEvalBase skip() {
		return () -> new Null();
	}
}
