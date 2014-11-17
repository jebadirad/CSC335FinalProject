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
				//need to create the cells before we add them to the board and try to access them.
				board[i][j] = new Cell(Terrain.Nothing, i,j);
				board[i][j].setUnit(null);
				// Give each cell a location
				board[i][j].setLocation(new Point(i, j));
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
				board[i][j].setLocation(new Point(i, j));
			}
		}
		// Generate Units:
		generateUnits();

	}

	// Responsible for adding a Unit to cells who we desire to have a unit
	public void generateUnits() {
		// Creating one single unit for now:
		UnitFactory factory = new UnitFactory();
		// Last parameter is UserName obtained from the GUI
		//added default username 
		Unit aUnit = factory.makeUnit("CloneTrooper", "fdsa");

	}

	// Responsible for returning a text version of the current GameBoard:
	public String toString() {
		String str = "";
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				str += "[ ";
				if(board[i][j].getTerrain().equals(Terrain.Desert)){
					str += "D";
				}
				else if (board[i][j].getTerrain().equals(Terrain.Boulder)){
					str += "B";
				}
				else if (board[i][j].getTerrain().equals(Terrain.Lava)){
					str += "L";
					
				}
				else if (board[i][j].getTerrain().equals(Terrain.Forest)){
					str += "F";
				}
				else { 
					str += " ";
				}
				str +=" ] ";
			}
			str+= "\n";
		}
		return str;
	}

	// This method moves the unit in the cell given, it is GUARENTEED that the
	// cell contains a unit!
	public boolean move(Cell cellWithUnit, String direction) {

		// DEAL WITH BOULDER:
		
		// theUnit will be my reference to this Unit.
		Unit theUnit = cellWithUnit.getUnit();
		if (theUnit.getMovesLeft() <= 0)
			return false;
		// theUnit can move:
		else {
			// Unit moves north:
			if (direction.equals("N")) {
				// Can't move farther north:
				if (cellWithUnit.getLocation().x == 0)
					return false;
				// Can move north:
				else {
					// Add theUnit to the cell above it:
					board[cellWithUnit.getLocation().x - 1][cellWithUnit
							.getLocation().y].setUnit(theUnit);
					// Remove unit from the current cell:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// Reduce the Units movement by 1:
					theUnit.setMovesLeft(theUnit.getMovesLeft() - 1);
					// Deal with the Terrain theUnit is now standing in:

					// My reference to the terrain the unit is now standing in
					Terrain terrain = board[cellWithUnit.getLocation().x - 1][cellWithUnit
							.getLocation().y].getTerrain();
					// Finish this:

				}
			} else if (direction.equals("S")) {
				// Can't move farther south:
				if (cellWithUnit.getLocation().x == 20)
					return false;
				// Can move north:
				else {
					// Add theUnit to the cell above it:
					board[cellWithUnit.getLocation().x + 1][cellWithUnit
							.getLocation().y].setUnit(theUnit);
					// Remove unit from the current cell:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// Reduce the Units movement by 1:
					theUnit.setMovesLeft(theUnit.getMovesLeft() - 1);
					// Deal with the Terrain theUnit is now standing in:
					
					// My reference to the terrain the unit is now standing in
					Terrain terrain = board[cellWithUnit.getLocation().x - 1][cellWithUnit
							.getLocation().y].getTerrain();
					// Finish this:
				}
			}
			// FINISH WEST AND EAST MOVE:

		}

		return false;
	}
	
	// When the turn is over, update movesLeft:
	// Assuming GUI passes an array of the cells with units in it to be updated
	public void turnOver(Cell[][] cellsContaingUnitsToBeUpdated) {
		for (int i = 0; i < cellsContaingUnitsToBeUpdated.length; i++) {
			for (int j = 0; j < cellsContaingUnitsToBeUpdated.length; j++) {
				cellsContaingUnitsToBeUpdated[i][j].getUnit().setMovesLeft(cellsContaingUnitsToBeUpdated[i][j].getUnit().getMoveRange());
			}
		}
	}

	// Returns a the unit in this cell, or null if there is no unit in this
	// cell:
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
	//simple getter that fetches the cell that i need so we can play with the data. 
	public Cell getCell(int x, int y){
		return board[x][y];
	}

}
