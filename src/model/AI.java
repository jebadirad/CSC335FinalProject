package model;

import java.util.ArrayList;

import view.GUI;

public class AI {
	public static final int WAIT_TIME = 500;

	public AI() {

	}

	public void makeMove() {
		
		int unitsWithMovesLeft = GUI.gameboard.getPlayer1Units().size();
		for (int i = 0; i < unitsWithMovesLeft; i++) {
			GUI.CurrentUnitSelected = GUI.gameboard.getPlayer1Units().get(i);
			
			
			while (GUI.CurrentUnitSelected.getUnit().getMovesLeft() > 0) {
				System.out.println("Doing an action");
//				ArrayList<Cell> attackableUnits = GUI.gameboard
//						.getUnitsInAttackRange(GUI.CurrentUnitSelected);
				ArrayList<Cell> attackableUnits = new ArrayList<Cell>();
				if (attackableUnits.isEmpty()) {
					// Find nearest enemy:
					int x = GUI.CurrentUnitSelected.getLocation().x;
					int y = GUI.CurrentUnitSelected.getLocation().y;
					int xDistance = 1000;
					int yDistance = 1000;
					int shortest = 1000;
					for (int j = 0; j < GUI.gameboard.getPlayer2Units().size(); j++) {
						
						xDistance = Math.abs(GUI.gameboard.getPlayer2Units().get(j).getLocation().x - x);
						yDistance = Math.abs(GUI.gameboard.getPlayer2Units().get(j).getLocation().y - y);	
						int num = Math.sqrt(xDistance^2 + yDistance^2);
						
						
					}
					
					
					
					// Move:
					if (GUI.gameboard.canMoveAIStyle(GUI.CurrentUnitSelected, "R") == true) {
						ActionCommand moveRight = new ActionCommand("R", GUI.CurrentUnitSelected);
						GUI.gameboard.commandqueue.add(moveRight);
						System.out.println(GUI.gameboard.commandqueue.size());
					} else {
						ActionCommand moveLeft = new ActionCommand("L", GUI.CurrentUnitSelected);
						GUI.gameboard.commandqueue.add(moveLeft);
						System.out.println(GUI.gameboard.commandqueue.size());

					}
				} else {
//					if (GUI.gameboard.CanAttack(attackableUnits.get(0))) {
//						GUI.gameboard.attack(GUI.CurrentUnitSelected,
//								attackableUnits.get(0));
//					}
//					else {
//						
//					}
					
				}

			}
			unitsWithMovesLeft--;
			
			
		}
		
		
		
		

	}
	


}
