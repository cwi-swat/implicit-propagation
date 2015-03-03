package funcons.core.impl.envstorevalue;

import funcons.core.ControlAlg;
import funcons.entities.Bool;
import funcons.entities.Value;
import funcons.entities.Skip;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteControlEnvStoreValAlg extends ControlAlg<IEvalEnvStoreVal, IEvalEnvStoreVal>{
	
	@Override
	default IEvalEnvStoreVal ifTrue(IEvalEnvStoreVal e, IEvalEnvStoreVal c1, IEvalEnvStoreVal c2) {
		return rhs -> {
			Value v = e.eval(rhs);
			if (v instanceof Bool){
				if (((Bool) v).getValue())
					return c1.eval(rhs);
				else {
					return c2.eval(rhs);
				}
			}
			else 
				throw new RuntimeException("Stuck");
		};
	}
	
	@Override
	default IEvalEnvStoreVal skip(){
		return (rhs) -> Skip.getInstance();
	}
	
}
