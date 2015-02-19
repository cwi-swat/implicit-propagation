package funcons.entities;

import java.util.HashMap;
import java.util.Map;

public class Store {
	private int cells;
	private final Map<Cell, Value> map;

	public Store() {
		this.map = new HashMap<>();
	}
	
	public Cell newCell() {
		return new Cell(cells++);
	}
	
	public Value update(Cell cell, Value value) {
		map.put(cell, value);
		return value;
	}
	
	public Value deref(Cell cell) {
		return map.get(cell);
	}
	
	@Override
	public String toString() {
		return "<" + map + ", " + cells + ">";
	}
}
