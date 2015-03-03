package funcons.languages;

import funcons.core.experiments.AbsAlg;
import funcons.entities.Bool;
import funcons.entities.Env;
import funcons.entities.FailureE;
import funcons.entities.I;
import funcons.entities.Int;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreVal;
import funcons.evaluators.IEvalEnvStoreValRetEnv;

public class CamlLightConcreteAlg implements
	CamlLightAlg<IEvalEnvStoreVal, IEvalEnvStoreValRetEnv, IEvalEnvStoreValRetEnv,IEvalEnvStoreVal>{

	
	/*
	@Override
	public IEvalEnvStoreVal assign(IEvalEnvStoreVal e1, IEvalEnvStoreVal e2) {
		return esv -> {
			Value v2 = e2.eval(esv);
			Value v1 = e1.eval(esv);
			esv._2().update((Cell) v1, v2);
			return Skip.getInstance();
		};
	}

	@Override
	public IEvalEnvStoreVal assignedValue(IEvalEnvStoreVal e) {
		return esv ->{
			Value v = e.eval(esv);
			return esv._2().deref((Cell) v);
		};
	}
	 */
	@Override
	public IEvalEnvStoreVal boundValue(I i) {
		return esv -> {
			Env<Value> env = esv._1();
			return env.lookup(i.toString());
		};
	}

	@Override
	public IEvalEnvStoreVal constant(Value v) {
		return esv -> v;
	}


	/*
	
	AbsAlg<Value, IEvalEnvStoreVal> abs2Alg(){
		return new AbsAlg<Value, IEvalEnvStoreVal>() {

			@Override
			public IEvalEnvStoreVal abs(Value x) {
				return esv -> x;
			}

			@Override
			public Value getAbstracted(IEvalEnvStoreVal x) {
				return x.eval(null);
			}
		};
	}
	*/
	
	@Override
	public IEvalEnvStoreVal close(IEvalEnvStoreVal x) {
		return esv -> {
			return applyFunAlg().abs(closure(x, dummy -> esv._1()));
		};
	}
	/*
	public Abstraction<IEvalEnvStoreVal, IEvalEnvStoreVal> close(
			Abstraction<IEvalEnvStoreVal, IEvalEnvStoreVal> x) {
		return absAlg().abs(closure(absAlg().getAbstracted(x),(rho) -> rho._1()));
	}*/

	/*
	public IEvalEnvStoreVal getAbstracted2(
			Abstraction<Triple<Env<Value>, Store, Value>, Value> x) {
		return esv -> {
			return x.eval(esv);
		};
	}*/

	@Override
	public IEvalEnvStoreVal given() {
		return esv -> esv._3();
	}


	@Override
	public IEvalEnvStoreValRetEnv bind(I id, IEvalEnvStoreVal x) {
		return esv->{
			return new Env<Value>().bind(id.toString(), x.eval(esv));
		};
	}

	@Override
	public IEvalEnvStoreVal scope(IEvalEnvStoreValRetEnv d, IEvalEnvStoreVal e) {
		return esv -> {
			Env<Value> rho1 = d.eval(esv);
			return e.eval(esv.replace1(esv._1().overrideBy(rho1)));
		};

	}


	@Override
	public IEvalEnvStoreVal add(IEvalEnvStoreVal e1, IEvalEnvStoreVal e2) {
		// TODO Auto-generated method stub
		return (esv) -> new Int(((Int) e1.eval(esv)).getValue() + ((Int) e2.eval(esv)).getValue());
	}

	
	@Override
	public AbsAlg<IEvalEnvStoreVal, IEvalEnvStoreVal> absAlg() {
		return new AbsAlg<IEvalEnvStoreVal, IEvalEnvStoreVal>() {

			@Override
			public IEvalEnvStoreVal abs(
					IEvalEnvStoreVal x) {
				return x;
			}

			// This is the trickiest and more interesting code as it tells how to represents
			// abstractions
			@Override
			public IEvalEnvStoreVal getAbstracted(
					IEvalEnvStoreVal x) {
				return esv ->{
					Value v = x.eval(esv);
					if (v instanceof IEvalEnvStoreVal)
						return ((IEvalEnvStoreVal) v).eval(esv);
					else
						return v;
				};

			}
		};
	}
	/*
	
	public ApplyAlg<Triple<Env<Value>, Store, Value>, Value, IEvalEnvStoreVal> applyFunAlg() {
		return new ApplyAlg<Triple<Env<Value>, Store, Value>, Value, IEvalEnvStoreVal>() {


	
			@Override
			public IEvalEnvStoreVal abs(Value x) {
				// TODO Auto-generated method stub
				return (IEvalEnvStoreVal) x;
			}


			@Override
			public Value getAbstracted(IEvalEnvStoreVal x) {
				return x;
			}



			@Override
			public Value supply(Triple<Env<Value>, Store, Value> v, Value x_) {
				IEvalEnvStoreVal x = (IEvalEnvStoreVal) x_;
				return (IEvalEnvStoreVal) (esv ->{
					return x.eval(esv.replace3(v._3()));
				});
			}
		};
	}
	*/

	
	@Override
	public IEvalEnvStoreValRetEnv supply(IEvalEnvStoreVal v,
			IEvalEnvStoreValRetEnv x) {
		return (esv) ->{
			System.out.println(x.eval(esv.replace3(v.eval(esv))).toString());
			return x.eval(esv.replace3(v.eval(esv)));
			//return e2.eval(esv).lookup(name)(esv.replace3(e1.eval(esv)));
			
		};
	}

/*
	@Override
	public AbsAlg<IEvalEnvStoreVal, IEvalEnvStoreVal> absAlg() {
		// TODO Auto-generated method stub
		return new AbsAlg<IEvalEnvStoreVal, IEvalEnvStoreVal>() {
			
			@Override
			public IEvalEnvStoreVal getAbstracted(IEvalEnvStoreVal x) {
				return x;
			}
			
			@Override
			public IEvalEnvStoreVal abs(IEvalEnvStoreVal x) {
				return x;
			}
		};
	}
*/
	@Override
	public IEvalEnvStoreVal closure(IEvalEnvStoreVal x,
			IEvalEnvStoreValRetEnv rho) {
		return (esv) -> {
			return x.eval(esv.replace1(rho.eval(esv)));
		};
	}
	

	@Override
	public IEvalEnvStoreValRetEnv abs(IEvalEnvStoreValRetEnv x) {
		return x;
	}

	@Override
	public IEvalEnvStoreValRetEnv getAbstracted(IEvalEnvStoreValRetEnv x) {
		// TODO Auto-generated method stub
		return x;
	}


	@Override
	public IEvalEnvStoreVal funSupply(IEvalEnvStoreVal v,
			IEvalEnvStoreVal x) {
		return (esv) ->{
			System.out.println(esv);
			System.out.println(((Int) v.eval(esv)).getValue());
			//System.out.println(((Int)(((IEvalEnvStoreVal) x.eval(esv)).eval(esv.replace3(v.eval(esv))))).getValue());
			System.out.println((x.eval(esv.replace3(v.eval(esv)))));
			
			//Value succFunction = x.eval(esv);
			return x.eval(esv.replace3(v.eval(esv)));
			//return ((Abstraction<Triple<Env<Value>, Store, Value>, Value>) x.eval(esv)).eval(esv.replace3(v.eval(esv)));
			
		};
	}

	@Override
	public IEvalEnvStoreVal else__(IEvalEnvStoreVal x, IEvalEnvStoreVal y) {
		return (esv) -> {
			try{
				Value rx = x.eval(esv);
				return rx;
			} catch (FailureE f){
				return y.eval(esv);
			}
			
		};
	}

	@Override
	public IEvalEnvStoreValRetEnv emptyEnv() {
		return esv -> new Env<Value>();
	}

	@Override
	public IEvalEnvStoreValRetEnv whenTrue(IEvalEnvStoreVal cond,
			IEvalEnvStoreValRetEnv x) {
		return esv -> {
			boolean condition = ((Bool) cond.eval(esv)).getValue();
			if (condition){
				return x.eval(esv);
			}
			else{
				throw new FailureE();
			}
			
		};
	}

	@Override
	public IEvalEnvStoreVal equal(IEvalEnvStoreVal e1, IEvalEnvStoreVal e2) {
		return esv -> {
			return new Bool(e1.eval(esv).equals(e2.eval(esv)));
		};
	}

	/*
	@Override
	public IEvalEnvStoreVal closure(Value x,
			IEvalEnvStoreValRetEnv rho) {
		return (esv) -> {
			return x.eval(esv.replace1(rho.eval(esv)));
		};
	}

	 */
	

}
