package funcons.entities.experiments;

import funcons.entities.I;

public class IdPattern implements Pattern{
	private I id;
	
	public IdPattern(I id){
		this.id = id;
	}

	public I getId() {
		return id;
	}
	
	
}