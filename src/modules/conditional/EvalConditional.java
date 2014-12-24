package modules.conditional;

import modules.arith.IEvalBase;

public interface EvalConditional extends ConditionalAlg<IEvalBase> {
	@Override
	default IEvalBase ifThen(IEvalBase cond, IEvalBase body, IEvalBase els) {
		return () -> ((Bool)cond.eval()).isTrue() ? body.eval() : els.eval();
	}
}
