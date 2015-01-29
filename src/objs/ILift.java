package objs;

public interface ILift<T, U> {
	U lift(T t);
	T lower(U u);
}
