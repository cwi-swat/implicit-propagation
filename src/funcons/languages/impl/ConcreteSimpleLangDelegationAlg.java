package funcons.languages.impl;

import funcons.core.ControlAlg;
import funcons.core.EffectAlg;
import funcons.core.ExpAlg;
import funcons.core.SeqAlg;
import funcons.core.impl.noarguments.ConcreteControlAlg;
import funcons.core.impl.noarguments.ConcreteEffectAlg;
import funcons.core.impl.noarguments.ConcreteExpAlg;
import funcons.core.impl.noarguments.ConcreteSeqAlg;
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
		return controlAlg().ifTrue(e, c1, c2);
	}

	@Override
	public IEvalBasic whileTrue(IEvalBasic e, IEvalBasic c) {
		// TODO Auto-generated method stub
		return controlAlg().whileTrue(e, c);
	}

	@Override
	public IEvalBasic skip() {
		// TODO Auto-generated method stub
		return controlAlg().skip();
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
	public ControlAlg<IEvalBasic, IEvalBasic> controlAlg() {
		
		// TODO Auto-generated method stub
		return new ConcreteControlAlg(){
			
		};
	}

	@Override
	public ExpAlg<IEvalBasic> expAlg() {
		// TODO Auto-generated method stub
		return new ConcreteExpAlg(){
			
		};
	}


	@Override
	public EffectAlg<IEvalBasic, IEvalBasic> effectAlg() {
		return new ConcreteEffectAlg(){
		
		};
	}

	
	
}