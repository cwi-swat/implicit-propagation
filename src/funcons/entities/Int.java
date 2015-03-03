package funcons.entities;

public class Int implements Value{
	private final int val;
	
	public Int(int val){
		this.val = val;
	}
	
	public int getValue(){
		return val;
	}
	
	@Override
	public String toString(){
		return Integer.toString(val);
	}
	
	@Override
	public boolean equals(Object other){
		if (other instanceof Int){
			return ((Int)other).val == this.val;
		}
		return false;
	}
}