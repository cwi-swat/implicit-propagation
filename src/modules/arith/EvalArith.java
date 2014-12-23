package modules.arith;


public interface EvalArith extends ArithAlg<IEvalBase> {
	@Override
	default IEvalBase add(IEvalBase l, IEvalBase r) {
		return () -> new Int(((Int)l.eval()).getValue() + ((Int)r.eval()).getValue());
	}
	
	@Override
	default IEvalBase lit(int n) {
		return () -> new Int(n);
	}
}
