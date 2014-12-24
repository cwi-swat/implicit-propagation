package modules.conditional;

public interface ConditionalAlg<E> {
	E ifThen(E cond, E body, E els);
}
