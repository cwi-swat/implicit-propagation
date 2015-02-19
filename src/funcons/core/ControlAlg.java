package funcons.core;


public interface ControlAlg<E,X> extends SeqAlg<X,X>{
	// Are we managing the polymorphism now?
	X ifTrue(E e, X x1, X x2);
	X skip();
	
	default X whileTrue(E e, X c) {
		return ifTrue(e, seq(c, whileTrue(e, c)), skip());
	}
}