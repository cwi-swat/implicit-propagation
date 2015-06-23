package funcons.core.impl.basic;

import funcons.core.experiments.ApplyAlg;
import funcons.core.impl.lifted.PattAbsAlgLiftedFromNoneToValue;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

public interface ConcreteApplyPattAlg extends
		ApplyAlg<IEvalValue, IEvalValueRetEnv, IEvalBasic>,
		PattAbsAlgLiftedFromNoneToValue, ConcreteSupplyPattAlg {

	/*
	@Override
	default AbsAlg<IEvalBasicRetEnv, IEvalBasicRetEnv> absAlg() {
		return new ConcreteAbsPattAlg() {
		};
	} */
}