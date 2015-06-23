package funcons.core.impl.basic;

import funcons.core.experiments.AbsAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;

public interface ConcreteAbsFunAlg extends AbsAlg<IEvalEnvValue, IEvalBasic>{
	@Override
	public default IEvalBasic abs(IEvalEnvValue x){
		return () -> x;
	}
	
	@Override
	public default IEvalEnvValue getAbstracted(IEvalBasic x){
		return (IEvalEnvValue) x.eval();
	}
}

/*

public interface ConcreteAbsFunAlg extends AbsAlg<IEvalEnvValue, AbsValue<IEvalEnvValue>>{
	@Override
	public default AbsValue<IEvalEnvValue> abs(IEvalEnvValue x){
		return new AbsValue<IEvalEnvValue>(x);
	}
	
	@Override
	public default IEvalEnvValue getAbstracted(AbsValue<IEvalEnvValue> x){
		return x.getAbstracted();
	}
}
*/