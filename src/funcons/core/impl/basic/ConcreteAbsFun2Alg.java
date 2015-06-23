package funcons.core.impl.basic;

import funcons.core.experiments.AbsAlg;
import funcons.evaluators.IEvalEnvValue;

public interface ConcreteAbsFun2Alg extends AbsAlg<IEvalEnvValue, IEvalEnvValue>{
	@Override
	public default IEvalEnvValue abs(IEvalEnvValue x){
		return (e,v) -> x;
	}
	
	@Override
	public default IEvalEnvValue getAbstracted(IEvalEnvValue x){
		return (e,v) -> ((IEvalEnvValue) x.eval(e,v)).eval(e, v);
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