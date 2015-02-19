package funcons.evaluators;

import funcons.entities.Store;
import funcons.entities.Value;

@FunctionalInterface
public interface IEvalStore{
	Value eval(Store store);
}