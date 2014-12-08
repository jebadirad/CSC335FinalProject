package model;

import view.GUI;

public class AI {
	public static final int WAIT_TIME = 500;
	
	public AI() {
		
	}

	public void makeMove() {
		
				for(int i = 0; i < GUI.gameboard.getPlayer1Units().size(); i++){
				GUI.CurrentUnitSelected = GUI.gameboard.getPlayer1Units().get(i);	
				ActionCommand moveleft = new ActionCommand("L", GUI.CurrentUnitSelected);
				ActionCommand moveright = new ActionCommand("R", GUI.CurrentUnitSelected);
				AttackCommand attack = new AttackCommand(GUI.CurrentUnitSelected, GUI.EnemyUnitSelected);	
				GUI.gameboard.commandqueue.add(attack);
				if(i < 1){
						
						GUI.gameboard.commandqueue.add(moveleft);
					}
					else{
						GUI.gameboard.commandqueue.add(moveright);
					}
				
				}
				
				
				
				
		
	}

}
