package funcons.core.impl.lifted;

import funcons.core.GivenAlg;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalValue;

public interface GivenAlgLiftedFromValueToEnv extends GivenAlg<IEvalEnvValue>{
	GivenAlg<IEvalValue> givenAlg();
	
	default IEvalEnvValue given() {
		return (env, val) -> givenAlg().given().eval(val);
	}
}