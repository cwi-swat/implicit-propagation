package modules.comparison;

public interface ComparisonAlg<E> {
	E lt(E l, E r);
	E gt(E l, E r);
	E leq(E l, E r);
	E geq(E l, E r);
	E eq(E l, E r);
	E neq(E l, E r);
}
