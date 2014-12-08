package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

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
			Timer t = new Timer(WAIT_TIME, new MoveListener());

			while (GUI.CurrentUnitSelected.getUnit().getMovesLeft() > 0) {
				System.out.println("Before");
				t.start();
				System.out.println("After");
				try {
					t.wait(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			t.stop();
			unitsWithMovesLeft--;

		}

		GUI.CurrentUnitSelected = null;

	}

	public class MoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Got here");
			if (GUI.gameboard.canMove(GUI.CurrentUnitSelected, "L") == false) {
				GUI.moveLeft.doClick();
			} else {
				GUI.moveRight.doClick();
			}
		}
	}

}
