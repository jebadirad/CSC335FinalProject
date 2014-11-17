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
	
	// Creates Map1
	public void createMap1() {
		// board is 20 by 20 for now:
		board = new Cell[20][20];
		// initialize all cells to contain no units, and create desert map
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				board[i][j].setUnit(null);
				board[i][j].setTerrain(Terrain.Desert);
				// Give each cell a location
				board[i][j].setLocation(new Point(i,j));
			}
		}
		// Generate actual terrain:
		for (int i = 0; i < 20; i++) {
			// Sets the third row for of this board to all lavas
			board[i][2].setTerrain(Terrain.Lava);
			// Sets the seventh row for of this board to all Boulders
			board[i][6].setTerrain(Terrain.Boulder);
		}
		
		// Generate Units:
		generateUnits();
		
	}

	// Creates Map2
	public void createMap2() {
		// board is 100 by 100 for now:
		board = new Cell[20][20];
		// initialize all cells to contain no units, and create forest map
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				board[i][j].setUnit(null);
				board[i][j].setTerrain(Terrain.Forest);
				// Give each cell a location
				board[i][j].setLocation(new Point(i,j));
			}
		}
		// Generate Units:
		generateUnits();

	}

	// Responsible for adding a Unit to cells who we desire to have a unit
	public void generateUnits() {
		// Creating one single unit for now:
		UnitFactory factory = new UnitFactory();
		// Set Location for the unit:
		Point p = new Point(0,0);
		// Last parameter is UserName obtained from the GUI
		Unit aUnit = factory.makeUnit("CloneTrooper", "");

	}

	// Responsible for returning a text version of the current GameBoard:
	public String toString() {
		String str = "";
		for (int i = 0; i < 20; i++) {
			if (i != 0) 
				str += "/n";
			for (int j = 0; j < 20; j++) {
				str += board[i][j].getTerrain();
			}
		}
		return str;
	}
	
	// This method moves the unit in the cell given, it is GUARENTEED that the cell contains a unit!
	public boolean move(Cell cellWithUnit, String direction) {
		
		// theUnit will be my reference to this Unit.
		Unit theUnit = cellWithUnit.getUnit();
		if (theUnit.getMovesLeft() <= 0)
			return false;
		// theUnit can move:
		else {
			// Unit moves north:
			if (direction.equals("N")) {
				// Can't move farther north:
				if (cellWithUnit.getLocation().y==20)
					return false;
				// Can move north:
				else {
					// Add theUnit to the cell above it:
					board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y-1].setUnit(theUnit);
					// Remove unit from the current cell:
					board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].removeUnit();
					// Reduce the Units movement by 1:
					theUnit.setMovesLeft(theUnit.getMovesLeft()-1);
					// Deal with the Terrain theUnit is now standing in:
					
					
				}
			}
			else if (direction.equals("S")) {
				
			}

				
				

		}
		
		
		return false;
	}
	
	public void turnOver() {
		// update movesLeft
	}
	
	
	
	
	
	// Returns a the unit in this cell, or null if there is no unit in this cell:
	public Unit getUnit(Cell cell) {
		if (cell.hasUnit())
			return cell.getUnit();
		else
			return null;
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
