package funcons.languages;

import java.util.Arrays;

import funcons.core.AssignAlg;
import funcons.core.BoundAlg;
import funcons.core.EffectAlg;
import funcons.core.ConstantAlg;
import funcons.core.GivenAlg;
import funcons.core.SeqAlg;
import funcons.core.SupplyAlg;
import funcons.entities.I;
import funcons.entities.Value;

/*
 * This language corresponds to section 2.2 of paper
 * "Reusable Components of Semantic Specifications"
 */
public interface Language22Alg<C,E> extends AssignAlg<E>, 
	BoundAlg<E>, GivenAlg<E>, SeqAlg<E, E>, SupplyAlg<E, E>,
	EffectAlg<C, E>, ConstantAlg<E>{
	
	default E l22Assign(I i, E e){
		return supply(e, seq(assign(boundValue(i), given()), given()));
	}
	
	@SuppressWarnings("unchecked")
	default E l22Seq(E... es){
		if (es.length == 1){
			return es[0];
		}
		else if (es.length>=2){
			return seq(es[0], l22Seq(Arrays.copyOfRange(es, 1, es.length)));
		}else
			throw new RuntimeException("Bad number of arguments for a sequence.");
	}
	
	default C l22ExpSemiColon(E e){
		return effect(e);
	}
	
	default E l22I(I i){
		return assignedValue(boundValue(i));
	}
	
	default E l22Constant(Value v){
		return constant(v);
	}
}