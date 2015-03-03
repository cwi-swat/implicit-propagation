package funcons.languages;

import funcons.core.experiments.AbsAlg;
import funcons.entities.Bool;
import funcons.entities.Env;
import funcons.entities.I;
import funcons.entities.Int;
import funcons.entities.SkipEnv;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreValFail;
import funcons.evaluators.IEvalEnvStoreValFailRetEnv;

public class CamlLightConcreteAlgWithFailure implements
	CamlLightAlg<IEvalEnvStoreValFail, IEvalEnvStoreValFailRetEnv, IEvalEnvStoreValFailRetEnv,IEvalEnvStoreValFail>{

	
	/*
	@Override
	public IEvalEnvStoreValFail assign(IEvalEnvStoreValFail e1, IEvalEnvStoreValFail e2) {
		return esvf -> {
			Value v2 = e2.eval(esvf);
			Value v1 = e1.eval(esvf);
			esvf._2().update((Cell) v1, v2);
			return Skip.getInstance();
		};
	}

	@Override
	public IEvalEnvStoreValFail assignedValue(IEvalEnvStoreValFail e) {
		return esvf ->{
			Value v = e.eval(esvf);
			return esvf._2().deref((Cell) v);
		};
	}
	 */
	@Override
	public IEvalEnvStoreValFail boundValue(I i) {
		return esvf -> {
			Env<Value> env = esvf._1();
			return env.lookup(i.toString());
		};
	}

	@Override
	public IEvalEnvStoreValFail constant(Value v) {
		return esvf -> v;
	}


	/*
	
	AbsAlg<Value, IEvalEnvStoreValFail> abs2Alg(){
		return new AbsAlg<Value, IEvalEnvStoreValFail>() {

			@Override
			public IEvalEnvStoreValFail abs(Value x) {
				return esvf -> x;
			}

			@Override
			public Value getAbstracted(IEvalEnvStoreValFail x) {
				return x.eval(null);
			}
		};
	}
	*/
	
	@Override
	public IEvalEnvStoreValFail close(IEvalEnvStoreValFail x) {
		return esvf -> {
			return applyFunAlg().abs(closure(x, dummy -> esvf._1()));
		};
	}
	/*
	public Abstraction<IEvalEnvStoreValFail, IEvalEnvStoreValFail> close(
			Abstraction<IEvalEnvStoreValFail, IEvalEnvStoreValFail> x) {
		return absAlg().abs(closure(absAlg().getAbstracted(x),(rho) -> rho._1()));
	}*/

	/*
	public IEvalEnvStoreValFail getAbstracted2(
			Abstraction<Triple<Env<Value>, Store, Value>, Value> x) {
		return esvf -> {
			return x.eval(esvf);
		};
	}*/

	@Override
	public IEvalEnvStoreValFail given() {
		return esvf -> esvf._3();
	}


	@Override
	public IEvalEnvStoreValFailRetEnv bind(I id, IEvalEnvStoreValFail x) {
		return esvf->{
			return new Env<Value>().bind(id.toString(), x.eval(esvf));
		};
	}

	@Override
	public IEvalEnvStoreValFail scope(IEvalEnvStoreValFailRetEnv d, IEvalEnvStoreValFail e) {
		return esvf -> {
			Env<Value> rho1 = d.eval(esvf);
			return e.eval(esvf.replace1(esvf._1().overrideBy(rho1)));
		};

	}


	@Override
	public IEvalEnvStoreValFail add(IEvalEnvStoreValFail e1, IEvalEnvStoreValFail e2) {
		// TODO Auto-generated method stub
		return (esvf) -> new Int(((Int) e1.eval(esvf)).getValue() + ((Int) e2.eval(esvf)).getValue());
	}

	
	@Override
	public AbsAlg<IEvalEnvStoreValFail, IEvalEnvStoreValFail> absAlg() {
		return new AbsAlg<IEvalEnvStoreValFail, IEvalEnvStoreValFail>() {

			@Override
			public IEvalEnvStoreValFail abs(
					IEvalEnvStoreValFail x) {
				return x;
			}

			// This is the trickiest and more interesting code as it tells how to represents
			// abstractions
			@Override
			public IEvalEnvStoreValFail getAbstracted(
					IEvalEnvStoreValFail x) {
				return esvf ->{
					Value v = x.eval(esvf);
					if (v instanceof IEvalEnvStoreValFail)
						return ((IEvalEnvStoreValFail) v).eval(esvf);
					else
						return v;
				};

			}
		};
	}
	/*
	
	public ApplyAlg<Triple<Env<Value>, Store, Value>, Value, IEvalEnvStoreValFail> applyFunAlg() {
		return new ApplyAlg<Triple<Env<Value>, Store, Value>, Value, IEvalEnvStoreValFail>() {


	
			@Override
			public IEvalEnvStoreValFail abs(Value x) {
				// TODO Auto-generated method stub
				return (IEvalEnvStoreValFail) x;
			}


			@Override
			public Value getAbstracted(IEvalEnvStoreValFail x) {
				return x;
			}



			@Override
			public Value supply(Triple<Env<Value>, Store, Value> v, Value x_) {
				IEvalEnvStoreValFail x = (IEvalEnvStoreValFail) x_;
				return (IEvalEnvStoreValFail) (esvf ->{
					return x.eval(esvf.replace3(v._3()));
				});
			}
		};
	}
	*/

	
	@Override
	public IEvalEnvStoreValFailRetEnv supply(IEvalEnvStoreValFail v,
			IEvalEnvStoreValFailRetEnv x) {
		return (esvf) ->{
			System.out.println(x.eval(esvf.replace3(v.eval(esvf))).toString());
			return x.eval(esvf.replace3(v.eval(esvf)));
			//return e2.eval(esvf).lookup(name)(esvf.replace3(e1.eval(esvf)));
			
		};
	}

/*
	@Override
	public AbsAlg<IEvalEnvStoreValFail, IEvalEnvStoreValFail> absAlg() {
		// TODO Auto-generated method stub
		return new AbsAlg<IEvalEnvStoreValFail, IEvalEnvStoreValFail>() {
			
			@Override
			public IEvalEnvStoreValFail getAbstracted(IEvalEnvStoreValFail x) {
				return x;
			}
			
			@Override
			public IEvalEnvStoreValFail abs(IEvalEnvStoreValFail x) {
				return x;
			}
		};
	}
*/
	@Override
	public IEvalEnvStoreValFail closure(IEvalEnvStoreValFail x,
			IEvalEnvStoreValFailRetEnv rho) {
		return (esvf) -> {
			return x.eval(esvf.replace1(rho.eval(esvf)));
		};
	}
	

	@Override
	public IEvalEnvStoreValFailRetEnv abs(IEvalEnvStoreValFailRetEnv x) {
		return x;
	}

	@Override
	public IEvalEnvStoreValFailRetEnv getAbstracted(IEvalEnvStoreValFailRetEnv x) {
		// TODO Auto-generated method stub
		return x;
	}


	@Override
	public IEvalEnvStoreValFail funSupply(IEvalEnvStoreValFail v,
			IEvalEnvStoreValFail x) {
		return (esvf) ->{
			System.out.println(esvf);
			System.out.println(((Int) v.eval(esvf)).getValue());
			//System.out.println(((Int)(((IEvalEnvStoreValFail) x.eval(esvf)).eval(esvf.replace3(v.eval(esvf))))).getValue());
			System.out.println((x.eval(esvf.replace3(v.eval(esvf)))));
			
			//Value succFunction = x.eval(esvf);
			return x.eval(esvf.replace3(v.eval(esvf)));
			//return ((Abstraction<Triple<Env<Value>, Store, Value>, Value>) x.eval(esvf)).eval(esvf.replace3(v.eval(esvf)));
			
		};
	}

	@Override
	public IEvalEnvStoreValFail else__(IEvalEnvStoreValFail x, IEvalEnvStoreValFail y) {
		return (esvf) -> {
			Value v = x.eval(esvf);
			if (esvf._4().getBool()){
				esvf._4().setBool(false);
				return y.eval(esvf);
			}else{
				return v;
			}
		};
	}

	@Override
	public IEvalEnvStoreValFailRetEnv emptyEnv() {
		return esvf -> new Env<Value>();
	}

	@Override
	public IEvalEnvStoreValFailRetEnv whenTrue(IEvalEnvStoreValFail cond,
			IEvalEnvStoreValFailRetEnv x) {
		return esvf -> {
			boolean condition = ((Bool) cond.eval(esvf)).getValue();
			if (condition){
				esvf._4().setBool(false);
				return x.eval(esvf);
			}
			else{
				esvf._4().setBool(true);
				return SkipEnv.getInstance();
			}
		};
	}

	@Override
	public IEvalEnvStoreValFail equal(IEvalEnvStoreValFail e1, IEvalEnvStoreValFail e2) {
		return esvf -> {
			return new Bool(e1.eval(esvf).equals(e2.eval(esvf)));
		};
	}

	/*
	@Override
	public IEvalEnvStoreValFail closure(Value x,
			IEvalEnvStoreValFailRetEnv rho) {
		return (esvf) -> {
			return x.eval(esvf.replace1(rho.eval(esvf)));
		};
	}

	 */
	

}
