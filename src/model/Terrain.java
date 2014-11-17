package model;

import unit.Unit;
// This enum contains the types of terrain that will be in a cell object:

public enum Terrain {
	// Possible terrain types:
	// Boulder type has zero movement, implying that you can't pass through it
	Boulder(0),
	// Nothing type for empty terrain, assuming 10 is fast movement (scale from 0 to 10)
	Nothing(10),
	QuickSand(5),
	Lava(5),
	Forest(5),
	Desert(5),
	Ice(5);

	// What kind of terrain do we want yo?
	
	// Instance variables, should both be doubles
	private int moveRange;
	
	// Constructor takes the movement we want to give it as a parameter:
	private Terrain(int moveRange) {
		this.moveRange = moveRange;
	}
	
	// Getters/Setters:
	public int getMoveRange() {
		return moveRange;
	}

	// setMoveRange changes the MoveRange of the unitStandingInThisTerrain Unit,
	public void setMoveRange(Unit unitStandingInThisTerrain) {
		unitStandingInThisTerrain.setMoveRange(unitStandingInThisTerrain.getMoveRange()-moveRange);
	}
	
	
	
}
