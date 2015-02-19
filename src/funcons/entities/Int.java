package funcons.entities;

public class Int implements Value{
	private final int val;
	
	public Int(int val){
		this.val = val;
	}
	
	public int getValue(){
		return val;
	}
}