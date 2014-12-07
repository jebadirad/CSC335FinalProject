package model;

import java.util.ArrayList;

import view.GUI;

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
	
	public void makeMove() {
		
		GUI.gameboard.move(units.get(0), "L");
		System.out.println("AI got here");
		
//		for (int i = 0; i < units.size(); i++) {
//			GUI.gameboard.move(units.get(i), "L");
//			
//		}
		
		
	}
	
	
	
}
