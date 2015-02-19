package funcons.entities;

public class Bool implements Value{
	private final boolean val;
	
	public Bool(boolean val){
		this.val = val;
	}
	
	public boolean getValue(){
		return val;
	}

}