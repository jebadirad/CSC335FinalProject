package model;
// Cell class creates the cell object needed for the gameBoard:

public class Cell {
	
	// Cell will contain a terrain type:
	private Terrain terrain;
	// This boolean lets us know if cell has a unit in it
	private boolean hasUnit;
	// A unit instance, might be null
	private Unit unit;
	
	// Constructor takes the type of terrain desired as a parameter
	public Cell(Terrain terrain) {
	    this.setTerrain(terrain);
	    setHasUnit(false);
	    setUnit(null);
	}
	
	// Getters/Setters:
	public Terrain getTerrain() {
		return terrain;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	public boolean hasUnit() {
		return hasUnit;
	}
	public void setHasUnit(boolean hasUnit) {
		this.hasUnit = hasUnit;
	}
	public Unit getUnit() {
		return unit;
	}
	// No need for the removeUnit method, just setUnit to null
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	
	
	
	

	
	
}
