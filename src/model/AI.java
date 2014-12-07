package model;


import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import unit.Unit;
import view.GUI;

public class AI {
	
	
	public AI() {
	}


	public void makeMove() {
		GUI.CurrentUnitSelected = GUI.gameboard.getPlayer1Units().get(0);
		Cell unit = GUI.gameboard.getPlayer1Units().get(0);
		System.out.println(unit.getUnit().toString());
		System.out.println("AI got here");
		GUI.moveLeft.doClick();
		
		GUI.CurrentUnitSelected= null; 
		//GUI.gameboard.move(unit, "L");
//		for (int i = 0; i < units.size(); i++) {
//			GUI.gameboard.move(units.get(i), "L");
//			
//		}
		
	}
	
	
	
}
