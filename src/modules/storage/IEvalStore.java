package modules.storage;

import modules.values.Value;

@FunctionalInterface
public interface IEvalStore {
	Value eval(Store store); 
}
