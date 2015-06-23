package funcons.core.impl.lifted;

import funcons.core.ConstantAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalValue;

public interface ConstantAlgLiftedFromNoneToValue extends ConstantAlg<IEvalValue> {
	ConstantAlg<IEvalBasic> constantAlg();

	default IEvalValue constant(Value v) {
		return (val) -> constantAlg().constant(v).eval();
	}
}