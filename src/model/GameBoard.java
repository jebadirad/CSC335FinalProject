package model;

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
		else
			createMap2();
	}

	public void createMap1() {
		// board is 100 by 100 for now:
		board = new Cell[100][100];
		// initialize all cells to no unit and no terrain:
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				board[i][j].setUnit(null);
				board[i][j].setTerrain(Terrain.Nothing);
			}
		}

	}

	public void createMap2() {
		// board is 100 by 100 for now:
		board = new Cell[100][100];
		// initialize all cells to no unit and no terrain:
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				board[i][j].setUnit(null);
				board[i][j].setTerrain(Terrain.Nothing);
			}
		}

	}

	// Responsible for adding terrain to each cell, or null if no terrain
	public void generateTerrain() {
		// Need to talk more about terrain before coding this method:
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				// Do stuff to board
			}
		}
	}

	// Responsible for adding a Unit to each cell, or null if no Unit
	public void generateUnits() {
		// Need to talk more about Units before coding this method:

	}

	// Responsible for returning a text version of the current GameBoard:
	public String toString() {
		String str = "";
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
