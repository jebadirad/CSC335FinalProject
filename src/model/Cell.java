package model;

import java.awt.Point;
import java.io.Serializable;

import unit.*;

/**
 * Cell class creates the cell object needed for the gameBoard
 * @author David Aaron
 *
 */
public class Cell implements Serializable{
	private static final long serialVersionUID = 6683828659087047035L;
	// Cell will contain a terrain type:
	private Terrain terrain;
	// This boolean lets us know if cell has a unit in it
	private boolean hasUnit;
	// A unit instance, might be null
	private Unit unit;
	// A cell has a point/location associated with it
	private Point location;
	// Counter for flag:
	private int flagTurns;
	
	/**
	 * Constructor takes the type of terrain desired as a parameter
	 * It also takes in its x and y Coordinates.
	 * @param terrain The Terrain to be assigned to this Cell
	 * @param xCoordinate The xCoordinate to be assigned to this Cell
	 * @param yCoordinate The 7Coordinate to be assigned to this Cell
	 */
	public Cell(Terrain terrain, int xCoordinate, int yCoordinate) {
		this.setTerrain(terrain);
		this.setLocation(new Point(xCoordinate, yCoordinate));
		setHasUnit(false);
		setUnit(null);
	}

	// Getters/Setters:
	/**
	 * Getter for Terrain in this cell
	 * @return Returns the Terrain type in this cell
	 */
	public Terrain getTerrain() {
		return terrain;
	}
	
	/**
	 * Setter for terrain
	 * @param terrain the Terrain to be set
	 */
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	/**
	 * returns the boolean has unit
	 * @return returns the boolean has unit
	 */
	public boolean hasUnit() {
		return hasUnit;
	}
	
	/**
	 * Sets has unit
	 * @param hasUnit the boolean value to be set to
	 */
	public void setHasUnit(boolean hasUnit) {
		this.hasUnit = hasUnit;
	}
	/**
	 * Sets the Unit to null, and sets hasUnit to false:
	 */
	public void removeUnit() {
		setUnit(null);
		hasUnit = false;
	}
	
	/**
	 * Getter for Unit
	 * @return the unit in the cell, could be null
	 */
	public Unit getUnit() {
		return unit;
	}

	// No need for the removeUnit method, just setUnit to null
	/**
	 * Sets the unit in the cell to the Unit parameter given:
	 * @param unit The unit to be set 
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	/**
	 * Getter for the location of this cell
	 * @return The locaiton as a point
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * Sets the lcoation to the point given
	 * @param location The new location to be set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}


}

