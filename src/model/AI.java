package model;

import java.util.ArrayList;

import view.GUI;

public class AI {
	public static final int WAIT_TIME = 500;
	leealgorithm[][] leeboard;
	int p = 0;

	public AI() {
		new Thread(new Runner()).start();

	}

	public void makeMove() {
		ArrayList<Cell> playerunits = new ArrayList<Cell>();
		playerunits = GUI.gameboard.getPlayer2Units();
		// for (int i = 0; i < GUI.gameboard.getPlayer1Units().size(); i++) {

		Cell cell = GUI.gameboard.getPlayer1Units().get(0);
		Cell unittoAttack = findNearestUnit(cell, playerunits);
		// if (i != 0) {
		ChangeUnitCommand command = new ChangeUnitCommand(cell);
		GUI.gameboard.commandqueue.add(command);
		// }
		// for (int j = 0; j < cell.getUnit().getMoveRange(); j++) {

		ActionCommand moveleft = new ActionCommand("N", cell);
		ActionCommand moveright = new ActionCommand("S", cell);

		GUI.gameboard.commandqueue.add(moveright);
		System.out.println(GUI.gameboard.commandqueue.size());

		// }

		// }

	}

	public Cell findNearestUnit(Cell cell, ArrayList<Cell> playerunits) {
		// duplicate gameboard -
		// set int array -
		// create recursive method
		// base: check if we are at final point
		// base: check if we are at edge
		// base: check if bolder

		Cell endcell = playerunits.get(0);
		int x = endcell.getLocation().x;
		int y = endcell.getLocation().y;
		int startx = cell.getLocation().x;
		int starty = cell.getLocation().y;
		Cell[][] dupeboard = new Cell[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				dupeboard[i][j] = GUI.gameboard.getCell(i, j);
			}
		}
		leeboard = new leealgorithm[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				leeboard[i][j] = new leealgorithm();
				if (GUI.gameboard.getCell(i, j).getTerrain() == Terrain.Lava) {
					leeboard[i][j].setvalue(Terrain.Lava.getmodifier());
				} else if (GUI.gameboard.getCell(i, j).getTerrain() == Terrain.Boulder) {
					leeboard[i][j].setvalue(Terrain.Boulder.getmodifier());
				} else if (GUI.gameboard.getCell(i, j).getTerrain() == Terrain.QuickSand) {
					leeboard[i][j].setvalue(Terrain.QuickSand.getmodifier());
				} else {
					leeboard[i][j].setvalue(0);
				}
			}
		}
		for (int i = 0; i < leeboard.length; i++) {
			for (int j = 0; j < leeboard.length; j++) {

				System.out.print(" " + leeboard[i][j].getValue() + " ");
			}
			System.out.print("\n");
		}
		System.out.println("");
		for (int i = 0; i < leeboard.length; i++) {
			for (int j = 0; j < leeboard.length; j++) {

				System.out.print(" " + leeboard[i][j].getmarked() + " ");
			}
			System.out.print("\n");
		}
		System.out.println("");
		leeboard[startx][starty].setmarked();
		leeboard[startx][starty].setvalue(9);
		nearestunithelper(startx, starty, x, y);
		for (int i = 0; i < leeboard.length; i++) {
			for (int j = 0; j < leeboard.length; j++) {

				System.out.print(leeboard[i][j].getValue() + "   ");
			}
			System.out.print("\n");
		}

		return null;
	}

	public void nearestunithelper(int startx, int starty, int x, int y) {
		NearestUnit(p + 1, startx, starty, x, y);

	}

	public void NearestUnit(int p, int startx, int starty, int x, int y) {
		/*
		 * for (int a = -1; a < 2; a++) { for (int b = -1; b < 2; b++) { if
		 * (startx == x && starty == y) { return; } else if (startx + a < 0 ) {
		 * return;
		 * 
		 * } else if (startx + a > 19) { return; } else if (starty + b < 0) {
		 * return; } else if (starty + b < 19) { return; }
		 * 
		 * else if (a == -1 && b == -1) { } else if (a == -1 && b == 1) { } else
		 * if (a == 1 && b == -1) { } else if (a == 1 && b == 1) { }
		 * 
		 * else { if (leeboard[startx + a][starty + b].getmarked() == true) { if
		 * (leeboard[startx + a][starty + b].getValue() < p) { leeboard[startx +
		 * a][starty + b].setvalue(p); } return; } else { leeboard[startx +
		 * a][starty + b].setvalue(p); leeboard[startx + a][starty +
		 * b].setmarked(); NearestUnit(p + 1, startx + a, starty + b, x, y); } }
		 * } }
		 * 
		 * return;
		 */
		p = 0;
		leeboard[startx][starty].setvalue(0);
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (startx + i < 0 || startx + i > 19) {

				} else if (starty + j < 0 || starty + j > 19) {

				} else if (startx - i < 0 || startx - i > 19) {

				} else if (starty - j < 0 || starty - j > 19) {

				} else {
					/*
					 * for(int a = -1; a < 2 ; a++){ for(int b = -1; b < 2;
					 * b++){ if(startx +a < 0 || startx + a > 19 || starty + b <
					 * 0 || starty + b >19){
					 * 
					 * } else if(a == -1 && b == -1){ leeboard[startx +
					 * a][starty + b].setvalue(p+1); } else if (a == -1 && b ==
					 * 1){ leeboard[startx + a][starty + b].setvalue(p+1); }
					 * else if (a == 1 && b == 1){ leeboard[startx + a][starty +
					 * b].setvalue(p+1); } else if (a == 1 && b == -1){
					 * leeboard[startx + a][starty + b].setvalue(p+1); } else{
					 * 
					 * } } }
					 */
					
					
				}

			}
			p++;
		}
		leeboard[startx][starty].setvalue(0);

	}

	private class leealgorithm {
		int value;
		boolean marked;

		public leealgorithm() {
			value = 0;
			marked = false;
		}

		public void setvalue(int value) {
			this.value = value;
		}

		public void setmarked() {
			this.marked = true;
		}

		public int getValue() {
			return value;
		}

		public boolean getmarked() {
			return marked;
		}

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
