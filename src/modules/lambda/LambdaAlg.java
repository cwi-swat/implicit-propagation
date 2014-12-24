package modules.lambda;

import modules.binding.Order;

public interface LambdaAlg<E> {
	@Order(1)
	E lambda(String x, @Order(2) E body);
	E apply(E e1, E e2);
}
