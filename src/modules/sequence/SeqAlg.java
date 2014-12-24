package modules.sequence;

import java.util.Arrays;

public interface SeqAlg<E> {
	E seq(E e1, E e2);
	
	@SuppressWarnings("unchecked")
	default E seq(E ...es) {
		return Arrays.asList(es).stream().reduce(this::seq).get();
	}
}
