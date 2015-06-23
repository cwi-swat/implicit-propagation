package funcons.languages;

import funcons.core.ConstantAlg;
import funcons.core.EffectAlg;
import funcons.core.IfWhileTrueAlg;
import funcons.core.SeqAlg;
import funcons.evaluators.IEvalBasic;


// Delegation based interface
// for a simple language with just expressions
public interface SimpleLangDelegationAlg<E> extends
	EffectAlg<E,E>, SeqAlg<E,E>, 
	IfWhileTrueAlg<E, E>, ConstantAlg<E>{
	SeqAlg<IEvalBasic, IEvalBasic> seqAlg();
	IfWhileTrueAlg<IEvalBasic, IEvalBasic> ifWhileTrueAlg();
	ConstantAlg<IEvalBasic> expAlg();
	EffectAlg<IEvalBasic, IEvalBasic> effectAlg();
}