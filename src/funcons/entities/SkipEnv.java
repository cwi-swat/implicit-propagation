package funcons.entities;

public class SkipEnv<T> extends Env<T> {
	public static <T> SkipEnv<T> getInstance(){
		return new SkipEnv<T>();
	}
}
