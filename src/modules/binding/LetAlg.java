package modules.binding;

public interface LetAlg<E> {
	@Order(2) 
	E let(String x, @Order(1) E e, @Order(3) E body);
	E var(String x);
}
