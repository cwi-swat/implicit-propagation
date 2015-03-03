package funcons.entities.experiments;

public class SimpleSimpleMatch<E> implements SimpleMatch<E>{
	private Pattern p;
	private E e;
	
	public SimpleSimpleMatch(Pattern p, E e){
		this.p = p;
		this.e = e;
	}

	public Pattern getP() {
		return p;
	}

	public E getE() {
		return e;
	}
	
	
}