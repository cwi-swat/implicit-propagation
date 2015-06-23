package funcons.languages;

import funcons.core.experiments.AbsAlg;
import funcons.entities.Bool;
import funcons.entities.Env;
import funcons.entities.FailureE;
import funcons.entities.I;
import funcons.entities.Int;
import funcons.entities.Value;
import funcons.evaluators.IEvalEnvStoreValMulti;
import funcons.evaluators.IEvalEnvStoreValMultiRetEnv;

public class CamlLightConcreteMultiAlg implements
	CamlLightAlg<IEvalEnvStoreValMulti, IEvalEnvStoreValMultiRetEnv, IEvalEnvStoreValMultiRetEnv,IEvalEnvStoreValMulti>{

	
	/*
	@Override
	public IEvalEnvStoreValMulti assign(IEvalEnvStoreValMulti e1, IEvalEnvStoreValMulti e2) {
		return (e, s, v) -> {
			Value v2 = e2.eval(esv);
			Value v1 = e1.eval(esv);
			esv._2().update((Cell) v1, v2);
			return Skip.getInstance();
		};
	}

	@Override
	public IEvalEnvStoreValMulti assignedValue(IEvalEnvStoreValMulti e) {
		return (e, s, v) ->{
			Value v = e.eval(esv);
			return esv._2().deref((Cell) v);
		};
	}
	 */
	@Override
	public IEvalEnvStoreValMulti boundValue(I i) {
		return (e, s, v) -> {
			return e.lookup(i.toString());
		};
	}

	@Override
	public IEvalEnvStoreValMulti constant(Value v) {
		return (e, s, v_) -> v;
	}


	/*
	
	AbsAlg<Value, IEvalEnvStoreValMulti> abs2Alg(){
		return new AbsAlg<Value, IEvalEnvStoreValMulti>() {

			@Override
			public IEvalEnvStoreValMulti abs(Value x) {
				return (e, s, v) -> x;
			}

			@Override
			public Value getAbstracted(IEvalEnvStoreValMulti x) {
				return x.eval(null);
			}
		};
	}
	*/
	
	@Override
	public IEvalEnvStoreValMulti close(IEvalEnvStoreValMulti x) {
		// The TRICK here is that the Abstraction is also a Value
		return (e, s, v) -> {
			return applyFunAlg().abs(closure(x, (a,b,c) -> e));
		};
	}
	/*
	public Abstraction<IEvalEnvStoreValMulti, IEvalEnvStoreValMulti> close(
			Abstraction<IEvalEnvStoreValMulti, IEvalEnvStoreValMulti> x) {
		return absAlg().abs(closure(absAlg().getAbstracted(x),(rho) -> rho._1()));
	}*/

	/*
	public IEvalEnvStoreValMulti getAbstracted2(
			Abstraction<Triple<Env<Value>, Store, Value>, Value> x) {
		return (e, s, v) -> {
			return x.eval(esv);
		};
	}*/

	@Override
	public IEvalEnvStoreValMulti given() {
		return (e, s, v) -> v;
	}


	@Override
	public IEvalEnvStoreValMultiRetEnv bind(I id, IEvalEnvStoreValMulti x) {
		return (e, s, v) ->{
			return new Env<Value>().bind(id.toString(), x.eval(e, s, v));
		};
	}

	@Override
	public IEvalEnvStoreValMulti scope(IEvalEnvStoreValMultiRetEnv d, IEvalEnvStoreValMulti ee) {
		return (e, s, v) -> {
			Env<Value> rho1 = d.eval(e,s,v);
			return ee.eval(e.overrideBy(rho1), s, v);
		};

	}


	@Override
	public IEvalEnvStoreValMulti add(IEvalEnvStoreValMulti e1, IEvalEnvStoreValMulti e2) {
		// TODO Auto-generated method stub
		return (e, s, v) -> new Int(((Int) e1.eval(e,s,v)).getValue() + ((Int) e2.eval(e,s,v)).getValue());
	}

	
	@Override
	public AbsAlg<IEvalEnvStoreValMulti, IEvalEnvStoreValMulti> absAlg() {
		return new AbsAlg<IEvalEnvStoreValMulti, IEvalEnvStoreValMulti>() {

			@Override
			public IEvalEnvStoreValMulti abs(
					IEvalEnvStoreValMulti x) {
				return x;
			}

			// This is the trickiest and more interesting code as it tells how to represents
			// abstractions
			@Override
			public IEvalEnvStoreValMulti getAbstracted(
					IEvalEnvStoreValMulti x) {
				return (e, s, v) ->{
					Value v_ = x.eval(e,s,v);
					if (v_ instanceof IEvalEnvStoreValMulti)
						return ((IEvalEnvStoreValMulti) v_).eval(e,s,v);
					else
						return v_;
				};

			}
		};
	}
	/*
	
	public ApplyAlg<Triple<Env<Value>, Store, Value>, Value, IEvalEnvStoreValMulti> applyFunAlg() {
		return new ApplyAlg<Triple<Env<Value>, Store, Value>, Value, IEvalEnvStoreValMulti>() {


	
			@Override
			public IEvalEnvStoreValMulti abs(Value x) {
				// TODO Auto-generated method stub
				return (IEvalEnvStoreValMulti) x;
			}


			@Override
			public Value getAbstracted(IEvalEnvStoreValMulti x) {
				return x;
			}



			@Override
			public Value supply(Triple<Env<Value>, Store, Value> v, Value x_) {
				IEvalEnvStoreValMulti x = (IEvalEnvStoreValMulti) x_;
				return (IEvalEnvStoreValMulti) ((e, s, v) ->{
					return x.eval(esv.replace3(v._3()));
				});
			}
		};
	}
	*/

	
	@Override
	public IEvalEnvStoreValMultiRetEnv supply(IEvalEnvStoreValMulti v,
			IEvalEnvStoreValMultiRetEnv x) {
		return (e, s, v_) ->{
			//System.out.println(x.eval(esv.replace3(v.eval(esv))).toString());
			//return x.eval(esv.replace3(v.eval(esv)));
			return x.eval(e,s, v.eval(e,s,v_));
			//return e2.eval(esv).lookup(name)(esv.replace3(e1.eval(esv)));
			
		};
	}

/*
	@Override
	public AbsAlg<IEvalEnvStoreValMulti, IEvalEnvStoreValMulti> absAlg() {
		// TODO Auto-generated method stub
		return new AbsAlg<IEvalEnvStoreValMulti, IEvalEnvStoreValMulti>() {
			
			@Override
			public IEvalEnvStoreValMulti getAbstracted(IEvalEnvStoreValMulti x) {
				return x;
			}
			
			@Override
			public IEvalEnvStoreValMulti abs(IEvalEnvStoreValMulti x) {
				return x;
			}
		};
	}
*/
	@Override
	public IEvalEnvStoreValMulti closure(IEvalEnvStoreValMulti x,
			IEvalEnvStoreValMultiRetEnv rho) {
		return (e, s, v) -> {
			return x.eval(rho.eval(e,s,v), s , v);
		};
	}
	

	@Override
	public IEvalEnvStoreValMultiRetEnv abs(IEvalEnvStoreValMultiRetEnv x) {
		return x;
	}

	@Override
	public IEvalEnvStoreValMultiRetEnv getAbstracted(IEvalEnvStoreValMultiRetEnv x) {
		// TODO Auto-generated method stub
		return x;
	}


	@Override
	public IEvalEnvStoreValMulti funSupply(IEvalEnvStoreValMulti v,
			IEvalEnvStoreValMulti x) {
		return (e, s, v_) ->{
			//System.out.println(esv);
			//System.out.println(((Int) v.eval(esv)).getValue());
			//System.out.println(((Int)(((IEvalEnvStoreValMulti) x.eval(esv)).eval(esv.replace3(v.eval(esv))))).getValue());
			//System.out.println((x.eval(esv.replace3(v.eval(esv)))));
			
			//Value succFunction = x.eval(esv);
			return x.eval(e, s ,v.eval(e, s, v_));
			//return ((Abstraction<Triple<Env<Value>, Store, Value>, Value>) x.eval(esv)).eval(esv.replace3(v.eval(esv)));
			
		};
	}

	@Override
	public IEvalEnvStoreValMulti else__(IEvalEnvStoreValMulti x, IEvalEnvStoreValMulti y) {
		return (e, s, v) -> {
			try{
				Value rx = x.eval(e, s, v);
				return rx;
			} catch (FailureE f){
				return y.eval(e, s, v);
			}
			
		};
	}

	@Override
	public IEvalEnvStoreValMultiRetEnv emptyEnv() {
		return (e, s, v) -> new Env<Value>();
	}

	@Override
	public IEvalEnvStoreValMultiRetEnv whenTrue(IEvalEnvStoreValMulti cond,
			IEvalEnvStoreValMultiRetEnv x) {
		return (e, s, v) -> {
			boolean condition = ((Bool) cond.eval(e, s, v)).getValue();
			if (condition){
				return x.eval(e, s, v);
			}
			else{
				throw new FailureE();
			}
			
		};
	}

	@Override
	public IEvalEnvStoreValMulti equal(IEvalEnvStoreValMulti e1, IEvalEnvStoreValMulti e2) {
		return (e, s, v) -> {
			return new Bool(e1.eval(e, s, v).equals(e2.eval(e, s, v)));
		};
	}

	/*
	@Override
	public IEvalEnvStoreValMulti closure(Value x,
			IEvalEnvStoreValMultiRetEnv rho) {
		return (e, s, v) -> {
			return x.eval(esv.replace1(rho.eval(esv)));
		};
	}

	 */
	

}
