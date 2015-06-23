package funcons.languages.experiments;

import funcons.entities.Value;

public interface CLAlg<E, D, F, P, LB, SM> {
	E function(SM sm);
	
	SM simpleMatch(P p, E e);

	LB simpleDecl(P p, E e);

	E apply(E e1, E e2);

	E simpleLet(LB lb, E e);

	P idPatt(String i);

	E constant(Value v);

	E boundValue(String s);
	
	E add(E e1, E e2);
}
