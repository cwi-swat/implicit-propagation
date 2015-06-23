package funcons.core.impl.basic;

import funcons.core.experiments.BindAlg;
import funcons.core.impl.lifted.PattAbsAlgLiftedFromNoneToValue;
import funcons.entities.Env;
import funcons.entities.I;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

public interface ConcreteBindAlg extends
		BindAlg<IEvalValue, IEvalValueRetEnv, IEvalBasic>,
		PattAbsAlgLiftedFromNoneToValue, ConcreteGivenAlg {

	/*
	 * @Override public default AbsAlg<IEvalBasicRetEnv, IEvalBasicRetEnv>
	 * absAlg() { return new ConcreteAbsPattAlg() {}; }
	 */

	@Override
	public default IEvalValueRetEnv bind(I id, IEvalValue x) {
		return (val) -> new Env<Value>().bind(id.toString(), x.eval(val));
	}

}
