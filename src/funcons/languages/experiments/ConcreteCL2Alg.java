package funcons.languages.experiments;

import funcons.core.impl.basic.FunctionsAlg;
import funcons.core.impl.basic.PatternsAlg;
import funcons.entities.I;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalEnvValueRetEnv;


public class ConcreteCL2Alg
		implements
		CLAlg<IEvalEnvValue, IEvalEnvValueRetEnv, IEvalEnvValue, IEvalBasic, IEvalEnvValueRetEnv, IEvalEnvValue> {

	
	public FunctionsAlg<IEvalEnvValue, IEvalEnvValue, IEvalEnvValue> funInterpreter() {
		return new CompleteFunctions2Alg();
	}

	public PatternsAlg<IEvalEnvValue, IEvalEnvValueRetEnv, IEvalBasic> pattInterpreter() {
		return new CompletePatternsAlg();
	}

	@Override
	public IEvalEnvValue function(IEvalEnvValue sm) {
		return funInterpreter().abs(sm);
	}

	@Override
	public IEvalEnvValue apply(IEvalEnvValue e1, IEvalEnvValue e2) {
		return funInterpreter().apply(e1, e2);
	}
	
	@Override
	public IEvalEnvValue simpleMatch(IEvalBasic p, IEvalEnvValue e) {
		return pattInterpreter().abs(p, e);
	}

	@Override
	public IEvalEnvValueRetEnv simpleDecl(IEvalBasic p, IEvalEnvValue e) {
		return pattInterpreter().match(e, p);
	}

	@Override
	public IEvalEnvValue simpleLet(IEvalEnvValueRetEnv lb, IEvalEnvValue e) {
		return pattInterpreter().scope(lb, e);
	}

	@Override
	public IEvalBasic idPatt(String i) {
		return pattInterpreter().bind(new I(i));
	}

	@Override
	public IEvalEnvValue constant(Value v) {
		return pattInterpreter().constant(v);
	}

	@Override
	public IEvalEnvValue boundValue(String s) {
		return pattInterpreter().boundValue(new I(s));
	}

	@Override
	public IEvalEnvValue add(IEvalEnvValue e1, IEvalEnvValue e2) {
		return pattInterpreter().add(e1, e2);
	}

}