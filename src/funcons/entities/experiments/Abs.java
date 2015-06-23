package funcons.entities.experiments;


interface Passable{
	
}

interface Expressible{
	
}

public class Abs<F>{
	 
	private F o;
	public Abs(F o){
		this.o = o;
	}
	public F getAbstracted(){
		return o;
	}
	
}
