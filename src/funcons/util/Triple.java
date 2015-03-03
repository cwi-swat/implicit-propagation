package funcons.util;

public class Triple<T,U,V> {
	T _1;
	U _2;
	V _3;
	
	public Triple(T _1, U _2, V _3){
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
	}
	
	public T _1() { return _1; };
	public U _2() { return _2; };
	public V _3() { return _3; };
	
	public Triple<T,U,V> replace1(T t){
		return new Triple<T, U, V>(t, this._2, this._3);
	}
	
	public Triple<T,U,V> replace2(U u){
		return new Triple<T, U, V>(this._1, u, this._3);
	}
	
	public Triple<T,U,V> replace3(V v){
		return new Triple<T, U, V>(this._1, this._2, v);
	}
	
	@Override
	public String toString(){
		return "("+ _1.toString() +", "+_2.toString()+", "+_3.toString()+")";
	}
	
}
