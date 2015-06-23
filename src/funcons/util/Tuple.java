package funcons.util;

public class Tuple<T,U> {
	T _1;
	U _2;
	
	public Tuple(T _1, U _2){
		this._1 = _1;
		this._2 = _2;
	}
	
	public T _1() { return _1; };
	public U _2() { return _2; };
	
	public Tuple<T,U> replace1(T t){
		return new Tuple<T, U>(t, this._2);
	}
	
	public Tuple<T,U> replace2(U u){
		return new Tuple<T, U>(this._1, u);
	}
	
	@Override
	public String toString(){
		return "("+ _1.toString() +", "+_2.toString()+")";
	}
	
}
