package funcons.entities.experiments;

import java.util.Arrays;

public class CompoundMatch<E> implements SimpleMatch<E>{

	private SimpleMatch<E> match1;
	private SimpleMatch<E> match2;
	

	private CompoundMatch(SimpleMatch<E> match1, SimpleMatch<E> match2){
		this.match1 = match1;
		this.match2 = match2;
	}

	public static <E> CompoundMatch<E> create(SimpleMatch<E> match1, SimpleMatch<E> match2){
		return new CompoundMatch<E>(match1, match2);
	}

	
	public static <E> SimpleMatch<E> create(SimpleMatch<E> ...es) {
		return Arrays.asList(es).stream().reduce(CompoundMatch::create).get();
	}

	public SimpleMatch<E> getMatch1() {
		return match1;
	}

	public SimpleMatch<E> getMatch2() {
		return match2;
	}
	
	
	
}