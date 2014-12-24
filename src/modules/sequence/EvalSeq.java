package modules.sequence;

import modules.arith.IEvalBase;

public interface EvalSeq extends SeqAlg<IEvalBase> {
	@Override
	default IEvalBase seq(IEvalBase e1, IEvalBase e2) {
		return () -> { e1.eval(); return e2.eval(); };
	};
}
