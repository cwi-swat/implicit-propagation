package funcons.core.impl.envstorevalue;

import funcons.core.experiments.BindAlg;
import funcons.entities.Env;
import funcons.entities.I;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreVal;
import funcons.evaluators.IEvalEnvStoreValRetEnv;

public class ConcreteBindAlgEnvStoreValAlg implements BindAlg<IEvalEnvStoreVal, IEvalEnvStoreValRetEnv, IEvalEnvStoreValRetEnv>{

	@Override
	public IEvalEnvStoreValRetEnv abs(IEvalEnvStoreValRetEnv x) {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public IEvalEnvStoreValRetEnv getAbstracted(IEvalEnvStoreValRetEnv x) {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public IEvalEnvStoreVal given() {
			return esv -> esv._3();
	}

	@Override
	public IEvalEnvStoreValRetEnv bind(I id, IEvalEnvStoreVal x) {
		// TODO Auto-generated method stub
		return esv->{
			return new Env<Value>().bind(id.toString(), x.eval(esv));
		};
	}

	
}