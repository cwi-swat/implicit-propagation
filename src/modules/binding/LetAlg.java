package modules.binding;

public interface LetAlg<E> {
	E let(String x, E e, E body);
	E var(String x);
}
