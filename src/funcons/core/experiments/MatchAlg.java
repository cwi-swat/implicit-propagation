package funcons.core.experiments;


/*
public interface MatchAlg<E> extends ApplyAlg<E,Pattern>{
	default Pattern match(E e, Abs<E, Pattern> p){
		return apply(p, e);
	}
}

*/

// ApplyAlg<From, To, X extends Abstraction<From, To>, AbsX extends Abstraction<From, X>>

/*
public interface MatchAlg<II,E, D extends Abstraction<II, E>, P extends Abstraction<II, D>> extends ApplyAlg<II,E,D,P>{
	default D match(II e, P p){
		return apply(p, e);
	}
}
*/
public interface MatchAlg<E, D, P> extends ApplyAlg<E,D,P>{
	default D match(E e, P p){
		return apply(p, e);
	}
}
