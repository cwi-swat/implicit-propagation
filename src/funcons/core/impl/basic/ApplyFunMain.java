package funcons.core.impl.basic;

import funcons.core.BoundAlg;
import funcons.core.ConstantAlg;
import funcons.core.GivenAlg;
import funcons.core.SupplyAlg;
import funcons.core.experiments.EmptyEnvAlg;
import funcons.core.experiments.ScopeAlg;
import funcons.core.impl.lifted.BoundAlgLiftedFromEnvToValue;
import funcons.core.impl.lifted.ClosureAlgLiftedFromEnvToValue;
import funcons.core.impl.lifted.ConstantAlgLiftedFromNoneToValue;
import funcons.core.impl.lifted.ConstantAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.EmptyEnvAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.EmptyEnvLiftedFromNoneToValueAlg;
import funcons.core.impl.lifted.GivenAlgLiftedFromValueToEnv;
import funcons.core.impl.lifted.ScopeAlgLiftedFromEnvToValue;
import funcons.entities.Env;
import funcons.entities.I;
import funcons.entities.Int;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalEnv;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

class FunInterpreter implements
		ConcreteFunctionsAlg,
		BoundAlgLiftedFromEnvToValue,
		GivenAlgLiftedFromValueToEnv,
		ConstantAlgLiftedFromValueToEnv, 
		ClosureAlgLiftedFromEnvToValue,
		ConcreteCloseAlg,
		ScopeAlgLiftedFromEnvToValue,
		EmptyEnvAlgLiftedFromValueToEnv {

	@Override
	public ConstantAlg<IEvalValue> constantAlg() {
		return new ConstantAlgLiftedFromNoneToValue() {
			
			@Override
			public ConstantAlg<IEvalBasic> constantAlg() {
				return new ConcreteConstantAlg() {};
			}
		};
	}

	@Override
	public EmptyEnvAlg<IEvalValueRetEnv> emptyEnvAlg() {
		return new EmptyEnvLiftedFromNoneToValueAlg() {
			
			@Override
			public EmptyEnvAlg<IEvalBasicRetEnv> emptyEnvAlg() {
				return new ConcreteEmptyEnvAlg() {
				};
			}
		};
	}

	@Override
	public ClosureAlg<IEvalEnv, IEvalBasicRetEnv> closureAlg() {
		return new ConcreteClosureAlg() {};
	}

	@Override
	public SupplyAlg<IEvalValue, IEvalValue> supplyAlg() {
		return new ConcreteSupplyFunAlg() {};
	}

	@Override
	public GivenAlg<IEvalValue> givenAlg() {
		return new ConcreteGivenAlg() {};
	}

	@Override
	public BoundAlg<IEvalEnv> boundAlg() {
		return new ConcreteBoundAlg() {
		};
	}

	@Override
	public ScopeAlg<IEvalBasicRetEnv, IEvalEnv> scopeAlg() {
		return new ConcreteScopeAlg(){};
	}
}

public class ApplyFunMain {
	/*
	public static IEvalEnvValue ex1(FunInterpreter i){
		return i.apply(new AbsValue<IEvalEnvValue>(new Abs<IEvalEnvValue>(i.constant(new Int(7)))), i.constant(new Int(3)));
	}
	*/
	
	public static IEvalEnvValue ex2(FunInterpreter i){
		return i.closure(i.boundValue(new I("x")), () -> new Env<Value>().bind("x", new Int(353)));
	}
	
	public static IEvalEnvValue ex3(FunInterpreter i){
		//return i.close(i.abs(i.boundValue(new I("x"))));
		return i.constant(new Int(33));
	}
	
	public static Object ex4(FunInterpreter i){
		return i.abs(i.constant(new Int(55)));
	}
	
	public static IEvalEnvValue ex5(FunInterpreter i){
		return i.constant(new Int(55));
	}
	
	public static void main(String[] args) {
		FunInterpreter i = new FunInterpreter() {};
		Value dummy = new Int(42);
		//System.out.println(ex1(i).eval(new Env<Value>(), dummy));
		System.out.println(ex2(i).eval(new Env<Value>(), dummy));
		System.out.println(ex3(i).eval(new Env<Value>(), dummy));
		System.out.println(ex4(i));
		System.out.println(ex5(i).eval(new Env<Value>().bind("x", new Int(2)), dummy));
	}
}
