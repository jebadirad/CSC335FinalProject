package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GUI;

public class AI {
	public static final int WAIT_TIME = 500;
	
	public AI() {
		
	}

	public void makeMove() {

		int unitsWithMovesLeft = GUI.gameboard.getPlayer1Units().size();
		for (int i = 0; i < unitsWithMovesLeft; i++) {
			GUI.CurrentUnitSelected = GUI.gameboard.getPlayer1Units().get(i);
			System.out.println(GUI.CurrentUnitSelected.getUnit().toString());
			
			
			while (GUI.gameboard.canMove(GUI.CurrentUnitSelected, "L")) {
				
				System.out.println("Before");
				GUI.moveLeft.doClick();
				
				System.out.println("After");
				
			}
			unitsWithMovesLeft--;

		}

		GUI.CurrentUnitSelected = null;

	}

	public class MoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Got here");
			if (GUI.gameboard.canMove(GUI.CurrentUnitSelected, "L") == true) {
				GUI.moveLeft.doClick();
			} else {
				GUI.moveRight.doClick();
			}
		}
	}

}
