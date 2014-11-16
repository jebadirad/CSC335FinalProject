package model;
// This enum contains the types of terrain that will be in a cell object:

public enum Terrain {
	// Possible terrain types:
	// Boulder type has zero movement, implying that you can't pass through it
	Boulder(0),
	// Nothing type for empty terrain, assuming 10 is fast movement (scale from 0 to 10)
	Nothing(10);
	// What kind of terrain do we want yo?
	
	// Instance variables, should both be doubles
	private double movement;
	private double movementModifier;
	
	// Constructor takes the movement we want to give it as a parameter:
	private Terrain(int movement) {
		this.movement = movement;
	}
	
	// Getters/Setters:
	public double getMovement() {
		return movement;
	}
	// setMovement uses the movementModifier instance variable which we can set in game
	public void setMovement() {
		movement = movement*movementModifier;
	}
	public double getMovementModifier() {
		return movementModifier;
	}
	public void setMovementModifier(double movementModifier) {
		this.movementModifier = movementModifier;
	}
	
	
}
