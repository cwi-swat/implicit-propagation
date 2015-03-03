package funcons.core.experiments;

import funcons.core.GivenAlg;
import funcons.entities.I;

/*
interface BindAlg<X> extends AbsAlg<X>{
	default Abs<X,Pattern> bind(I id){
		return abs(bind(id, given()));
	}
	Abs<X,Pattern> bind(I id, X x);
}

*/

public interface BindAlg<X,D, P> extends AbsAlg<D,P>, GivenAlg<X>{
	
	default P bind(I id){
		return abs(bind(id, given()));
	}

	D bind(I id, X x);
}