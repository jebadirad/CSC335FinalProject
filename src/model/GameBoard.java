package model;

import java.awt.Point;
import java.util.ArrayList;
import unit.Unit;
import unit.UnitFactory;

// The GameBoard class creates the board for the game:

public class GameBoard {
	// board will be the most important thing in this class
	private Cell[][] board;
	// Not sure how to do end of game conditions yet, for now its this boolean
	private boolean playing;
	// Player 1 units:
	private ArrayList<Cell> player1Units;
	// Player 2 Units:
	private ArrayList<Cell> player2Units;
	// Player 1's name, obtained somehow from the GUI:
	private String player1Name;
	// Player 2's name, obtained somehow from the GUI:
	private String player2Name;

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
				// need to create the cells before we add them to the board and
				// try to access them.
				board[i][j] = new Cell(Terrain.Nothing, i, j);
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
		generatePlayer1Units();
		generatePlayer2Units();

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
		generatePlayer1Units();
		generatePlayer2Units();

	}

	// Responsible for adding Units to Cells who we desire to have a unit
	// For now creates one unit at board[0][0] and adds it to player1's units
	// list
	public void generatePlayer1Units() {
		// Instantiate player1Units:
		player1Units = new ArrayList<Cell>();

		// Creating one single unit for now:
		UnitFactory factory = new UnitFactory();
		// Last parameter is UserName obtained from the GUI, is "Player1" for
		// now:
		Unit aUnit = factory.makeUnit("CloneTrooper", "Player1");
		board[0][0].setUnit(aUnit);
		board[0][0].setHasUnit(true);
		// Adds this to player1Units list:
		player1Units.add(board[0][0]);

	}

	// Responsible for adding Units to Cells who we desire to have a unit
	// For now creates one unit at board[10][10] and adds it to player2's units
	// list
	public void generatePlayer2Units() {
		// Instantiate player1Units:
		player2Units = new ArrayList<Cell>();

		// Creating one single unit for now:
		UnitFactory factory = new UnitFactory();
		// Last parameter is UserName obtained from the GUI, is "Player2" for
		// now:
		Unit anotherUnit = factory.makeUnit("CloneTrooper", "Player2");
		board[10][10].setUnit(anotherUnit);
		board[10][10].setHasUnit(true);
		// Adds this to player1Units list:
		player2Units.add(board[10][10]);
	}

	// i think we should add units this way, that way i can loop through them on
	// a newgame method.
	public void generateUnitatCell(Cell cell, String unit, String username) {
		UnitFactory factory = new UnitFactory();
		Unit aUnit = factory.makeUnit(unit, username);
		cell.setHasUnit(true);
		cell.setUnit(aUnit);
	}

	// Responsible for returning a text version of the current GameBoard:
	public String toString() {
		String str = "";
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				str += "[ ";
				if (board[i][j].hasUnit()) {
					str += board[i][j].getUnit().toString();
				} else {
					if (board[i][j].getTerrain().equals(Terrain.Desert)) {
						str += "D";
					} else if (board[i][j].getTerrain().equals(Terrain.Boulder)) {
						str += "B";
					} else if (board[i][j].getTerrain().equals(Terrain.Lava)) {
						str += "L";

					} else if (board[i][j].getTerrain().equals(Terrain.Forest)) {
						str += "F";
					} else {
						str += " ";
					}

				}
				str += " ] ";
			}
			str += "\n";
		}
		return str;
	}
	
	// Checks to see if the unit in this cell can move or not:
	public boolean canMove(Cell cellWithUnit, String direction) {
		boolean result = true;
		if (direction.equals("N")) {
			// Can't move farther north:
			if (cellWithUnit.getLocation().x == 0)
				result = false;
			// Trying to move into a Boulder, return false.
			else if (board[cellWithUnit.getLocation().x - 1][cellWithUnit
					.getLocation().y].getTerrain() == Terrain.Boulder)
				result = false;
		} else if (direction.equals("S")) {
			// Can't move farther south:
			if (cellWithUnit.getLocation().x == 19)
				return false;
			// Trying to move into a Boulder, return false.
			else if (board[cellWithUnit.getLocation().x + 1][cellWithUnit
					.getLocation().y].getTerrain() == Terrain.Boulder)
				result = false;

		} else if (direction.equals("L")) {
			// Can't move farther left:
			if (cellWithUnit.getLocation().y == 0)
				result = false;
			// Trying to move into a Boulder, return false.
			else if (board[cellWithUnit.getLocation().x][cellWithUnit
					.getLocation().y - 1].getTerrain() == Terrain.Boulder)
				result = false;

		} else if (direction.equals("R")) {
			// Can't move farther right:
			if (cellWithUnit.getLocation().y == 19)
				return false;
			// Trying to move into a Boulder, return false.
			else if (board[cellWithUnit.getLocation().x][cellWithUnit
					.getLocation().y + 1].getTerrain() == Terrain.Boulder)
				result = false;
		}

		return result;
	}
	
	
	public Cell move(Cell cellWithUnit, String direction) {
		if (direction.equals("N")) {
			
			// Check to see what terrain we are about to step in:
			Terrain t = board[cellWithUnit.getLocation().x - 1][cellWithUnit.getLocation().y].getTerrain();
			// Lava reduced moves left and health left by 2:
			if (t==Terrain.Lava) {
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setMovesLeft(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getMovesLeft()-2);
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setHealth(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getHealth()-2);
			}
			// Reduce movement by 1:
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setMovesLeft(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getMovesLeft()-1);

			// Add unit to the cell above it
			board[cellWithUnit.getLocation().x - 1][cellWithUnit.getLocation().y].setUnit(cellWithUnit.getUnit());
			board[cellWithUnit.getLocation().x - 1][cellWithUnit.getLocation().y].setHasUnit(true);
			// Remove Unit from old cell
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].removeUnit();
			// return the cell the unit moved to:
			return board[cellWithUnit.getLocation().x - 1][cellWithUnit.getLocation().y];
		}
		else if (direction.equals("S")) {
			// Check to see what terrain we are about to step in:
			Terrain t = board[cellWithUnit.getLocation().x + 1][cellWithUnit.getLocation().y].getTerrain();
			// Lava reduced moves left and health left by 2:
			if (t==Terrain.Lava) {
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setMovesLeft(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getMovesLeft()-2);
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setHealth(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getHealth()-2);
			}
			// Reduce movement by 1:
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setMovesLeft(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getMovesLeft()-1);

			
			// Add unit to the cell below it
			board[cellWithUnit.getLocation().x + 1][cellWithUnit.getLocation().y].setUnit(cellWithUnit.getUnit());
			board[cellWithUnit.getLocation().x + 1][cellWithUnit.getLocation().y].setHasUnit(true);
			// Remove Unit from old cell
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].removeUnit();
			// return the cell the unit moved to:
			return board[cellWithUnit.getLocation().x + 1][cellWithUnit.getLocation().y];
		}
		else if (direction.equals("L")) {
			// Check to see what terrain we are about to step in:
			Terrain t = board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y - 1].getTerrain();
			// Lava reduced moves left and health left by 2:
			if (t==Terrain.Lava) {
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setMovesLeft(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getMovesLeft()-2);
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setHealth(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getHealth()-2);
			}
			// Reduce movement by 1:
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setMovesLeft(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getMovesLeft()-1);

			
			// Add unit to the cell to the left
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y-1].setUnit(cellWithUnit.getUnit());
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y-1].setHasUnit(true);
			// Remove Unit from old cell
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].removeUnit();
			// return the cell the unit moved to:
			return board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y-1];
		}
		else {
			// Check to see what terrain we are about to step in:
			Terrain t = board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y + 1].getTerrain();
			// Lava reduced moves left and health left by 2:
			if (t==Terrain.Lava) {
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setMovesLeft(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getMovesLeft()-2);
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setHealth(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getHealth()-2);
			}
			// Reduce movement by 1:
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().setMovesLeft(board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].getUnit().getMovesLeft()-1);

			// Better be Right, adds unit to the cell to the right
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y + 1].setUnit(cellWithUnit.getUnit());
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y + 1].setHasUnit(true);
			// Remove Unit from old cell
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y].removeUnit();
			// return the cell the unit moved to:
			return board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y + 1];
		}

		
	}
	
	// Get player1's list of cells that contain units:
	public ArrayList<Cell> getPlayer1Untis() {
		return player1Units;
	}

	// Get player1's list of cells that contain units:
	public ArrayList<Cell> getPlayer2Untis() {
		return player2Units;
	}

	// This method moves the unit in the cell given, it is GUARENTEED that the
	// cell contains a unit, also need direction, index of unit in list, and
	// player who played it:
	// public boolean move(Cell cellWithUnit, String direction, String player,
	// int indexOfUnit) {
	// // A unit in player1Units will be moved
	// if (player.equals(player1Name)) {
	//
	// if (player1Units.get(indexOfUnit).getMovesLeft() <= 0)
	// return false;
	// // theUnit can move:
	// else {
	// // Unit moves north:
	// if (direction.equals("N")) {
	// // Can't move farther north:
	// if (cellWithUnit.getLocation().x == 0)
	// return false;
	// // Trying to move into a Boulder, return false.
	// if (board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].getTerrain() == Terrain.Boulder)
	// return false;
	// // Can move north, and there is no Boulder in way:
	// else {
	//
	// // Reduce the Units movement by 1:
	// player1Units.get(indexOfUnit)
	// .setMovesLeft(
	// player1Units.get(indexOfUnit)
	// .getMovesLeft() - 1);
	//
	// // Deal with the Terrain theUnit is now standing in:
	//
	// // My reference to the terrain the unit is now standing
	// // in
	// Terrain terrain = board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].getTerrain();
	//
	// // Will be more here depending on future design
	// // decisions
	// // about the game's terrain:
	// // For now deal with lava as it is the only thing on the
	// // map
	// // other than boulders:
	// // For now lava reduces movement by 2, and causes 2
	// // damage:
	// if (terrain == Terrain.Lava) {
	// player1Units.get(indexOfUnit).setMovesLeft(
	// player1Units.get(indexOfUnit)
	// .getMovesLeft() - 2);
	// player1Units.get(indexOfUnit)
	// .setHealth(
	// player1Units.get(indexOfUnit)
	// .getHealth() - 2);
	// }
	//
	// // Finish this for more terrain later on
	//
	// // Add theUnit to the cell above it:
	// board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].setUnit(player1Units
	// .get(indexOfUnit));
	// board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].setHasUnit(true);
	// // Remove unit from the current cell:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y].removeUnit();
	//
	// // Return true at very end:
	// return true;
	//
	// }
	// } else if (direction.equals("S")) {
	// // Can't move farther south:
	// if (cellWithUnit.getLocation().x == 20)
	// return false;
	// // Trying to move into a Boulder, return false.
	// if (board[cellWithUnit.getLocation().x + 1][cellWithUnit
	// .getLocation().y].getTerrain() == Terrain.Boulder)
	// return false;
	// // Can move north:
	// else {
	//
	// // Reduce the Units movement by 1:
	// player1Units.get(indexOfUnit)
	// .setMovesLeft(
	// player1Units.get(indexOfUnit)
	// .getMovesLeft() - 1);
	//
	// // Deal with the Terrain theUnit is now standing in:
	//
	// // My reference to the terrain the unit is now standing
	// // in
	// Terrain terrain = board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].getTerrain();
	//
	// // Will be more here depending on future design
	// // decisions
	// // about the game's terrain:
	// // For now deal with lava as it is the only thing on the
	// // map
	// // other than boulders:
	// // For now lava reduces movement by 2, and causes 2
	// // damage:
	// if (terrain == Terrain.Lava) {
	// if (terrain == Terrain.Lava) {
	// player1Units.get(indexOfUnit).setMovesLeft(
	// player1Units.get(indexOfUnit)
	// .getMovesLeft() - 2);
	// player1Units.get(indexOfUnit).setHealth(
	// player1Units.get(indexOfUnit)
	// .getHealth() - 2);
	// }
	//
	// }
	//
	// // Finish this for more terrain later on
	//
	// // Add theUnit to the cell above it:
	// board[cellWithUnit.getLocation().x + 1][cellWithUnit
	// .getLocation().y].setUnit(player1Units
	// .get(indexOfUnit));
	// board[cellWithUnit.getLocation().x + 1][cellWithUnit
	// .getLocation().y].setHasUnit(true);
	// // Remove unit from the current cell:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y].removeUnit();
	//
	// // Return true at very end:
	// return true;
	// }
	// }
	//
	// // Left move:
	// else if (direction.equals("L")) {
	// // Can't move farther left:
	// if (cellWithUnit.getLocation().y == 0)
	// return false;
	// // Trying to move into a Boulder, return false.
	// if (board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y - 1].getTerrain() == Terrain.Boulder)
	// return false;
	// // Can move left:
	// else {
	// // Reduce the Units movement by 1:
	// player1Units.get(indexOfUnit)
	// .setMovesLeft(
	// player1Units.get(indexOfUnit)
	// .getMovesLeft() - 1);
	//
	// // Deal with the Terrain theUnit is now standing in:
	//
	// // My reference to the terrain the unit is now standing
	// // in
	// Terrain terrain = board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y - 1].getTerrain();
	//
	// // Will be more here depending on future design
	// // decisions
	// // about the game's terrain:
	// // For now deal with lava as it is the only thing on the
	// // map
	// // other than boulders:
	// // For now lava reduces movement by 2, and causes 2
	// // damage:
	// if (terrain == Terrain.Lava) {
	// if (terrain == Terrain.Lava) {
	// player1Units.get(indexOfUnit).setMovesLeft(
	// player1Units.get(indexOfUnit)
	// .getMovesLeft() - 2);
	// player1Units.get(indexOfUnit).setHealth(
	// player1Units.get(indexOfUnit)
	// .getHealth() - 2);
	// }
	//
	// }
	//
	// // Finish this for more terrain later on
	//
	// // Add theUnit to the cell to the left of it:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y - 1].setUnit(player1Units
	// .get(indexOfUnit));
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y - 1].setHasUnit(true);
	// // Remove unit from the current cell:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y].removeUnit();
	//
	// // Return true at very end:
	// return true;
	// }
	// }
	// // Right move:
	// else if (direction.equals("L")) {
	// // Can't move farther left:
	// if (cellWithUnit.getLocation().y == 20)
	// return false;
	// // Trying to move into a Boulder, return false.
	// if (board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y + 1].getTerrain() == Terrain.Boulder)
	// return false;
	// // Can move right:
	// else {
	// // Reduce the Units movement by 1:
	// player1Units.get(indexOfUnit)
	// .setMovesLeft(
	// player1Units.get(indexOfUnit)
	// .getMovesLeft() - 1);
	//
	// // Deal with the Terrain theUnit is now standing in:
	//
	// // My reference to the terrain the unit is now standing
	// // in
	// Terrain terrain = board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y + 1].getTerrain();
	//
	// // Will be more here depending on future design
	// // decisions
	// // about the game's terrain:
	// // For now deal with lava as it is the only thing on the
	// // map
	// // other than boulders:
	// // For now lava reduces movement by 2, and causes 2
	// // damage:
	// if (terrain == Terrain.Lava) {
	// if (terrain == Terrain.Lava) {
	// player1Units.get(indexOfUnit).setMovesLeft(
	// player1Units.get(indexOfUnit)
	// .getMovesLeft() - 2);
	// player1Units.get(indexOfUnit).setHealth(
	// player1Units.get(indexOfUnit)
	// .getHealth() - 2);
	// }
	//
	// }
	//
	// // Finish this for more terrain later on
	//
	// // Add theUnit to the cell to the right of it:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y + 1].setUnit(player1Units
	// .get(indexOfUnit));
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y + 1].setHasUnit(true);
	// // Remove unit from the current cell:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y].removeUnit();
	//
	// // Return true at very end:
	// return true;
	// }
	// }
	//
	// }
	// } else if (player.equals(player2Name)) {
	// if (player2Units.get(indexOfUnit).getMovesLeft() <= 0)
	// return false;
	// // theUnit can move:
	// else {
	// // Unit moves north:
	// if (direction.equals("N")) {
	// // Can't move farther north:
	// if (cellWithUnit.getLocation().x == 0)
	// return false;
	// // Trying to move into a Boulder, return false.
	// if (board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].getTerrain() == Terrain.Boulder)
	// return false;
	// // Can move north, and there is no Boulder in way:
	// else {
	// // Reduce the Units movement by 1:
	// player2Units.get(indexOfUnit)
	// .setMovesLeft(
	// player2Units.get(indexOfUnit)
	// .getMovesLeft() - 1);
	// // Deal with the Terrain theUnit is now standing in:
	//
	// // My reference to the terrain the unit is now standing
	// // in
	// Terrain terrain = board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].getTerrain();
	//
	// // Will be more here depending on future design
	// // decisions
	// // about the game's terrain:
	// // For now deal with lava as it is the only thing on the
	// // map
	// // other than boulders:
	// // For now lava reduces movement by 2, and causes 2
	// // damage:
	// if (terrain == Terrain.Lava) {
	// player2Units.get(indexOfUnit).setMovesLeft(
	// player2Units.get(indexOfUnit)
	// .getMovesLeft() - 2);
	// player2Units.get(indexOfUnit)
	// .setHealth(
	// player2Units.get(indexOfUnit)
	// .getHealth() - 2);
	// }
	//
	// // Finish this for more terrain later on
	//
	// // Add theUnit to the cell above it:
	// board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].setUnit(player2Units
	// .get(indexOfUnit));
	// board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].setHasUnit(true);
	// // Remove unit from the current cell:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y].removeUnit();
	//
	// // Return true at very end:
	// return true;
	//
	// }
	// } else if (direction.equals("S")) {
	// // Can't move farther south:
	// if (cellWithUnit.getLocation().x == 20)
	// return false;
	// // Trying to move into a Boulder, return false.
	// if (board[cellWithUnit.getLocation().x + 1][cellWithUnit
	// .getLocation().y].getTerrain() == Terrain.Boulder)
	// return false;
	// // Can move north:
	// else {
	//
	// // Reduce the Units movement by 1:
	// player2Units.get(indexOfUnit)
	// .setMovesLeft(
	// player2Units.get(indexOfUnit)
	// .getMovesLeft() - 1);
	//
	// // Deal with the Terrain theUnit is now standing in:
	//
	// // My reference to the terrain the unit is now standing
	// // in
	// Terrain terrain = board[cellWithUnit.getLocation().x - 1][cellWithUnit
	// .getLocation().y].getTerrain();
	//
	// // Will be more here depending on future design
	// // decisions
	// // about the game's terrain:
	// // For now deal with lava as it is the only thing on the
	// // map
	// // other than boulders:
	// // For now lava reduces movement by 2, and causes 2
	// // damage:
	// if (terrain == Terrain.Lava) {
	// if (terrain == Terrain.Lava) {
	// player2Units.get(indexOfUnit).setMovesLeft(
	// player2Units.get(indexOfUnit)
	// .getMovesLeft() - 2);
	// player2Units.get(indexOfUnit).setHealth(
	// player2Units.get(indexOfUnit)
	// .getHealth() - 2);
	// }
	//
	// }
	//
	// // Finish this for more terrain later on
	//
	// // Add theUnit to the cell above it:
	// board[cellWithUnit.getLocation().x + 1][cellWithUnit
	// .getLocation().y].setUnit(player2Units
	// .get(indexOfUnit));
	// board[cellWithUnit.getLocation().x + 1][cellWithUnit
	// .getLocation().y].setHasUnit(true);
	// // Remove unit from the current cell:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y].removeUnit();
	//
	// // Return true at very end:
	// return true;
	// }
	// }
	//
	// // Left move:
	// else if (direction.equals("L")) {
	// // Can't move farther left:
	// if (cellWithUnit.getLocation().y == 0)
	// return false;
	// // Trying to move into a Boulder, return false.
	// if (board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y - 1].getTerrain() == Terrain.Boulder)
	// return false;
	// // Can move left:
	// else {
	// // Reduce the Units movement by 1:
	// player2Units.get(indexOfUnit)
	// .setMovesLeft(
	// player2Units.get(indexOfUnit)
	// .getMovesLeft() - 1);
	//
	// // Deal with the Terrain theUnit is now standing in:
	//
	// // My reference to the terrain the unit is now standing
	// // in
	// Terrain terrain = board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y - 1].getTerrain();
	//
	// // Will be more here depending on future design
	// // decisions
	// // about the game's terrain:
	// // For now deal with lava as it is the only thing on the
	// // map
	// // other than boulders:
	// // For now lava reduces movement by 2, and causes 2
	// // damage:
	// if (terrain == Terrain.Lava) {
	// if (terrain == Terrain.Lava) {
	// player2Units.get(indexOfUnit).setMovesLeft(
	// player2Units.get(indexOfUnit)
	// .getMovesLeft() - 2);
	// player2Units.get(indexOfUnit).setHealth(
	// player2Units.get(indexOfUnit)
	// .getHealth() - 2);
	// }
	//
	// }
	//
	// // Finish this for more terrain later on
	//
	// // Add theUnit to the cell to the left of it:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y - 1].setUnit(player2Units
	// .get(indexOfUnit));
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y - 1].setHasUnit(true);
	// // Remove unit from the current cell:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y].removeUnit();
	//
	// // Return true at very end:
	// return true;
	// }
	// }
	// // Right move:
	// else if (direction.equals("L")) {
	// // Can't move farther left:
	// if (cellWithUnit.getLocation().y == 20)
	// return false;
	// // Trying to move into a Boulder, return false.
	// if (board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y + 1].getTerrain() == Terrain.Boulder)
	// return false;
	// // Can move right:
	// else {
	//
	// // Reduce the Units movement by 1:
	// player2Units.get(indexOfUnit)
	// .setMovesLeft(
	// player2Units.get(indexOfUnit)
	// .getMovesLeft() - 1);
	//
	// // Deal with the Terrain theUnit is now standing in:
	//
	// // My reference to the terrain the unit is now standing
	// // in
	// Terrain terrain = board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y + 1].getTerrain();
	//
	// // Will be more here depending on future design
	// // decisions
	// // about the game's terrain:
	// // For now deal with lava as it is the only thing on the
	// // map
	// // other than boulders:
	// // For now lava reduces movement by 2, and causes 2
	// // damage:
	// if (terrain == Terrain.Lava) {
	// if (terrain == Terrain.Lava) {
	// player2Units.get(indexOfUnit).setMovesLeft(
	// player2Units.get(indexOfUnit)
	// .getMovesLeft() - 2);
	// player2Units.get(indexOfUnit).setHealth(
	// player2Units.get(indexOfUnit)
	// .getHealth() - 2);
	// }
	//
	// }
	//
	// // Finish this for more terrain later on
	//
	// // Add theUnit to the cell to the right of it:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y + 1].setUnit(player2Units
	// .get(indexOfUnit));
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y + 1].setHasUnit(true);
	// // Remove unit from the current cell:
	// board[cellWithUnit.getLocation().x][cellWithUnit
	// .getLocation().y].removeUnit();
	//
	// // Return true at very end:
	// return true;
	// }
	// }
	//
	// }
	// }
	// return false;
	// }

	// When the turn is over, update movesLeft:
	// Assuming GUI passes an the name of the player to be updated as a string
	public void turnOver(String playerName) {

		if (playerName.equals(player1Name)) {
			for (int i = 0; i < player1Units.size(); i++) {
				player1Units
						.get(i)
						.getUnit()
						.setMovesLeft(
								player1Units.get(i).getUnit().getMoveRange());
			}
		} else if (playerName.equals(player2Name)) {
			for (int i = 0; i < player2Units.size(); i++) {
				player2Units
						.get(i)
						.getUnit()
						.setMovesLeft(
								player2Units.get(i).getUnit().getMoveRange());
			}
		}

	}

	// Returns the unit in this cell, or null if there is no unit in this
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

	// simple getter that fetches the cell that i need so we can play with the
	// data.
	public Cell getCell(int x, int y) {
		return board[x][y];
	}

	// Getters/Setters for player names, needs to set these names from the GUI
	public String getPlayer1Name() {
		return player1Name;
	}

	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}

	public String getPlayer2Name() {
		return player2Name;
	}

	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}

}
