package modules.whiledo;

import modules.arith.IEvalBase;
import modules.conditional.ConditionalAlg;
import modules.jumps.GotoAlg;
import modules.sequence.SeqAlg;
import modules.skip.SkipAlg;

public interface EvalWhile<Alg extends ConditionalAlg<IEvalBase> & GotoAlg<IEvalBase> & SeqAlg<IEvalBase> & SkipAlg<IEvalBase>> 
	extends WhileAlg<IEvalBase> {
	
	Alg alg();
	
	@Override
	default IEvalBase whileDo(IEvalBase cond, IEvalBase body) {
		return alg().label("next", alg().ifThen(cond, alg().seq(body, alg().jump("next")), alg().skip()));
	}
}
