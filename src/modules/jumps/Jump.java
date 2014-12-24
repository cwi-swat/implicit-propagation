package modules.jumps;

@SuppressWarnings("serial")
public class Jump extends RuntimeException {
	private final String label;
	
	public Jump(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

}
