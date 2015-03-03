package funcons.entities;

@FunctionalInterface
public interface ParameterlessAbstraction<To> extends Abstraction<None, To>{
	To eval(None dummy);
}