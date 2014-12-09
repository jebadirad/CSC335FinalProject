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

	private Cell findNearestUnit(Cell cell, ArrayList<Cell> playerunits) {
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
		int[][] intboard = new int[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (GUI.gameboard.getCell(i, j).getTerrain() == Terrain.Lava) {
					intboard[i][j] = Terrain.Lava.getmodifier();
				} else if (GUI.gameboard.getCell(i, j).getTerrain() == Terrain.Boulder) {
					intboard[i][j] = Terrain.Boulder.getmodifier();
				} else if (GUI.gameboard.getCell(i, j).getTerrain() == Terrain.QuickSand) {
					intboard[i][j] = Terrain.QuickSand.getmodifier();
				} else {
					intboard[i][j] = 0;
				}
			}
		}
		intboard = NearestUnit(0, startx, starty, x, y, intboard);
		for (int i = 0; i < intboard.length; i++) {
			for (int j = 0; j < intboard.length; j++) {

				System.out.print(" " + intboard[i][j] + " ");
			}
			System.out.print("\n");
		}

		return null;
	}

	private int[][] NearestUnit(int p, int startx, int starty, int x, int y,
			int[][] intboard) {
		p++;
		for (int a = -1; a < 2; a++) {
			for (int b = -1; b < 2; b++) {

				if (startx + a < 0) {
					
				}
				else if (startx + a > 19) {
					
				}
				else if (starty + b < 0) {
					
				}
				else if (starty + b > 19) {
					
				}
				else if (a == 0 && b == 0) {
					// do nothing
				} else {
					if (startx == x && starty == y) {

					} else {

						int i = startx + a;
						int j = starty + b;
						if (intboard[startx][starty] == 0 || intboard[startx][starty] == 2 || intboard[startx][starty] == 4) {
							intboard[startx][starty] = intboard[startx][starty] + p;
						} else {
							
						}

						NearestUnit(p, startx + a, starty + b, x, y, intboard);
					}
				}

			}
		}

		return intboard;
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
