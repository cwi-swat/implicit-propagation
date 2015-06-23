package funcons.languages.impl;

import funcons.core.ConstantAlg;
import funcons.core.EffectAlg;
import funcons.core.IfWhileTrueAlg;
import funcons.core.SeqAlg;
import funcons.core.impl.basic.ConcreteConstantAlg;
import funcons.core.impl.basic.ConcreteEffectAlg;
import funcons.core.impl.basic.ConcreteIfWhileTrueAlg;
import funcons.core.impl.basic.ConcreteSeqAlg;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;
import funcons.languages.SimpleLangDelegationAlg;

public class ConcreteSimpleLangDelegationAlg implements SimpleLangDelegationAlg<IEvalBasic>{
	

	@Override
	public IEvalBasic effect(IEvalBasic e) {
		// TODO Auto-generated method stub
		return effectAlg().effect(e);
	}

	@Override
	public IEvalBasic seq(IEvalBasic c1, IEvalBasic c2) {
		// TODO Auto-generated method stub
		return seqAlg().seq(c1, c2);
	}

	@Override
	public IEvalBasic ifTrue(IEvalBasic e, IEvalBasic c1, IEvalBasic c2) {
		// TODO Auto-generated method stub
		return ifWhileTrueAlg().ifTrue(e, c1, c2);
	}

	@Override
	public IEvalBasic whileTrue(IEvalBasic e, IEvalBasic c) {
		// TODO Auto-generated method stub
		return ifWhileTrueAlg().whileTrue(e, c);
	}

	@Override
	public IEvalBasic skip() {
		// TODO Auto-generated method stub
		return ifWhileTrueAlg().skip();
	}
	

	@Override
	public IEvalBasic constant(Value v) {
		// TODO Auto-generated method stub
		return expAlg().constant(v);
	}

	@Override
	public SeqAlg<IEvalBasic, IEvalBasic> seqAlg() {
		// TODO Auto-generated method stub
		return new ConcreteSeqAlg(){
			
		};
	}
	
	@Override
	public IfWhileTrueAlg<IEvalBasic, IEvalBasic> ifWhileTrueAlg() {
		
		// TODO Auto-generated method stub
		return new ConcreteIfWhileTrueAlg(){
			
		};
	}

	@Override
	public ConstantAlg<IEvalBasic> expAlg() {
		// TODO Auto-generated method stub
		return new ConcreteConstantAlg(){
			
		};
	}


	@Override
	public EffectAlg<IEvalBasic, IEvalBasic> effectAlg() {
		return new ConcreteEffectAlg(){
		
		};
	}

	
	
}