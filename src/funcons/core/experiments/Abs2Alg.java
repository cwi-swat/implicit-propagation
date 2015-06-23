package funcons.core.experiments;

import funcons.core.GivenAlg;

/*
 public interface AbsAlg<E,X,P extends Abs> extends ScopeAlg<P,X>, MatchAlg<X,P>,
 GivenAlg<X>{

 default E abs(P p, X x){
 return (E) new Abs(scope(match(given(), p),x));
 }
 }
 */

//public interface Abs2Alg<E,X, To,P extends Abstraction<E,To>> extends GivenAlg<E>, 

/*
 public interface Abs2Alg<X, E, D extends Abstraction<E, E>, P extends Abstraction<E, D>>
 extends	 ScopeAlg<D, X>, 
 MatchAlg<E, E, D, P>,
 GivenAlg<E>{
 AbsAlg<E, E, X, P> absAlg();

 default E abs(P p, X x){
 return absAlg().abs(scope(match(given(), p), x));
 }

 }
 */

public interface Abs2Alg<X,E,D,P>
		extends ScopeAlg<D, E>, MatchAlg<E, D, P>, GivenAlg<E> {
	AbsAlg<E, X> absAlg();

	default X abs(P p, E e) {
		return absAlg().abs(scope(match(given(), p), e));
	}

}
