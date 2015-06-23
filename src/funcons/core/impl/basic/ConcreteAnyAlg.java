package funcons.core.impl.basic;

import funcons.core.experiments.AnyAlg;
import funcons.core.experiments.EmptyEnvAlg;
import funcons.core.impl.lifted.EmptyEnvLiftedFromNoneToValueAlg;
import funcons.core.impl.lifted.PattAbsAlgLiftedFromNoneToValue;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalValueRetEnv;

public interface ConcreteAnyAlg extends AnyAlg<IEvalValueRetEnv, IEvalBasic>,
	PattAbsAlgLiftedFromNoneToValue,
	EmptyEnvLiftedFromNoneToValueAlg{

	@Override
	default EmptyEnvAlg<IEvalBasicRetEnv> emptyEnvAlg(){
		return new ConcreteEmptyEnvAlg(){};
	}
	
	/*
	@Override
	default AbsAlg<IEvalBasicRetEnv, IEvalBasic> absAlg(){
		return new ConcreteAbsPattAlg() {};
	}
	*/
}