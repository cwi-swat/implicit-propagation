package funcons.core.impl.basic;

import funcons.core.ArithAlg;
import funcons.core.BoundAlg;
import funcons.core.ConstantAlg;
import funcons.core.GivenAlg;
import funcons.core.SupplyAlg;
import funcons.core.experiments.Abs2Alg;
import funcons.core.experiments.AbsAlg;
import funcons.core.experiments.AnyAlg;
import funcons.core.experiments.ApplyAlg;
import funcons.core.experiments.BindAlg;
import funcons.core.experiments.EmptyEnvAlg;

public interface PatternsAlg<E, D, P>
	extends 
	ApplyAlg<E, D, P>,
	AnyAlg<D, P>,
	BindAlg<E, D, P>,
	GivenAlg<E>,
	ConstantAlg<E>,
	AbsAlg<D, P>,
	SupplyAlg<E, D>, 
	EmptyEnvAlg<D>,
	Abs2Alg<E, E, D, P>,
	ArithAlg<E>,
	BoundAlg<E>{
}

