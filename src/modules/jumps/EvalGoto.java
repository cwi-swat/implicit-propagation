package modules.jumps;

import modules.arith.IEvalBase;

public interface EvalGoto extends GotoAlg<IEvalBase> {

	@Override
	default IEvalBase jump(String label) {
		return () -> { throw new Jump(label); };
	}
	
	@Override
	default IEvalBase label(String label, IEvalBase body) {
		return () -> {
			while (true) {
				try {
					return body.eval();
				}
				catch (Jump j) {
					if (!j.getLabel().equals(label)) {
						throw j;
					}
				}
			}
		};
	}
	
}
