package funcons.core.experiments;

import javafx.util.Pair;
import funcons.entities.Failure;
import funcons.entities.None;
import funcons.entities.Value;
import funcons.evaluators.IEvalFailure;

public interface ElseAlg<X> {
	X else_(X x, X y);
}

class ConcreteElseAlg implements ElseAlg<IEvalFailure>{

	@Override
	public IEvalFailure else_(IEvalFailure x, IEvalFailure y) {
		return (none) -> {
			Pair<Value, Failure> rx = x.eval(new None());
			if (rx.getValue().getBool()){
				// This is not equals to the semantics in the paper, as in this case we are expressing
				// the rule in big-step style. It is well known that big-step is not suitable to model
				// abrupt termination
				return new Pair<Value,Failure>(y.eval(new None()).getKey(), new Failure(false));
			}else{
				return rx;
			}
		};
	}
	
}