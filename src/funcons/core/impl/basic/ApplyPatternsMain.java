package funcons.core.impl.basic;

import funcons.core.ConstantAlg;
import funcons.core.experiments.AnyAlg;
import funcons.core.experiments.ApplyAlg;
import funcons.core.experiments.BindAlg;
import funcons.core.experiments.EmptyEnvAlg;
import funcons.core.impl.lifted.ConstantAlgLiftedFromNoneToValue;
import funcons.core.impl.lifted.EmptyEnvLiftedFromNoneToValueAlg;
import funcons.entities.Env;
import funcons.entities.I;
import funcons.entities.Int;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

interface PattInterpreter extends
		PatternsAlg<IEvalValue, IEvalValueRetEnv, IEvalBasic>,
		ApplyAlg<IEvalValue, IEvalValueRetEnv, IEvalBasic>,
		AnyAlg<IEvalValueRetEnv, IEvalBasic>,
		BindAlg<IEvalValue, IEvalValueRetEnv, IEvalBasic>,
		ConstantAlgLiftedFromNoneToValue, ConcreteAbsPattAlg,
		ConcreteSupplyPattAlg, 
		ConcreteAbs2Alg,
		EmptyEnvLiftedFromNoneToValueAlg {

	@Override
	default EmptyEnvAlg<IEvalBasicRetEnv> emptyEnvAlg() {
		return new ConcreteEmptyEnvAlg() {};
	}

	/*
	@Override
	default AbsAlg<IEvalBasicRetEnv, IEvalBasic> absAlg() {
		return new ConcreteAbsPattAlg() {};
	}
	*/
	
	@Override
	default ConstantAlg<IEvalBasic> constantAlg() {
		return new ConcreteConstantAlg() {
		};
	}
	
	@Override
	// Maybe there should be an algebra for the environment binding, as there is one for empty env.
	default IEvalValueRetEnv bind(I id, IEvalValue x) {
		return (val) -> new Env<Value>().bind(id.toString(), x.eval(val));
	}
}

public class ApplyPatternsMain {
	public static void main(String[] args) {
		PattInterpreter interpreter = new PattInterpreter() {};
		Object o1 = interpreter.apply(interpreter.any(),
				interpreter.constant(new Int(3))).eval(new Int(42));
		System.out.println(o1);
		Object o2 = interpreter.apply(interpreter.bind(new I("w")),
				interpreter.constant(new Int(3))).eval(new Int(42));
		System.out.println(o2);
	}
}
