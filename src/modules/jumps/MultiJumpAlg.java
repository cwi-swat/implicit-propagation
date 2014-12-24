package modules.jumps;


public interface MultiJumpAlg<E> {

	@SuppressWarnings("unchecked")
	E labeled(Pair<String, E> ...pairs);
	E jump(String label);
}
