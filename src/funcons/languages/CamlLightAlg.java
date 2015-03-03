package funcons.languages;

import funcons.core.ArithAlg;
import funcons.core.BoundAlg;
import funcons.core.ExpAlg;
import funcons.core.experiments.Abs2Alg;
import funcons.core.experiments.AnyAlg;
import funcons.core.experiments.ApplyAlg;
import funcons.core.experiments.BindAlg;
import funcons.core.experiments.CloseAlg;
import funcons.core.experiments.ClosureAlg;
import funcons.core.experiments.MatchAlg;
import funcons.core.experiments.OnlyAlg;
import funcons.core.experiments.PreferOverAlg;
import funcons.core.experiments.ScopeAlg;
import funcons.entities.I;
import funcons.entities.Value;
import funcons.entities.experiments.AnyPattern;
import funcons.entities.experiments.CompoundMatch;
import funcons.entities.experiments.ConstantPattern;
import funcons.entities.experiments.IdPattern;
import funcons.entities.experiments.Pattern;
import funcons.entities.experiments.SimpleMatch;
import funcons.entities.experiments.SimpleSimpleMatch;

/*
 * This language corresponds to section 2.2 of paper
 * "Reusable Components of Semantic Specifications"
 */

public interface CamlLightAlg<E,D,P,F>
//, F extends Abstraction<E,E>> 
	extends
	//AssignAlg<E>, 
	BoundAlg<E>,
	ClosureAlg<E, D>,
	//GivenAlg<E>, 
	//SeqAlg<E, E>,
	//EffectAlg<C, E>, 
	ExpAlg<E>, 
	BindAlg<E, D, P>, 
	AnyAlg<D,P>,
	OnlyAlg<D, P, E, E>,
	Abs2Alg<E,E,D,P>,
	CloseAlg<E>,
	MatchAlg<E, D, P>,
	ScopeAlg<D,E>,
	ArithAlg<E>{
	
	default PreferOverAlg<E, E> preferOverAlg(){
		return new PreferOverAlg<E, E>() {
			@Override
			public E else_(E x, E y) {
				return else__(x, y);
			}

			@Override
			public E abs(E x) {
				return absAlg().abs(x);
			}

			@Override
			public E getAbstracted(E x) {
				return absAlg().getAbstracted(x);
			}
		};
	}
	
	E else__(E x,E y);

	//SupplyAlg<E, E> supplyAlg();
	//Abs2Alg<E, E, D, P> abs2Alg();
	default ApplyAlg<E,E,E> applyFunAlg(){
		return new ApplyAlg<E,E,E>(){
			public E abs(E x) {
				return absAlg().abs(x);
			}

			@Override
			public E getAbstracted(E x) {
				return absAlg().getAbstracted(x);
			}

			@Override
			public E supply(E v, E x) {
				return funSupply(v, x);
			}
		};
	}
	
	E funSupply(E v, E x);
	
	//CloseAlg<To, Abstraction<Ctx, V>> closeAlg();
	//public AbsAlg<Ctx, V, Abstraction<Ctx, V>> absNewAlg();
	
	/*
	default E camlAssign(I i, E e){
		return supply(e, seq(assign(boundValue(i), given()), given()));
	}
	
	@SuppressWarnings("unchecked")
	default E camlSeq(E... es){
		if (es.length == 1){
			return es[0];
		}
		else if (es.length>=2){
			return seq(es[0], camlSeq(Arrays.copyOfRange(es, 1, es.length)));
		}else
			throw new RuntimeException("Bad number of arguments for a sequence.");
	}
	
	*/ 
	default E camlI(I i){
		return boundValue(i);
	}
	
	
	default E camlConstant(Value v){
		return constant(v);
	}
	
	
	default E camlApply(E e1, E e2){
		return applyFunAlg().apply(e1, e2);
	}
	
/*


	

	
	*/
	/*
	default Abs simpleMatch(SimpleSimpleMatch<E> sm){
		return abs(patt(sm.getP()), sm.getE());
	}
	*/
	/*
	//// ?????????????


	*/
	// We need to dispatch on E e1... for now we expect a Function
	default E camlApply(SimpleSimpleMatch<E> sm, E e2){
		return applyFunAlg().apply(function(sm), e2);
	}
	
	
	default E function(SimpleMatch<E> sm){
		return close(simpleMatchToAbs(sm));
	}

	default E camlAdd(E e1, E e2){
		return add(e1, e2);
	}
	
	
	default E simpleMatchToAbs(SimpleMatch<E> sm){
		if (sm instanceof SimpleSimpleMatch){
			SimpleSimpleMatch<E> ssm = (SimpleSimpleMatch<E>) sm;
			return abs(patt(ssm.getP()), ssm.getE());
		} else if (sm instanceof CompoundMatch){
			CompoundMatch<E> cm = (CompoundMatch<E>) sm;
			return preferOverAlg().preferOver(simpleMatchToAbs(cm.getMatch1()), simpleMatchToAbs(cm.getMatch2()));

		}else{
			throw new RuntimeException("Wrong simple match.");
		}
	}

	default E simpleLet(P p, E e, E body){
		return scope(declaration(p,e), body);
	}
	
	default D declaration(P p, E e){
		return match(e,p);
	}
	
	default P patt(Pattern p){
		if (p instanceof IdPattern){
			return bind(((IdPattern) p).getId());
		}
		else if (p instanceof AnyPattern){
			return any();
		}
		else if (p instanceof ConstantPattern){
			return only(camlConstant(((ConstantPattern) p).getValue()));
		}
		else
			throw new RuntimeException("Pattern not found");
	}

}