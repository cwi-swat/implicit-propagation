package funcons.languages;

import funcons.core.ControlAlg;
import funcons.core.EffectAlg;
import funcons.core.ExpAlg;
import funcons.core.SeqAlg;
import funcons.evaluators.IEvalBasic;


// Delegation based interface
// for a simple language with just expressions
public interface SimpleLangDelegationAlg<E> extends
	EffectAlg<E,E>, SeqAlg<E,E>, 
	ControlAlg<E, E>, ExpAlg<E>{
	SeqAlg<IEvalBasic, IEvalBasic> seqAlg();
	ControlAlg<IEvalBasic, IEvalBasic> controlAlg();
	ExpAlg<IEvalBasic> expAlg();
	EffectAlg<IEvalBasic, IEvalBasic> effectAlg();
}