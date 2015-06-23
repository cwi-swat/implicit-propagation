package funcons.core.impl.basic;

import funcons.core.SupplyAlg;
import funcons.core.experiments.AbsAlg;
import funcons.core.experiments.ApplyAlg;

public interface FunctionsAlg<E, To, X> extends
	ApplyAlg<E,To,X>,
	AbsAlg<To, X>,
	SupplyAlg<E, To>{
	
}
