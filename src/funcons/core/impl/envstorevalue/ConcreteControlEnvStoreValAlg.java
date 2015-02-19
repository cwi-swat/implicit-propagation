package funcons.core.impl.envstorevalue;

import funcons.core.ControlAlg;
import funcons.entities.Bool;
import funcons.entities.Value;
import funcons.entities.Skip;
import funcons.evaluators.IEvalEnvStoreVal;

public interface ConcreteControlEnvStoreValAlg extends ControlAlg<IEvalEnvStoreVal, IEvalEnvStoreVal>{
	
	@Override
	default IEvalEnvStoreVal ifTrue(IEvalEnvStoreVal e, IEvalEnvStoreVal c1, IEvalEnvStoreVal c2) {
		return (rho, store, value) -> {
			Value v = e.eval(rho, store, value);
			if (v instanceof Bool){
				if (((Bool) v).getValue())
					return c1.eval(rho, store, value);
				else {
					return c2.eval(rho, store, value);
				}
			}
			else 
				throw new RuntimeException("Stuck");
		};
	}
	
	@Override
	default IEvalEnvStoreVal skip(){
		return (rho, store, value) -> Skip.getInstance();
	}
	
}
