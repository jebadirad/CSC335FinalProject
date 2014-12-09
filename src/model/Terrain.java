package model;

import java.io.Serializable;

import unit.Unit;

// This enum contains the types of terrain that will be in a cell object:

public enum Terrain implements Serializable{
	// Possible terrain types:
	Boulder(9999),
	Nothing(0),
	QuickSand(4),
	Lava(2),
	Forest(0),
	Desert(0),
	Ice(0),
	Flag(0);
	
	
	private int modifier;
	
	private Terrain(int mod){
		this.modifier = mod;
	}
	
public enum Terrain implements Serializable
{
  // Possible terrain types:
  Boulder, Nothing, QuickSand, Lava, Forest, Desert, Ice, Flag;

	public int getmodifier(){
		return modifier;
	}
}
