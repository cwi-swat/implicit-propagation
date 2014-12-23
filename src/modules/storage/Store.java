package modules.storage;

import java.util.HashMap;
import java.util.Map;

import modules.values.Value;


public class Store {
	private int cells;
	private final Map<Cell, Value> map;

	public Store() {
		this.map = new HashMap<>();
	}
	

	Cell newCell() {
		return new Cell(cells++);
	}
	
	Value update(Cell cell, Value value) {
		map.put(cell, value);
		return value;
	}
	
	Value deref(Cell cell) {
		return map.get(cell);
	}
	
	@Override
	public String toString() {
		return "<" + map + ", " + cells + ">";
	}
}
