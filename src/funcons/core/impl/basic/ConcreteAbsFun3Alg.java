package funcons.core.impl.basic;

import funcons.core.experiments.AbsAlg;
import funcons.entities.AbsValue;
import funcons.evaluators.IEvalEnvValue;

public interface ConcreteAbsFun3Alg extends AbsAlg<IEvalEnvValue, AbsValue<IEvalEnvValue>>{
	@Override
	public default AbsValue<IEvalEnvValue> abs(IEvalEnvValue x){
		return new AbsValue<IEvalEnvValue>(x);
	}
	
	@Override
	public default IEvalEnvValue getAbstracted(AbsValue<IEvalEnvValue> x){
		return x.getAbstracted();
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