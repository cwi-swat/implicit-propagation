package funcons.languages.impl;

import funcons.core.impl.envstorevalue.ConcreteAssignEnvStoreValAlg;
import funcons.core.impl.envstorevalue.ConcreteBoundEnvStoreValAlg;
import funcons.core.impl.envstorevalue.ConcreteEffectEnvStoreValAlg;
import funcons.core.impl.envstorevalue.ConcreteExpEnvStoreValAlg;
import funcons.core.impl.envstorevalue.ConcreteGivenEnvStoreValAlg;
import funcons.core.impl.envstorevalue.ConcreteSeqEnvStoreValAlg;
import funcons.core.impl.envstorevalue.ConcreteSupplyEnvStoreValAlg;
import funcons.evaluators.IEvalEnvStoreVal;
import funcons.languages.Language22Alg;

public interface ConcreteLanguage22Alg
	extends 
	Language22Alg<IEvalEnvStoreVal, IEvalEnvStoreVal>,
	ConcreteExpEnvStoreValAlg,
	ConcreteEffectEnvStoreValAlg,
	ConcreteSeqEnvStoreValAlg,
	ConcreteGivenEnvStoreValAlg,
	ConcreteSupplyEnvStoreValAlg,
	ConcreteBoundEnvStoreValAlg,
	ConcreteAssignEnvStoreValAlg{
	
}