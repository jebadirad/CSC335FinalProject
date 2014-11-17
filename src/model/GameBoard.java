package model;

import java.awt.Point;

import unit.Unit;
import unit.UnitFactory;

// The GameBoard class creates the board for the game:

public class GameBoard {
	// board will be the most important thing in this class
	private Cell[][] board;
	// Not sure how to do end of game conditions yet, for now its this boolean
	private boolean playing;

	// Takes "Map 1" or "Map 2" as a parameter
	public GameBoard(String mapName) {
		if (mapName.equals("Map 1"))
			createMap1();
		else if (mapName.equals("Map 2"))
			createMap2();
		
	}

	public void createMap1() {
		// board is 100 by 100 for now:
		board = new Cell[100][100];
		// initialize all cells to contain no units, and create desert map
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				board[i][j].setUnit(null);
				board[i][j].setTerrain(Terrain.Desert);
			}
		}
		// Generate actual terrain:
		for (int i = 0; i < 100; i++) {
			// Sets the third for of this board to all lavas
			board[i][2].setTerrain(Terrain.Lava);
			// Sets the seventh for of this board to all Boulders
			board[i][6].setTerrain(Terrain.Boulder);
		}
		
		// Generate Units:
		generateUnits();
		
	}

	public void createMap2() {
		// board is 100 by 100 for now:
		board = new Cell[100][100];
		// initialize all cells to contain no units, and create forest map
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				board[i][j].setUnit(null);
				board[i][j].setTerrain(Terrain.Forest);
			}
		}

	}

	// Responsible for adding a Unit to cells who we desire to have a unit
	public void generateUnits() {
		// Creating one single unit for now:
		UnitFactory factory = new UnitFactory();
		// Set Location for the unit:
		Point p = new Point(0,0);
		// Last parameter is UserName obtained from the GUI
		Unit aUnit = factory.makeUnit("CloneTrooper", p , "");

	}

	// Responsible for returning a text version of the current GameBoard:
	public String toString() {
		String str = "";
		for (int i = 0; i < 100; i++) {
			if (i != 0) 
				str += "/n";
			for (int j = 0; j < 100; j++) {
				str += board[i][j].getTerrain();
			}
		}
		return str;
	}

	// Getters/Setters:
	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

}
