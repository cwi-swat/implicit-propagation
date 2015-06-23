package funcons.core;


public interface IfWhileTrueAlg<E,X> extends SeqAlg<X,X>, Skip<X>{
	// Are we managing the polymorphism now?
	X ifTrue(E e, X x1, X x2);
	
	default X whileTrue(E e, X c) {
		return ifTrue(e, seq(c, whileTrue(e, c)), skip());
	}
}