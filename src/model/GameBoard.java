package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import unit.Unit;
import unit.UnitFactory;
import view.GUI;

// The GameBoard class creates the board for the game:

public class GameBoard implements Serializable {
	private static final long serialVersionUID = -3079556358722781506L;
	// board will be the most important thing in this class
	private Cell[][] board;
	// Not sure how to do end of game conditions yet, for now its this boolean
	private boolean playing;
	// Player 1 units:
	private ArrayList<Cell> player1Units;
	// Player 2 Units:
	private ArrayList<Cell> player2Units;

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
		
		board[10][10].setTerrain(Terrain.Ice);
		board[10][9].setTerrain(Terrain.QuickSand);
		board[11][9].setTerrain(Terrain.QuickSand);
		board[9][9].setTerrain(Terrain.QuickSand);
		board[9][10].setTerrain(Terrain.QuickSand);
		board[11][10].setTerrain(Terrain.QuickSand);	
		board[11][11].setTerrain(Terrain.QuickSand);
		board[9][11].setTerrain(Terrain.QuickSand);
		board[10][11].setTerrain(Terrain.QuickSand);
		
		

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
		// Last parameter is UserName obtained from the GUI
		Unit aUnit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
		board[0][0].setUnit(aUnit);
		board[0][0].setHasUnit(true);
	
		Unit bUnit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
		board[0][1].setUnit(bUnit);
		board[0][1].setHasUnit(true);
		
		Unit cUnit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
		board[19][19].setUnit(cUnit);
		board[19][19].setHasUnit(true);
		
		// Adds this to player1Units list:
		player1Units.add(board[0][0]);
		player1Units.add(board[0][1]);
		player1Units.add(board[19][19]);
		
		

	}

	// Responsible for adding Units to Cells who we desire to have a unit
	// For now creates one unit at board[10][10] and adds it to player2's units
	// list
	public void generatePlayer2Units() {
		// Instantiate player1Units:
		player2Units = new ArrayList<Cell>();

		// Creating one single unit for now:
		UnitFactory factory = new UnitFactory();
		// Last parameter is UserName obtained from the GUI
		/*Unit dUnit = factory.makeUnit("Medic", GUI.getPlayer2());
		board[1][0].setUnit(dUnit);
		board[1][0].setHasUnit(true);
		*/
		Unit eUnit = factory.makeUnit("Medic", GUI.getPlayer2());
		board[1][1].setUnit(eUnit);
		board[1][1].setHasUnit(true);
		
		// Adds this to player2Units list:
		//player2Units.add(board[1][0]);
		player2Units.add(board[1][1]);
		
		// Testing getUnitsInAttackRange: 
		System.out.println("Enemies of board[0][0] ");
		getUnitsInAttackRange(board[0][0]);
		System.out.println();
		System.out.println("Enemies of board[0][1] ");
		getUnitsInAttackRange(board[0][1]);
		System.out.println();
		System.out.println("Enemies of board[1][1] ");
		getUnitsInAttackRange(board[1][1]);
		
		


		
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
					} else if (board[i][j].getTerrain().equals(Terrain.QuickSand)) {
						str += "Q";
					}
					else if (board[i][j].getTerrain().equals(Terrain.Ice)) {
						str += "I";
					}
					else {
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
		Unit unit = cellWithUnit.getUnit();
		if (unit.getMovesLeft() <= 0) {
			return false;
		} else {
			
			if (direction.equals("N")) {
				// Can't move farther north:
				if (cellWithUnit.getLocation().x == 0)
					result = false;
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x - 1][cellWithUnit
						.getLocation().y].getTerrain() == Terrain.Boulder || board[cellWithUnit.getLocation().x - 1][cellWithUnit.getLocation().y].hasUnit() == true)
					result = false;
			} else if (direction.equals("S")) {
				// Can't move farther south:
				if (cellWithUnit.getLocation().x == 19)
					return false;
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x + 1][cellWithUnit
						.getLocation().y].getTerrain() == Terrain.Boulder || board[cellWithUnit.getLocation().x + 1][cellWithUnit.getLocation().y].hasUnit() == true)
					result = false;

			} else if (direction.equals("L")) {
				// Can't move farther left:
				if (cellWithUnit.getLocation().y == 0)
					result = false;
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y - 1].getTerrain() == Terrain.Boulder || board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y - 1].hasUnit() == true)
					result = false;

			} else if (direction.equals("R")) {
				// Can't move farther right:
				if (cellWithUnit.getLocation().y == 19)
					return false;
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y + 1].getTerrain() == Terrain.Boulder || board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y + 1].hasUnit()==true)
					result = false;
			}
		}
		return result;
	}

	public Cell move(Cell cellWithUnit, String direction) {
		if (direction.equals("N")) {

			// Check to see what terrain we are about to step in:
			Terrain t = board[cellWithUnit.getLocation().x - 1][cellWithUnit
					.getLocation().y].getTerrain();
			// Lava reduced moves left and health left by 2:
			if (t == Terrain.Lava) {
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setMovesLeft(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit()
										.getMovesLeft() - 2);
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setHealth(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit().getHealth() - 2);
			}
			// Reduce movement by 1:
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
					.getUnit()
					.setMovesLeft(
							board[cellWithUnit.getLocation().x][cellWithUnit
									.getLocation().y].getUnit().getMovesLeft() - 1);

			// Add unit to the cell above it
			board[cellWithUnit.getLocation().x - 1][cellWithUnit.getLocation().y]
					.setUnit(cellWithUnit.getUnit());
			board[cellWithUnit.getLocation().x - 1][cellWithUnit.getLocation().y]
					.setHasUnit(true);
			// Remove Unit from old cell
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
					.removeUnit();
			// return the cell the unit moved to:
			return board[cellWithUnit.getLocation().x - 1][cellWithUnit
					.getLocation().y];
		} else if (direction.equals("S")) {
			// Check to see what terrain we are about to step in:
			Terrain t = board[cellWithUnit.getLocation().x + 1][cellWithUnit
					.getLocation().y].getTerrain();
			// Lava reduced moves left and health left by 2:
			if (t == Terrain.Lava) {
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setMovesLeft(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit()
										.getMovesLeft() - 2);
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setHealth(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit().getHealth() - 2);
			}
			// Reduce movement by 1:
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
					.getUnit()
					.setMovesLeft(
							board[cellWithUnit.getLocation().x][cellWithUnit
									.getLocation().y].getUnit().getMovesLeft() - 1);

			// Add unit to the cell below it
			board[cellWithUnit.getLocation().x + 1][cellWithUnit.getLocation().y]
					.setUnit(cellWithUnit.getUnit());
			board[cellWithUnit.getLocation().x + 1][cellWithUnit.getLocation().y]
					.setHasUnit(true);
			// Remove Unit from old cell
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
					.removeUnit();
			// return the cell the unit moved to:
			return board[cellWithUnit.getLocation().x + 1][cellWithUnit
					.getLocation().y];
		} else if (direction.equals("L")) {
			// Check to see what terrain we are about to step in:
			Terrain t = board[cellWithUnit.getLocation().x][cellWithUnit
					.getLocation().y - 1].getTerrain();
			// Lava reduced moves left and health left by 2:
			if (t == Terrain.Lava) {
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setMovesLeft(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit()
										.getMovesLeft() - 2);
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setHealth(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit().getHealth() - 2);
			}
			// Reduce movement by 1:
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
					.getUnit()
					.setMovesLeft(
							board[cellWithUnit.getLocation().x][cellWithUnit
									.getLocation().y].getUnit().getMovesLeft() - 1);

			// Add unit to the cell to the left
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y - 1]
					.setUnit(cellWithUnit.getUnit());
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y - 1]
					.setHasUnit(true);
			// Remove Unit from old cell
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
					.removeUnit();
			// return the cell the unit moved to:
			return board[cellWithUnit.getLocation().x][cellWithUnit
					.getLocation().y - 1];
		} else {
			// Check to see what terrain we are about to step in:
			Terrain t = board[cellWithUnit.getLocation().x][cellWithUnit
					.getLocation().y + 1].getTerrain();
			// Lava reduced moves left and health left by 2:
			if (t == Terrain.Lava) {
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setMovesLeft(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit()
										.getMovesLeft() - 2);
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setHealth(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit().getHealth() - 2);
			}
			// Reduce movement by 1:
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
					.getUnit()
					.setMovesLeft(
							board[cellWithUnit.getLocation().x][cellWithUnit
									.getLocation().y].getUnit().getMovesLeft() - 1);

			// Better be Right, adds unit to the cell to the right
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y + 1]
					.setUnit(cellWithUnit.getUnit());
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y + 1]
					.setHasUnit(true);
			// Remove Unit from old cell
			board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
					.removeUnit();
			// return the cell the unit moved to:
			return board[cellWithUnit.getLocation().x][cellWithUnit
					.getLocation().y + 1];
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
	
	// This method returns a list of cells with units in it that 
	// can be attacked by a cell containing a unit given to it
	public ArrayList<Cell> getUnitsInAttackRange(Cell cellWithUnit) {
		ArrayList<Cell> unitsInRange = new ArrayList<Cell>();
		int range = cellWithUnit.getUnit().getAttackRange();
		
		// make sure we are in bounds:
		int startRow = cellWithUnit.getLocation().x - range;
		int startCol = cellWithUnit.getLocation().y - range;
		if (startRow <= 0)
			startRow = 0;
		if (startCol <= 0)
			startCol = 0;
		int finishRow = cellWithUnit.getLocation().x + range;
		int finishCol = cellWithUnit.getLocation().y + range;
		if (finishRow > 19)
			finishRow = 19;
		if (finishCol > 19)
			finishCol = 19;
		
		for (int i = startRow; i <= finishRow; i ++) {
			for (int j = startCol; j <= finishCol; j ++) {
				// Have located a unit in the range of the unit given:
				if (board[i][j].hasUnit()) {
					// Make sure it is not the unit in cellWithUnit:
					if (i==cellWithUnit.getLocation().x && j==cellWithUnit.getLocation().y) {
						// Do Nothing!
					}
					// See if this unit is an enemy:
					else if (board[i][j].getUnit().getUsername() != cellWithUnit.getUnit().getUsername())
						unitsInRange.add(board[i][j]);
					
				}
			}
		}
		
		for (int i = 0; i < unitsInRange.size(); i++) {
			System.out.print(unitsInRange.get(i).getUnit() + " ");
		}
		return unitsInRange;
	}
	
	// Attack method for two cells containing units being given:
	// returns the cell of unitBeingAttacked to the GUI, that updates the unit info in that cell:
	public Cell attack(Cell cellWithUnitAtacking, Cell cellWithUnitBeingAttacked) {
		
		cellWithUnitBeingAttacked.getUnit().setHealth(cellWithUnitBeingAttacked.getUnit().getHealth()-cellWithUnitAtacking.getUnit().getDamage());
		// unitBeingAttacked has died:
		if (cellWithUnitBeingAttacked.getUnit().getHealth() <= 0) {
			// remove this unit from whomever owns this unit:
			if (cellWithUnitBeingAttacked.getUnit().getUsername().equals(GUI.getPlayer1())) {
				// If Player 1 owns this unit, remove it from player1Units list:
				player1Units.remove(cellWithUnitBeingAttacked);
			}
			if (cellWithUnitBeingAttacked.getUnit().getUsername().equals(GUI.getPlayer2())) {
				// If Player 2 owns this unit, remove it from player2Units list:
				player2Units.remove(cellWithUnitBeingAttacked);
			}
			// Remove the unit from the Cell
			cellWithUnitBeingAttacked.removeUnit();
			
			// Return the new cell that now has no unit in it
			return cellWithUnitBeingAttacked;
		}
		// else return cellWithUnitBeingAttacked with updated info
		else 
			return cellWithUnitBeingAttacked;
		
	}
	
	// Checks to see if the game is over by looking at player1Untis List, and player2Units List 
	// to see if either one of them is empty, this verison returns a String explainig who lost:
	public String gameOverStringVersion() {
		
		if (player1Units.isEmpty()) {
			// Player 1 has lost:
			return GUI.getPlayer1() + " Has Lost The Game!";
		}
		if (player2Units.isEmpty()) {
			// Player 2 has lost:
			return GUI.getPlayer2() + " Has Lost The Game!";
		}
		
		return "";
	}
	
	// Checks to see if the game is over by looking at player1Untis List, and player2Units List 
	// to see if either one of them is empty, this verison returns a boolean, we dont know who lost:
	public boolean gameOverBooleanVersion() {
		
		if (player1Units.isEmpty()) {
			// Player 1 has lost:
			return true;
		}
		else if (player2Units.isEmpty()) {
			// Player 2 has lost:
			return true;
		}
		else 
			return false;
	}
	
	// When the turn is over, update movesLeft:
	// Assuming GUI passes an the name of the player to be updated as a string
	public void turnOver(String playerName) {

		if (playerName.equals(GUI.getPlayer1())) {
			for (int i = 0; i < player1Units.size(); i++) {
				player1Units
						.get(i)
						.getUnit()
						.setMovesLeft(
								player1Units.get(i).getUnit().getMoveRange());
			}
		} else if (playerName.equals(GUI.getPlayer2())) {
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


}
