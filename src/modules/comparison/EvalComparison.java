package modules.comparison;

import modules.arith.IEvalBase;
import modules.conditional.Bool;
import modules.arith.Int;

public interface EvalComparison extends ComparisonAlg<IEvalBase> {

	@Override
	default IEvalBase lt(IEvalBase l, IEvalBase r) {
		return () -> new Bool(((Int)l.eval()).getValue() < ((Int)r.eval()).getValue()); 
	}

	@Override
	default IEvalBase gt(IEvalBase l, IEvalBase r) {
		return () -> new Bool(((Int)l.eval()).getValue() > ((Int)r.eval()).getValue()); 
	}

	@Override
	default IEvalBase leq(IEvalBase l, IEvalBase r) {
		return () -> new Bool(((Int)l.eval()).getValue() <= ((Int)r.eval()).getValue()); 
	}

	@Override
	default IEvalBase geq(IEvalBase l, IEvalBase r) {
		return () -> new Bool(((Int)l.eval()).getValue() >= ((Int)r.eval()).getValue()); 
	}

	@Override
	default IEvalBase eq(IEvalBase l, IEvalBase r) {
		return () -> new Bool(((Int)l.eval()).getValue() == ((Int)r.eval()).getValue()); 
	}

	@Override
	default IEvalBase neq(IEvalBase l, IEvalBase r) {
		return () -> new Bool(((Int)l.eval()).getValue() != ((Int)r.eval()).getValue()); 
	}
	
}
