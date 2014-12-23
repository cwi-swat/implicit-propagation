package annos;

import modules.arith.ArithAlg;
import modules.arith.IEvalBase;
import modules.binding.IEvalBinding;

@Propagate(IEvalBase.class) 
public interface EvalArithWithBinding extends ArithAlg<IEvalBinding> {

}
