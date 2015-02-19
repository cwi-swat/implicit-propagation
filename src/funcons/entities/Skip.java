package funcons.entities;

public class Skip implements Value{
	static private Skip instance;
	private Skip(){
		
	}
	public static Skip getInstance(){
		if (instance == null)
			instance = new Skip();
		return instance;
	}
}