package funcons.core.experiments;

import funcons.entities.AbsValue;

/*
public interface AbsValueAlg<X> extends AbsAlg<X, Value>{
	@Override
	default Value abs(X x){
		return new AbsValue<X>(new Abs<X>(x));
	}
	@Override
	default X getAbstracted(Value v){
		if (v instanceof AbsValue)
			return ((AbsValue<X>) v).getAbstraction().getAbstracted();
		else
			throw new RuntimeException("Not an abstraction."); 
	}
}*/


public interface AbsValueAlg<X> extends AbsAlg<X, AbsValue<X>>{
	@Override
	default AbsValue<X> abs(X x){
		return new AbsValue<X>(x);
	}
	@Override
	default X getAbstracted(AbsValue<X> x){
		return x.getAbstracted();
	}
}