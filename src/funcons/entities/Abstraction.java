package funcons.entities;

@FunctionalInterface
public interface Abstraction<From, To> extends Value{
	To eval(From from);
}