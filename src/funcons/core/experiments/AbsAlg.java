package funcons.core.experiments;


/*
 public interface AbsAlg<E,X,P extends Abs> extends ScopeAlg<P,X>, MatchAlg<X,P>,
 GivenAlg<X>{

 default E abs(P p, X x){
 return (E) new Abs(scope(match(given(), p),x));
 }
 }
 */

/*

 public interface AbsAlg<X> 
 extends ScopeAlg<Pattern,X>, MatchAlg<X>, GivenAlg<X> {

 default Abs<X, Pattern> abs(Abs<X, Pattern> p, X x) {
 return new Abs<X, Pattern>(scope(match(given(), p), x));
 }
 }

 */

/*

public interface AbsAlg<E,F>{
F abs(E e);
E getAbstracted(F f);
}

 */

/*
public interface AbsAlg<From, To, X extends Abstraction<From, To>, AbsX extends ParameterlessAbstraction<X>>{
	AbsX abs(X x);
	X getAbstracted(AbsX x);
}*/

/*
public interface AbsAlg<From, To, X extends Abstraction<From, To>, AbsX extends ParameterlessAbstraction<X>>{
	AbsX abs(X x);
	X getAbstracted(AbsX x);
}
*/

public interface AbsAlg<To, X>{
	X abs(To x);
	To getAbstracted(X x);
}