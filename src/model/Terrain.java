package model;

import java.io.Serializable;

import unit.Unit;
// This enum contains the types of terrain that will be in a cell object:

public enum Terrain implements Serializable{
	// Possible terrain types:
	Boulder,
	Nothing,
	QuickSand,
	Lava,
	Forest,
	Desert,
	Ice;
	
}
