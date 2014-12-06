package model;

import java.util.ArrayList;

public class AI {
	
	private String name;
	private ArrayList<Cell> units;
	
	
	public AI(String name) {
		units = new ArrayList<Cell>();
		this.name = name;
	}

	public ArrayList<Cell> getUnits() {
		return units;
	}


	public void setUnits(ArrayList<Cell> units) {
		this.units = units;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
