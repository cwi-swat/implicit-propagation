package funcons.core.impl.lifted;

import funcons.core.ConstantAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalValue;

public interface ConstantAlgLiftedFromValueToEnv extends ConstantAlg<IEvalEnvValue> {
	ConstantAlg<IEvalValue> constantAlg();

	default IEvalEnvValue constant(Value v) {
		return (env, val) -> constantAlg().constant(v).eval(val);
	}
	
}