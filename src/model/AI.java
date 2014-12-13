package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

		Cell cell = GUI.gameboard.getPlayer1Units().get(0);

		ChangeUnitCommand command = new ChangeUnitCommand(cell);
		GUI.gameboard.commandqueue.add(command);
		System.out.println("change unit command");
		Stack<Point> path = findNearestUnit(cell, playerunits);
		int amountofmoves = cell.getUnit().getMoveRange();
		int attackrange = cell.getUnit().getAttackRange();
		for (int i = 0; i < path.size() || i < amountofmoves; i++) {
			if (!path.isEmpty()) {
				Point p = path.pop();
				Point prev = p;
				Point cellpoint;
				if(!path.isEmpty() && i != 0){
					cellpoint = path.peek();
				}
				else{
					cellpoint = cell.getLocation();
				}
				System.out.println("this is the point i am going to go next" + p);
				System.out.println("This is the next point" + cellpoint);
				int x = p.x;
				
				int y = p.y;
				int cellx = cell.getLocation().x;
				int celly = cell.getLocation().y;
				if (x == cellx && y == celly) {
				} 
				else if (path.size() == attackrange){
					Point attackpoint = null;
					while(!path.isEmpty()){
						attackpoint = path.pop();
					}
					
					Cell attackcell = GUI.gameboard.getCell(prev.y, prev.x);
					Cell defensecell = GUI.gameboard.getCell(attackpoint.x,  attackpoint.y);
					AttackCommand attack = new AttackCommand(attackcell, defensecell);
					System.out.println(attackpoint+ "attackpoint");
					System.out.println("x" + prev.x + "y"+ prev.y);
					System.out.println("x" + attackpoint.y + "y" + attackpoint.y);
					System.out.println(attackcell.getLocation() + " attack cell" + attackcell.getUnit().getCanAttack());
					System.out.println(defensecell.getLocation() + "defense cell");
					GUI.gameboard.commandqueue.add(attack);
					break;
				}
				else {
					System.out.println(p);
					String direction = findDirection(p, cellpoint);
					System.out.println(direction);
					ActionCommand move = new ActionCommand(direction, cell);
					GUI.gameboard.commandqueue.add(move);
				}
				prev = p;
				
			}

		}
		System.out.println(GUI.gameboard.commandqueue.size());

	}

	public String findDirection(Point start, Point end) {
		int startx = start.x;
		int endx = end.x;
		int starty = start.y;
		int endy = end.y;

		if (startx < endx) {
			return "S";
		} else if (startx > endx ) {
			return "N";
		} else if (starty < endy) {
			return "R";
		} else {

			return "L";
		}
	}

	public Stack<Point> findNearestUnit(Cell cell, ArrayList<Cell> playerunits) {
		// duplicate gameboard -
		// set int array -
		// create recursive method
		// base: check if we are at final point
		// base: check if we are at edge
		// base: check if bolder
		ArrayList<Stack<Point>> distances = new ArrayList<Stack<Point>>();
		for (int k = 0; k < playerunits.size(); k++) {
			Cell endcell = playerunits.get(k);
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
						leeboard[i][j]
								.setvalue(Terrain.QuickSand.getmodifier());
					} else {
						leeboard[i][j].setvalue(0);
					}
				}
			}

			leeboard[startx][starty].setmarked();
			Stack<Point> temp = distance(startx, starty, x, y);
			distances.add(temp);
		}
		int smallest = 0;
		for (int i = 0; i < distances.size(); i++) {
			smallest = Math.min(distances.get(i).size(), smallest);
		}
		int index = 0;
		for (int i = 0; i < distances.size(); i++) {
			if (distances.get(i).size() == smallest) {
				index = i;
			}
		}

		return distances.get(index);
	}

	public Stack<Point> distance(int x, int y, int enemyx, int enemyy) {
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

					} else if (leeboard[a.x + i][a.y + j].getmarked()) {
					} else {
						leeboard[a.x + i][a.y + j].setvalue(leeboard[a.x][a.y]
								.getValue() + 1);
						leeboard[a.x + i][a.y + j].setmarked();
						q.add(new Point(a.x + i, a.y + j));
					}

				}
			}

		}
		Stack<Point> stack = new Stack<Point>();
		Point min = new Point();
		min.x = enemyx;
		min.y = enemyy;
		stack.add(min);
		int count = leeboard[enemyx][enemyy].getValue();
		while (count > 1) {
			Point next = stack.lastElement();
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (next.x + i < 0 || next.x + i > 19 || next.y + j < 0
							|| next.y + j > 19) {

					} else if (i == -1 && j == -1 || i == 1 && j == 1
							|| i == -1 && j == 1 || i == 1 && j == -1) {

					} else if (i == 0 && j == 0) {

					} else {
						if (leeboard[next.x + i][next.y + j].getValue() == count - 1) {
							Point add = new Point();
							add.x = next.x + i;
							add.y = next.y + j;
							stack.add(add);
							count--;
							i = 2;
							j = 2;
							break;

						}
					}
				}
			}

		}
		for(int i = 0; i < stack.size(); i ++){
			System.out.println("------------");
			System.out.println(stack.get(i));
		}

		return stack;
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
