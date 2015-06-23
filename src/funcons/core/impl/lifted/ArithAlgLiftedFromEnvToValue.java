package funcons.core.impl.lifted;

import funcons.core.ArithAlg;
import funcons.evaluators.IEvalEnv;
import funcons.evaluators.IEvalEnvValue;

public interface ArithAlgLiftedFromEnvToValue extends ArithAlg<IEvalEnvValue> {
	ArithAlg<IEvalEnv> arithAlg();
	
	@Override
	default IEvalEnvValue add(IEvalEnvValue l, IEvalEnvValue r) {
		return (env, store) -> arithAlg().add((e) -> l.eval(e, store), (e) -> r.eval(e, store)).eval(env);
	}
}