package funcons.languages.experiments;

import funcons.core.ArithAlg;
import funcons.core.BoundAlg;
import funcons.core.ConstantAlg;
import funcons.core.GivenAlg;
import funcons.core.SupplyAlg;
import funcons.core.experiments.AbsAlg;
import funcons.core.experiments.BindAlg;
import funcons.core.experiments.EmptyEnvAlg;
import funcons.core.experiments.ScopeAlg;
import funcons.core.impl.basic.ConcreteArithAlg;
import funcons.core.impl.basic.ConcreteBindAlg;
import funcons.core.impl.basic.ConcreteBoundAlg;
import funcons.core.impl.basic.ConcreteConstantAlg;
import funcons.core.impl.basic.ConcreteEmptyEnvAlg;
import funcons.core.impl.basic.ConcreteGivenAlg;
import funcons.core.impl.basic.ConcretePatternsAlg;
import funcons.core.impl.basic.ConcreteScopeAlg;
import funcons.core.impl.basic.ConcreteSupplyPattAlg;
import funcons.core.impl.lifted.ArithAlgLiftedFromNoneToEnv;
import funcons.core.impl.lifted.ConstantAlgLiftedFromNoneToValue;
import funcons.core.impl.lifted.EmptyEnvLiftedFromNoneToValueAlg;
import funcons.evaluators.IEvalBasic;
import funcons.evaluators.IEvalBasicRetEnv;
import funcons.evaluators.IEvalEnv;
import funcons.evaluators.IEvalEnvValue;
import funcons.evaluators.IEvalValue;
import funcons.evaluators.IEvalValueRetEnv;

public class CompletePatternsAlg implements ConcretePatternsAlg {

	@Override
	public BindAlg<IEvalValue, IEvalValueRetEnv, IEvalBasic> bindAlg() {
		return new ConcreteBindAlg() {
		};
	}

	@Override
	public SupplyAlg<IEvalValue, IEvalValueRetEnv> supplyAlg() {
		return new ConcreteSupplyPattAlg() {
		};
	}

	@Override
	public EmptyEnvAlg<IEvalValueRetEnv> emptyEnvAlg() {
		return new EmptyEnvLiftedFromNoneToValueAlg() {

			@Override
			public EmptyEnvAlg<IEvalBasicRetEnv> emptyEnvAlg() {
				return new ConcreteEmptyEnvAlg() {
				};
			}
		};
	}

	@Override
	public GivenAlg<IEvalValue> givenAlg() {
		return new ConcreteGivenAlg() {
		};
	}

	@Override
	public ConstantAlg<IEvalValue> constantAlg() {
		return new ConstantAlgLiftedFromNoneToValue() {

			@Override
			public ConstantAlg<IEvalBasic> constantAlg() {
				return new ConcreteConstantAlg() {
				};
			}
		};
	}

	@Override
	public ScopeAlg<IEvalBasicRetEnv, IEvalEnv> scopeAlg() {
		return new ConcreteScopeAlg() {
		};
	}

	@Override
	public AbsAlg<IEvalEnvValue, IEvalEnvValue> absAlg() {
		return new AbsAlg<IEvalEnvValue, IEvalEnvValue>() {

			@Override
			public IEvalEnvValue getAbstracted(IEvalEnvValue x) {
				return x;
			}

			@Override
			public IEvalEnvValue abs(IEvalEnvValue x) {
				// TODO Auto-generated method stub
				return x;
			}
		};
	}

	@Override
	public BoundAlg<IEvalEnv> boundAlg() {
		return new ConcreteBoundAlg() {
		};
	}

	@Override
	public ArithAlg<IEvalEnv> arithAlg() {
		return new ArithAlgLiftedFromNoneToEnv() {

			@Override
			public ArithAlg<IEvalBasic> arithAlg() {
				return new ConcreteArithAlg() {

				};
			}
		};
	}

}
