package funcons.util;

public class Quadruple<T, U, V, W> {
	T _1;
	U _2;
	V _3;
	W _4;
	
	public Quadruple(T _1, U _2, V _3, W _4){
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
		this._4 = _4;
	}
	
	public T _1() { return _1; };
	public U _2() { return _2; };
	public V _3() { return _3; };
	public W _4() { return _4; };
	
	
	public Quadruple<T,U,V,W> replace1(T t){
		return new Quadruple<T, U, V, W>(t, this._2, this._3, this._4);
	}
	
	public Quadruple<T,U,V,W> replace2(U u){
		return new Quadruple<T, U, V, W>(this._1, u, this._3, this._4);
	}
	
	public Quadruple<T,U,V,W> replace3(V v){
		return new Quadruple<T, U, V, W>(this._1, this._2, v, this._4);
	}
	
	public Quadruple<T,U,V,W> replace4(W w){
		return new Quadruple<T, U, V, W>(this._1, this._2, this._3, w);
	}
	
	@Override
	public String toString(){
		return "("+ _1.toString() +", "+_2.toString()+", "+_3.toString()+", "+_4.toString()+")";
	}
}
