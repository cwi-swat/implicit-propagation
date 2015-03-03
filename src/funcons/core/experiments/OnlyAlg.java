package funcons.core.experiments;

import funcons.core.GivenAlg;



public interface OnlyAlg<To,X,E,B> extends AbsAlg<To, X>, WhenTrueAlg<B,To>, EqualAlg<B,E>, GivenAlg<E>, EmptyEnvAlg<To>{
	default X only(E e){
		return abs(whenTrue(equal(given(), e), emptyEnv()));
	}
}