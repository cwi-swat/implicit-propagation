package modules.values;

public class Cell implements Value {
	private final Integer id;
	public Cell(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cell)) {
			return false;
		}
		return ((Cell)obj).id.equals(id);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
