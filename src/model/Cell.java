package model;

import java.awt.Point;

import unit.*;

// Cell class creates the cell object needed for the gameBoard:

public class Cell {

	// Cell will contain a terrain type:
	private Terrain terrain;
	// This boolean lets us know if cell has a unit in it
	private boolean hasUnit;
	// A unit instance, might be null
	private Unit unit;
	// A cell has a point/location associated with it
	private Point location;

	// Constructor takes the type of terrain desired as a parameter
	// It also takes in its x and y Coordinates.
	public Cell(Terrain terrain, int xCoordinate, int yCoordinate) {
		this.setTerrain(terrain);
		this.setLocation(new Point(xCoordinate, yCoordinate));
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
	// Sets the Unit to null, and sets hasUnit to false:
	public void removeUnit() {
		setUnit(null);
		hasUnit = false;
	}
	
	public Unit getUnit() {
		return unit;
	}

	// No need for the removeUnit method, just setUnit to null
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

}
