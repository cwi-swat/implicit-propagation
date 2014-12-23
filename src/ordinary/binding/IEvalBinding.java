package ordinary.binding;

import ordinary.arith.IEvalBase;
import modules.binding.Env;
import modules.values.Value;

public interface IEvalBinding extends IEvalBase {
	Value eval(Env env);
}
