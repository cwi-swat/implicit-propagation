package modules.lambda;

public interface LambdaAlg<E> {
	E lambda(String x, E body);
	E apply(E e1, E e2);
}
