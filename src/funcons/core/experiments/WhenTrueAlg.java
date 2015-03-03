package funcons.core.experiments;

import javafx.util.Pair;
import funcons.entities.Bool;
import funcons.entities.Failure;
import funcons.entities.None;
import funcons.entities.Value;
import funcons.evaluators.IEvalBasicNone;
import funcons.evaluators.IEvalFailure;

public interface WhenTrueAlg<E,X> {
	X whenTrue(E cond, X x);
}

class ConcreteWhenTrueAlg implements WhenTrueAlg<IEvalBasicNone, IEvalFailure>{

	@Override
	public IEvalFailure whenTrue(IEvalBasicNone cond, IEvalFailure x) {
		return (none) -> {
			boolean condition = ((Bool) cond.eval(new None())).getValue();
			if (condition){
				return x.eval(none);
			}
			else{
				// This is not equals to the semantics in the paper, as in this case we are expressing
				// the rule in big-step style. It is well known that big-step is not suitable to model
				// abrupt termination
				return new Pair<Value,Failure>(x.eval(new None()).getKey(), new Failure(true));
			}
			
		};
	}
	
}