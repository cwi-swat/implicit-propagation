package funcons.entities.experiments;

import java.util.function.Function;

interface Passable{
	
}

interface Expressible{
	
}
/*
  public class Abs<P extends Passable, E extends Expressible> implements Function<P, E>{
 
	private Object o;
	public Abs(Object o){
		this.o = o;
	}
	@Override
	public E apply(P t) {
		System.out.println("Trying to apply Abs");
		return null;
	}
	
}
*/
public class Abs<P, E> implements Function<P, E>{
	 
	private Object o;
	public Abs(Object o){
		this.o = o;
	}
	@Override
	public E apply(P t) {
		System.out.println("Trying to apply Abs");
		return null;
	}
	
	public Object getAbstracted(){
		return o;
	}
	
}
