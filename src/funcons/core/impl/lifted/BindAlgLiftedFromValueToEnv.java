package funcons.core.impl.lifted;

import funcons.core.experiments.BindAlg;
import funcons.core.impl.basic.ConcreteAbsPattAlg;
import funcons.entities.I;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

public interface BindAlgLiftedFromValueToEnv extends
		BindAlg<IEvalEnvValue, IEvalEnvValueRetEnv, IEvalBasic>,
		ConcreteAbsPattAlg, GivenAlgLiftedFromValueToEnv {
	
	BindAlg<IEvalValue, IEvalValueRetEnv, IEvalBasic> bindAlg();
	/*
	 * @Override public default AbsAlg<IEvalBasicRetEnv, IEvalBasicRetEnv>
	 * absAlg() { return new ConcreteAbsPattAlg() {}; }
	 */

	@Override
	public default IEvalEnvValueRetEnv bind(I id, IEvalEnvValue x) {
		return (env, val) -> bindAlg().bind(id, (v) -> x.eval(env, v)).eval(val);
	}

}