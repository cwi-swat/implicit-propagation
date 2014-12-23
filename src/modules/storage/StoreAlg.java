package modules.storage;

public interface StoreAlg<E> {
	E create();
	E deref(E x);
	E update(E x, E v);
	E seq(E l, E r);
}
