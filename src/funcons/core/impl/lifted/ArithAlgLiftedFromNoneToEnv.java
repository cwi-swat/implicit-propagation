package funcons.core.impl.lifted;

import funcons.core.ArithAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalEnv;

public interface ArithAlgLiftedFromNoneToEnv extends ArithAlg<IEvalEnv> {
	ArithAlg<IEvalBasic> arithAlg();
	
	@Override
	default IEvalEnv add(IEvalEnv l, IEvalEnv r) {
		return (env) -> arithAlg().add(() -> l.eval(env), () -> r.eval(env)).eval(); 
	}
}