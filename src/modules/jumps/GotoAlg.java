package modules.jumps;

public interface GotoAlg<E> {
	E label(String label, E body);
	E jump(String label);
}
