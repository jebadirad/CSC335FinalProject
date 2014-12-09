package model;

import java.util.ArrayList;

import view.GUI;

public class AI {
	public static final int WAIT_TIME = 500;

	public AI() {
		new Thread(new Runner()).start();

	}

	public void makeMove() {
		ArrayList<Cell> playerunits = new ArrayList<Cell>();
		playerunits = GUI.gameboard.getPlayer2Units();
		for (int i = 0; i < GUI.gameboard.getPlayer1Units().size(); i++) {

			Cell cell = GUI.gameboard.getPlayer1Units().get(i);
			Cell unittoAttack = findNearestUnit(cell, playerunits);
			if (i != 0) {
				ChangeUnitCommand command = new ChangeUnitCommand(cell);
				GUI.gameboard.commandqueue.add(command);
			}
			for (int j = 0; j < cell.getUnit().getMoveRange(); j++) {

				ActionCommand moveleft = new ActionCommand("N", cell);
				ActionCommand moveright = new ActionCommand("S", cell);

				GUI.gameboard.commandqueue.add(moveright);
				System.out.println(GUI.gameboard.commandqueue.size());

			}

		}

	}
	
	
	private Cell findNearestUnit(Cell cell, ArrayList<Cell> playerunits){
		//duplicate gameboard
		//set int array
		//create recursive method
		//base: check if we are at final point
		//base: check if we are at edge
		//base: check if bolder
		Cell[][] dupeboard = new Cell[20][20];
		for(int i =0; i < 20; i++){
			for(int j = 0; j < 20; j ++){
				dupeboard[i][j] = GUI.gameboard.getCell(i, j);
			}
		}
		int[][] intboard = new int[20][20];
		for(int i = 0; i < 20; i ++){
			for(int j = 0; j < 20; j++){
				if(GUI.gameboard.getCell(i,j).getTerrain() == Terrain.Lava){
					intboard[i][j] = Terrain.Lava.getmodifier();
				}
				else if(GUI.gameboard.getCell(i, j).getTerrain() == Terrain.Boulder){
					intboard[i][j] = Terrain.Boulder.getmodifier();
				}
				else if (GUI.gameboard.getCell(i,j).getTerrain() == Terrain.QuickSand){
					intboard[i][j] = Terrain.QuickSand.getmodifier();
				}
				else{
					intboard[i][j] = 0;
				}
			}
		}
		
		
		
		return null;
	}
	private class Runner implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true) {

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
