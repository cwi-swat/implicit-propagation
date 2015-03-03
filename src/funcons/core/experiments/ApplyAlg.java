package funcons.core.experiments;

import funcons.core.SupplyAlg;
import funcons.entities.Abstraction;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalWithValue;

/*
 public interface ApplyAlg<E, T>{
 SupplyAlg<E, Abs<E,T>> supplyAlg();

 // The second apply is Java function application
 default T apply(Abs<E,T> x, E e){
 return supplyAlg().supply(e, x).apply(e);
 }	
 }

 abstract class ConcreteApplyAlg<F> implements ApplyAlg<IEvalBasic,IEvalBasic>{


 @Override
 public IEvalBasic apply(Abs x, IEvalBasic e) {
 // TODO Auto-generated method stub
 return valueAlg().supply(e,e);

 }

 }

 */

//public interface ApplyAlg<From, To, X extends Abstraction<From,To>>{

/*
 public interface ApplyAlg<From, To, X extends Abstraction<From, To>> 

 extends AbsAlg<X,X>, SupplyAlg<From,X>{

 // The second apply is Java function application
 default To apply(X x, From from){
 return abs(supply(from, x));
 }	
 }
 */

/*
 public interface ApplyAlg<From, To, AbsX extends Abstraction<From, X>> 

 extends AbsAlg<To,From>, SupplyAlg<P,To>{


 // The second apply is Java function application
 default To apply(AbsX p, V v){
 return supply(p, x);
 }	
 }*/

public interface ApplyAlg<From, To, X>
		extends AbsAlg<To, X>, SupplyAlg<From, To> {

	// The second apply is Java function application
	default To apply(X p, From v) {
		return supply(v, getAbstracted(p));
	}
}
 
class ConcreteApplyAlg implements ApplyAlg<IEvalBasic, IEvalWithValue, Abstraction<IEvalBasic,IEvalWithValue>>{

	@Override
	public Abstraction<IEvalBasic, IEvalWithValue> abs(IEvalWithValue f) {
		return (iEvalBasic) -> f;
	}

	@Override
	public IEvalWithValue getAbstracted(
			Abstraction<IEvalBasic, IEvalWithValue> x) {
		return x.eval(null);
	}

	@Override
	public IEvalWithValue supply(IEvalBasic v, IEvalWithValue x) {
		// TODO Auto-generated method stub
		return v_ -> x.eval(v.eval());
	}

	
	
}
//
//class ConcreteApplyAlg
//		implements
//		ApplyAlg<IEvalWithValue, IEvalWithValue, Abstraction<IEvalWithValue, IEvalWithValue>, ParameterlessAbstraction<Abstraction<IEvalWithValue,IEvalWithValue>>> {
//
//	/*
//	@Override
//	public Abstraction<IEvalWithValue, Abstraction<IEvalWithValue, IEvalWithValue>> abs(
//			Abstraction<IEvalWithValue, IEvalWithValue> e) {
//		// TODO Auto-generated method stub
//		return (iev) -> e;
//	}
//
//	@Override
//	public Abstraction<IEvalWithValue, IEvalWithValue> getAbstracted(
//			Abstraction<IEvalWithValue, Abstraction<IEvalWithValue, IEvalWithValue>> f) {
//		// Just to capture the closure
//		return f.eval(null);
//	}
//*/
//	// ???????????
//	@Override
//	public Abstraction<IEvalWithValue, IEvalWithValue> supply(IEvalWithValue v,
//			Abstraction<IEvalWithValue, IEvalWithValue> x) {
//		// TODO Auto-generated method stub
//		return v_ -> x.eval((IEvalWithValue) v.eval(v_));
//	}
//
//	@Override
//	public ParameterlessAbstraction<Abstraction<IEvalWithValue, IEvalWithValue>> abs(
//			Abstraction<IEvalWithValue, IEvalWithValue> x) {
//		// TODO Auto-generated method stub
//		return dummy -> x;
//	}
//
//	@Override
//	public Abstraction<IEvalWithValue, IEvalWithValue> getAbstracted(
//			ParameterlessAbstraction<Abstraction<IEvalWithValue, IEvalWithValue>> f) {
//		// TODO Auto-generated method stub
//		return f.eval(new None());
//	}
//
//}

/*
 * class ConcreteApplyAlg implements ApplyAlg<IEvalWithValue, IEvalWithValue,
 * Abstraction<IEvalWithValue, IEvalWithValue>> {
 * 
 * @Override public IEvalWithValue abs(Abstraction<IEvalWithValue,
 * IEvalWithValue> e) { return (v) -> e; }
 * 
 * @Override public Abstraction<IEvalWithValue, IEvalWithValue>
 * supply(IEvalWithValue e, Abstraction<IEvalWithValue, IEvalWithValue> x) {
 * return v -> x.eval(e); }
 * 
 * }
 */