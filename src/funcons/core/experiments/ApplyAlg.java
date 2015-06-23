package funcons.core.experiments;

import funcons.core.SupplyAlg;

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