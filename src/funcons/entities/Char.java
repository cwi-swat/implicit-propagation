package funcons.entities;

public class Char implements Value {
	
	private char c;
	
	public Char(char c) {
		this.c  = c;
	}

	public char getValue(){
		return c;
	}
	
	@Override
	public String toString(){
		return Character.toString(c);
	}
	
	
	@Override
	public boolean equals(Object other){
		if (other instanceof Char){
			return ((Char)other).c == this.c;
		}
		return false;
	}
}
