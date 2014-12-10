package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
		distance(startx, starty,x,y);

		for (int i = 0; i < leeboard.length; i++) {
			for (int j = 0; j < leeboard.length; j++) {

				System.out.print(leeboard[i][j].getValue() + "   ");
			}
			System.out.print("\n");
		}

		return null;
	}

	public void distance(int x, int y, int enemyx, int enemyy) {
		Queue<Point> q = new LinkedList<Point>();
		Point p = new Point();
		p.x = x;
		p.y = y;
		if (q.isEmpty()) {
			q.add(p);
		}
		while (!q.isEmpty()) {
			Point a = q.poll();
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (a.x + i < 0 || a.x + i > 19 || a.y + j < 0
							|| a.y + j > 19) {

					} else if (i == -1 && j == -1 || i == 1 && j == 1
							|| i == -1 && j == 1 || i == 1 && j == -1) {

					} else if (i == 0 && j == 0) {

					} else if (leeboard[a.x+i][a.y+j].getmarked()) {
					} else {
						leeboard[a.x + i][a.y + j].setvalue(leeboard[a.x][a.y]
								.getValue() + 1);
						leeboard[a.x + i][a.y + j].setmarked();
						q.add(new Point(a.x + i, a.y + j));
					}
					
				}
			}

		}

		
		System.out.println(leeboard[x][y].getValue());
		
		return;
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
