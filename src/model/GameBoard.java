package model;

import item.Item;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import unit.Unit;
import unit.UnitFactory;
import view.GUI;

/**
 * Creating the GameBoard object needed to play this game
 * 
 * @author David Aaron
 * 
 */
public class GameBoard extends JFrame implements Serializable {
	private static final long serialVersionUID = -3079556358722781506L;
	private static final String saveDir = System.getProperty("user.dir")
			+ File.separator + "gamesaves" + File.separator;
	// board will be the most important thing in this class
	private Cell[][] board;
	// Player 1 units:
	private ArrayList<Cell> player1Units;
	// Player 2 Units:
	private ArrayList<Cell> player2Units;
	public volatile Queue<Command> commandqueue;
	public static String background;
	private AI computer;
	String mapName;

	/**
	 * Constructor for the GameBoard object
	 * 
	 * @param mapName, ArrayList<Unit>, units selected by the user in GUI
	 *            Name of Map, either "Map 1", "Map 2", "Random", or "Monster"
	 */
	public GameBoard(String mapName, ArrayList<Unit> units) {
		commandqueue = new LinkedList<Command>();
		this.mapName = mapName;
		if (mapName.equals("Map 1"))
			createMap1(units);
		else if (mapName.equals("Map 2"))
			createMap2(units);
		else if (mapName.equals("Random"))
			createRandomMap(units);
		else if (mapName.equals("Monster"))
			createMonsterMap(units);
		else
			createVsComputerMap(units);

	}
	/**
	 * Used to create units, has become obsolete
	 * @return ArrayList<Unit>, the units that will be added to the board
	 */
	public ArrayList<Unit> createUnitSelection() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		UnitFactory factory = new UnitFactory();
		Unit unit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
		units.add(0, unit);
		unit = factory.makeUnit("DarthVader", GUI.getPlayer1());
		units.add(1,unit);
		unit = factory.makeUnit("LukeSkywalker", GUI.getPlayer1());
		units.add(2,unit);
		unit = factory.makeUnit("SpiderTank", GUI.getPlayer1());
		units.add(3,unit);
		unit = factory.makeUnit("Walker", GUI.getPlayer1());
		units.add(4,unit);
		unit = factory.makeUnit("BattleDroid", GUI.getPlayer2());
		units.add(5, unit);
		unit = factory.makeUnit("ImperialMedic", GUI.getPlayer2());
		units.add(6,unit);
		unit = factory.makeUnit("DarthVader", GUI.getPlayer2());
		units.add(7,unit);
		unit = factory.makeUnit("Walker", GUI.getPlayer2());
		units.add(8,unit);
		unit = factory.makeUnit("SpiderTank", GUI.getPlayer2());
		units.add(9,unit);
		return units;
	}
	/**
	 * used to create units for the monster game, not sure if still needed
	 * @return the untis to be added to the board
	 */
	public ArrayList<Unit> createUnitSelectionMonster() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		UnitFactory factory = new UnitFactory();
		Unit unit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
		units.add(0, unit);
		unit = factory.makeUnit("DarthVader", GUI.getPlayer1());
		units.add(1,unit);
		unit = factory.makeUnit("LukeSkywalker", GUI.getPlayer1());
		units.add(2,unit);
		unit = factory.makeUnit("SpiderTank", GUI.getPlayer1());
		units.add(3,unit);
		unit = factory.makeUnit("Walker", GUI.getPlayer1());
		units.add(4,unit);
	
		return units;
	}
	
	/**
	 * Creates Map1
	 */
	public void createMap1(ArrayList<Unit> units) {
		
		background = "Grass.png";
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
		// Quicksand pit:
		board[10][9].setTerrain(Terrain.QuickSand);
		board[11][9].setTerrain(Terrain.QuickSand);
		board[9][9].setTerrain(Terrain.QuickSand);
		board[9][10].setTerrain(Terrain.QuickSand);
		board[11][10].setTerrain(Terrain.QuickSand);
		board[11][11].setTerrain(Terrain.QuickSand);
		board[9][11].setTerrain(Terrain.QuickSand);
		board[10][11].setTerrain(Terrain.QuickSand);
		
		// place flag:
		board[10][10].setTerrain(Terrain.Flag);
		
		// Boulders and Lavas:
		board[8][8].setTerrain(Terrain.Boulder);
		board[8][12].setTerrain(Terrain.Boulder);
		board[12][8].setTerrain(Terrain.Boulder);
		board[12][12].setTerrain(Terrain.Boulder);
		board[9][8].setTerrain(Terrain.Lava);
		board[11][8].setTerrain(Terrain.Lava);
		board[9][12].setTerrain(Terrain.Lava);
		board[11][12].setTerrain(Terrain.Lava);
		board[10][12].setTerrain(Terrain.Boulder);
		board[10][8].setTerrain(Terrain.Boulder);
		board[8][9].setTerrain(Terrain.Lava);
		board[8][10].setTerrain(Terrain.Boulder);
		board[8][11].setTerrain(Terrain.Lava);
		board[12][9].setTerrain(Terrain.Lava);
		board[12][10].setTerrain(Terrain.Boulder);
		board[12][11].setTerrain(Terrain.Lava);
		
		// Ice
		for (int i = 3; i < 17; i++) 
			board[3][i].setTerrain(Terrain.Ice);
		for (int i = 3; i < 17; i++) 
			board[17][i].setTerrain(Terrain.Ice);
		for (int i = 3; i < 17; i++)
			board[7][i].setTerrain(Terrain.Ice);
		for (int i = 3; i < 17; i++)
			board[13][i].setTerrain(Terrain.Ice);
		
		board[17][17].setTerrain(Terrain.Lava);
		board[3][2].setTerrain(Terrain.Lava);
		board[7][17].setTerrain(Terrain.QuickSand);		
		board[13][2].setTerrain(Terrain.QuickSand);

		for (int i = 0; i < 20; i++) { 
			board[i][5].setTerrain(Terrain.Boulder);
			i++;
		}
		for (int i = 0; i < 20; i++) { 
			board[i][15].setTerrain(Terrain.Boulder);
			i++;
		}
		for (int i = 3; i < 17; i++)
			board[7][i].setTerrain(Terrain.Ice);
		
//		ArrayList<Unit> unitsToAdd = new ArrayList<Unit>();
//		unitsToAdd = createUnitSelection();
//	
		tempGenerateMap1Or2Units(units);
	}

	/**
	 * Creates Map 2, ice madness map
	 */
	public void createMap2(ArrayList<Unit> units) {
		background = "desert.png";
		// board is 20 by 20 for now:
		board = new Cell[20][20];
		// initialize all cells to contain no units, and create desert map
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				// need to create the cells before we add them to the board and
				// try to access them.
				board[i][j] = new Cell(Terrain.Ice, i, j);
				board[i][j].setUnit(null);
				// Give each cell a location
				board[i][j].setLocation(new Point(i, j));
			}
		}
	
		tempGenerateMap1Or2Units(units);

		Random rand = new Random();
		int numberOfBoulders = 0;
		while (numberOfBoulders < 75) {
			int randomX = rand.nextInt(20);
			int randomY = rand.nextInt(20);
			if (board[randomX][randomY].getTerrain() == Terrain.Ice
					&& board[randomX][randomY].hasUnit() == false) {
				board[randomX][randomY].setTerrain(Terrain.Boulder);
				numberOfBoulders++;
			}
		}
		// Create a flag:
		int numberOfFlags = 0;
		while (numberOfFlags < 1) {
			int randomX = rand.nextInt(20);
			int randomY = rand.nextInt(20);
			if (board[randomX][randomY].getTerrain() == Terrain.Ice && board[randomX][randomY].hasUnit() == false) {
				board[randomX][randomY].setTerrain(Terrain.Flag);
				numberOfFlags++;
			}
		}
		

	}
	/**
	 * Creates a game against the computer, needs to be fixed
	 */
	public void createVsComputerMap(ArrayList<Unit> units) {
		computer = new AI();
		background = "Grass.png";
		// board is 20 by 20 for now:
		board = new Cell[20][20];

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
		
		// place flag:
		board[10][10].setTerrain(Terrain.Flag);
		
		// Boulders:
		board[6][7].setTerrain(Terrain.Boulder);
		board[6][13].setTerrain(Terrain.Boulder);
		board[13][13].setTerrain(Terrain.Boulder);
		board[13][7].setTerrain(Terrain.Boulder);
		

		addPlayerUnitsAndGenerateComputerUnits(units);

	}
	/**
	 * Creates the game against the computer controlled monster, also needs to be fixed
	 */
	public void createMonsterMap(ArrayList<Unit> units) {
		
		board = new Cell[20][20];
		// Desert background
		background = "desert.png";
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				// need to create the cells before we add them to the board
				// and
				// try to access them.
				board[i][j] = new Cell(Terrain.Nothing, i, j);
				board[i][j].setUnit(null);
				// Give each cell a location
				board[i][j].setLocation(new Point(i, j));
			}
		}
		
		for (int i = 0; i < 20; i++) {
			board[4][i].setTerrain(Terrain.Lava);
			board[9][i].setTerrain(Terrain.Lava);
			board[15][i].setTerrain(Terrain.Lava);

		}
		for (int i = 0; i < 20; i++) {
			board[i][4].setTerrain(Terrain.Lava);
			board[i][9].setTerrain(Terrain.Lava);
			board[i][15].setTerrain(Terrain.Lava);

		}
		board[10][10].setTerrain(Terrain.Flag);
	
		tempGenerateMonsterPlayer1Units(units);
		// Still call generateMonster
		generateMonster();
		
	}
	

	/**
	 * Creates a random map:
	 */
	public void createRandomMap(ArrayList<Unit> units) {
		Random rand = new Random();
		int random = rand.nextInt(2);

		if (random == 0) {
			// board is 20 by 20 for now:
			board = new Cell[20][20];
			// Desert background
			background = "desert.png";
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					// need to create the cells before we add them to the board
					// and
					// try to access them.
					board[i][j] = new Cell(Terrain.Nothing, i, j);
					board[i][j].setUnit(null);
					// Give each cell a location
					board[i][j].setLocation(new Point(i, j));
				}
			}
			// Generate 150 random Terrains on the map:
			int numberOfTerriansOnTheBoard = 0;
			while (numberOfTerriansOnTheBoard < 200) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				int randomTerrain = rand.nextInt(7);

				// Create Boulder
				if (randomTerrain == 0) {
					if (board[randomX][randomY].getTerrain() == Terrain.Nothing) {
						board[randomX][randomY].setTerrain(Terrain.Boulder);
						numberOfTerriansOnTheBoard++;
					}
				}
				// Create Quicksand
				else if (randomTerrain == 1) {
					if (board[randomX][randomY].getTerrain() == Terrain.Nothing) {
						board[randomX][randomY].setTerrain(Terrain.QuickSand);
						numberOfTerriansOnTheBoard++;
					}
				}
				// Create Ice
				else if (randomTerrain == 2 || randomTerrain == 3
						|| randomTerrain == 4) {
					if (board[randomX][randomY].getTerrain() == Terrain.Nothing) {
						board[randomX][randomY].setTerrain(Terrain.Ice);
						numberOfTerriansOnTheBoard++;
					}
				}
				// Create Lava
				else if (randomTerrain == 5) {
					if (board[randomX][randomY].getTerrain() == Terrain.Nothing) {
						board[randomX][randomY].setTerrain(Terrain.Lava);
						numberOfTerriansOnTheBoard++;
					}
				}
				// Does nothing, no new Terrain assigned:
				else if (randomTerrain == 6) {

				}
			}
			// Create a flag:
			int numberOfFlags = 0;
			while (numberOfFlags < 1) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				if (board[randomX][randomY].getTerrain() == Terrain.Nothing && board[randomX][randomY].hasUnit() == false) {
					board[randomX][randomY].setTerrain(Terrain.Flag);
					numberOfFlags++;
				}
			}
		
			tempGenerateRandomMapUnits(units);
			

		} else {
			// board is 20 by 20 for now:
			board = new Cell[20][20];
			// Forest background
			background = "Grass.png";
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					// need to create the cells before we add them to the board
					// and
					// try to access them.
					board[i][j] = new Cell(Terrain.Nothing, i, j);
					board[i][j].setUnit(null);
					// Give each cell a location
					board[i][j].setLocation(new Point(i, j));
				}
			}
			// Generate 150 random Terrains on the map:
			int numberOfTerriansOnTheBoard = 0;
			while (numberOfTerriansOnTheBoard < 200) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				int randomTerrain = rand.nextInt(7);

				// Create Boulder
				if (randomTerrain == 0) {
					if (board[randomX][randomY].getTerrain() == Terrain.Nothing) {
						board[randomX][randomY].setTerrain(Terrain.Boulder);
						numberOfTerriansOnTheBoard++;
					}
				}
				// Create Quicksand
				else if (randomTerrain == 1) {
					if (board[randomX][randomY].getTerrain() == Terrain.Nothing) {
						board[randomX][randomY].setTerrain(Terrain.QuickSand);
						numberOfTerriansOnTheBoard++;
					}
				}
				// Create Ice
				else if (randomTerrain == 2 || randomTerrain == 3
						|| randomTerrain == 4) {
					if (board[randomX][randomY].getTerrain() == Terrain.Nothing) {
						board[randomX][randomY].setTerrain(Terrain.Ice);
						numberOfTerriansOnTheBoard++;
					}
				}
				// Create Lava
				else if (randomTerrain == 5) {
					if (board[randomX][randomY].getTerrain() == Terrain.Nothing) {
						board[randomX][randomY].setTerrain(Terrain.Lava);
						numberOfTerriansOnTheBoard++;
					}
				}
				// Does nothing, no new Terrain assigned:
				else if (randomTerrain == 6) {

				}
			}

			// Create a flag:
			int numberOfFlags = 0;
			while (numberOfFlags < 1) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				if (board[randomX][randomY].getTerrain() == Terrain.Nothing && board[randomX][randomY].hasUnit() == false) {
					board[randomX][randomY].setTerrain(Terrain.Flag);
					numberOfFlags++;
				}
			}
			
			
			tempGenerateRandomMapUnits(units);


		}
	}
	/**
	 * Generates maps 1 and 2's units, given the units the selected in the GUI, replaces
	 * the older methods
	 * @param units the units to be added to the board
	 */
	public void tempGenerateMap1Or2Units(ArrayList<Unit> units) {
		player1Units = new ArrayList<Cell>();
		player2Units = new ArrayList<Cell>();

		// Player 1 has first five units in this list
		board[3][0].setUnit(units.get(0));
		board[3][0].setHasUnit(true);
		player1Units.add(board[3][0]);
		
		board[7][0].setUnit(units.get(1));
		board[7][0].setHasUnit(true);
		player1Units.add(board[7][0]);
		
		board[11][0].setUnit(units.get(2));
		board[11][0].setHasUnit(true);
		player1Units.add(board[11][0]);
		
		board[15][0].setUnit(units.get(3));
		board[15][0].setHasUnit(true);
		player1Units.add(board[15][0]);
		
		board[19][0].setUnit(units.get(4));
		board[19][0].setHasUnit(true);
		player1Units.add(board[19][0]);
		
		// Player 2 has last five units in this list
		board[3][19].setUnit(units.get(5));
		board[3][19].setHasUnit(true);
		player2Units.add(board[3][19]);
		
		board[7][19].setUnit(units.get(6));
		board[7][19].setHasUnit(true);
		player2Units.add(board[7][19]);
		
		board[11][19].setUnit(units.get(7));
		board[11][19].setHasUnit(true);
		player2Units.add(board[11][19]);
		
		board[15][19].setUnit(units.get(8));
		board[15][19].setHasUnit(true);
		player2Units.add(board[15][19]);
		
		board[19][19].setUnit(units.get(9));
		board[19][19].setHasUnit(true);
		player2Units.add(board[19][19]);
		
		
	}
	/**
	 * adds monster game player 1 units to the board, not sure how this is going to work yet
	 * @param units
	 */
	public void tempGenerateMonsterPlayer1Units(ArrayList<Unit> units) {
		Random rand = new Random();
		player1Units = new ArrayList<Cell>();
		player2Units = new ArrayList<Cell>();

		int numberOfUnitsPlaced = 0;
		while (numberOfUnitsPlaced < 5) {
			int randomX = rand.nextInt(20);
			int randomY = rand.nextInt(20);
			while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
				randomX = rand.nextInt(20);
				randomY = rand.nextInt(20);
			}
			board[randomX][randomY].setUnit(units.get(numberOfUnitsPlaced));
			board[randomX][randomY].setHasUnit(true);
			// Add to player1Units:
			player1Units.add(board[randomX][randomY]);
			numberOfUnitsPlaced++;
		}
		
	}
	
	
	
	/**
	 * Adds the units selected in GUI from both players to the random map game type
	 * @param units, the units to be added to the board, selected from GUI
	 */
	public void tempGenerateRandomMapUnits(ArrayList<Unit> units) {
		Random rand = new Random();
		player1Units = new ArrayList<Cell>();
		player2Units = new ArrayList<Cell>();

		int numberOfUnitsPlaced = 0;
		while (numberOfUnitsPlaced < 5) {
			int randomX = rand.nextInt(20);
			int randomY = rand.nextInt(20);
			while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
				randomX = rand.nextInt(20);
				randomY = rand.nextInt(20);
			}

			board[randomX][randomY].setUnit(units.get(numberOfUnitsPlaced));
			board[randomX][randomY].setHasUnit(true);
			// Add to player1Units:
			player1Units.add(board[randomX][randomY]);
			numberOfUnitsPlaced++;
		}
		
		while (numberOfUnitsPlaced < 10) {
			int randomX = rand.nextInt(20);
			int randomY = rand.nextInt(20);
			while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
				randomX = rand.nextInt(20);
				randomY = rand.nextInt(20);
			}
			board[randomX][randomY].setUnit(units.get(numberOfUnitsPlaced));
			board[randomX][randomY].setHasUnit(true);
			// Add to player1Units:
			player2Units.add(board[randomX][randomY]);
			numberOfUnitsPlaced++;
		}
		
	}
	
	
	
	/**
	 * Responsible for adding Units to Cells who we desire to have a unit, these
	 * are Player1's units
	 */
	public void generatePlayer1Units() {
		// Instantiate player1Units:
		player1Units = new ArrayList<Cell>();

		// Creating one single unit for now:
		UnitFactory factory = new UnitFactory();
		// Last parameter is UserName obtained from the GUI
		Unit aUnit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
		board[5][0].setUnit(aUnit);
		board[5][0].setHasUnit(true);

		// Adds this to player1Units list:
		player1Units.add(board[5][0]);

	}

	/**
	 * Responsible for adding Units to Cells who we desire to have a unit, these
	 * are Player2's units
	 */
	public void generatePlayer2Units() {
		Random rand = new Random();
		// Instantiate player2Units:
		player2Units = new ArrayList<Cell>();
		ArrayList<Unit> units = new ArrayList<Unit>();
		UnitFactory factory = new UnitFactory();
		Unit unit = factory.makeUnit("CloneTrooper", GUI.getPlayer2());
		int random = rand.nextInt(8);
		int numberOfUnits = 0;
		while (numberOfUnits < 5) {
			random = rand.nextInt(8);
			if (random == 0) {
				unit = factory.makeUnit("CloneTrooper", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
			else if (random ==1) {
				unit = factory.makeUnit("BattleDroid", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
			else if (random ==2) {
				unit = factory.makeUnit("DarthVader", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
			else if (random ==3) {
				unit = factory.makeUnit("LukeSkywalker", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
			else if (random ==4) {
				unit = factory.makeUnit("Walker", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
			else if (random ==5) {
				unit = factory.makeUnit("Droideka", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
			else if (random ==6) {
				unit = factory.makeUnit("ImperialMedic", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
			else if (random ==7) {
				unit = factory.makeUnit("ArtilleryDroid", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
			else if (random ==8) {
				unit = factory.makeUnit("SpiderTank", GUI.getPlayer2());
				units.add(numberOfUnits, unit);
				numberOfUnits++;
			}
		}
	
		board[3][19].setUnit(units.get(0));
				board[3][19].setHasUnit(true);
				player2Units.add(board[3][19]);
				
				board[7][19].setUnit(units.get(1));
				board[7][19].setHasUnit(true);
				player2Units.add(board[7][19]);
				
				board[11][19].setUnit(units.get(2));
				board[11][19].setHasUnit(true);
				player2Units.add(board[11][19]);
				
				board[15][19].setUnit(units.get(3));
				board[15][19].setHasUnit(true);
				player2Units.add(board[15][19]);
				
				board[19][19].setUnit(units.get(4));
				board[19][19].setHasUnit(true);
				player2Units.add(board[19][19]);
		
		

	}
	/**
	 * Adds computer units to the board, needs fixing
	 */
	public void addPlayerUnitsAndGenerateComputerUnits(ArrayList<Unit> units) {
		player1Units = new ArrayList<Cell>();
		// Player 1 has first five units in this list
		board[3][0].setUnit(units.get(0));
		board[3][0].setHasUnit(true);
		player1Units.add(board[3][0]);
		
		board[7][0].setUnit(units.get(1));
		board[7][0].setHasUnit(true);
		player1Units.add(board[7][0]);
		
		board[11][0].setUnit(units.get(2));
		board[11][0].setHasUnit(true);
		player1Units.add(board[11][0]);
		
		board[15][0].setUnit(units.get(3));
		board[15][0].setHasUnit(true);
		player1Units.add(board[15][0]);
		
		board[19][0].setUnit(units.get(4));
		board[19][0].setHasUnit(true);
		player1Units.add(board[19][0]);
		
		generatePlayer2Units();

		

	}

	/**
	 * Generates two random units for player1, not standing in any type of
	 * terrain
	 */
	public void generateRandomPlayer1Units() {
		// Instantiate player1Units:
		player1Units = new ArrayList<Cell>();
		UnitFactory factory = new UnitFactory();
		Random rand = new Random();
		int numberOfUnits = 0;
		while (numberOfUnits < 3) {
			int random = rand.nextInt(4);
			// Creates Clone Trooper at random location
			if (random == 0) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
					randomX = rand.nextInt(20);
					randomY = rand.nextInt(20);
				}
				// There is no terrain here:
				// Create a Clone Trooper
				Unit aUnit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
				board[randomX][randomY].setUnit(aUnit);
				board[randomX][randomY].setHasUnit(true);
				// Add to player1Units:
				player1Units.add(board[randomX][randomY]);
				numberOfUnits++;
			}
			// Creates Jedi at random location
			if (random == 1) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
					randomX = rand.nextInt(20);
					randomY = rand.nextInt(20);
				}
				// There is no terrain here:
				// Create a Clone Trooper
				Unit aUnit = factory
						.makeUnit("LukeSkywalker", GUI.getPlayer1());
				board[randomX][randomY].setUnit(aUnit);
				board[randomX][randomY].setHasUnit(true);
				// Add to player1Units:
				player1Units.add(board[randomX][randomY]);
				numberOfUnits++;
			}
			if (random == 2) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
					randomX = rand.nextInt(20);
					randomY = rand.nextInt(20);
				}
				// There is no terrain here:
				// Create a Medic
				Unit aUnit = factory.makeUnit("ImperialMedic", GUI.getPlayer1());
				board[randomX][randomY].setUnit(aUnit);
				board[randomX][randomY].setHasUnit(true);
				// Add to player1Units:
				player1Units.add(board[randomX][randomY]);
				numberOfUnits++;
			}
			if (random == 3) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
					randomX = rand.nextInt(20);
					randomY = rand.nextInt(20);
				}
				// There is no terrain here:
				// Create a Spider Tank
				Unit aUnit = factory.makeUnit("SpiderTank", GUI.getPlayer1());
				board[randomX][randomY].setUnit(aUnit);
				board[randomX][randomY].setHasUnit(true);
				// Add to player1Units:
				player1Units.add(board[randomX][randomY]);
				numberOfUnits++;
			}

		}

	}

	/**
	 * Generates two random units for player1, not standing in any type of
	 * terrain
	 */
	public void generateRandomPlayer2Units() {
		// Instantiate player2Units:
		player2Units = new ArrayList<Cell>();
		UnitFactory factory = new UnitFactory();
		Random rand = new Random();
		int numberOfUnits = 0;
		while (numberOfUnits < 5) {
			int random = rand.nextInt(4);
			// Creates Clone Trooper at random location
			if (random == 0) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
					randomX = rand.nextInt(20);
					randomY = rand.nextInt(20);
				}
				// There is no terrain here:
				// Create a Clone Trooper
				Unit aUnit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
				board[randomX][randomY].setUnit(aUnit);
				board[randomX][randomY].setHasUnit(true);
				// Add to player1Units:
				player2Units.add(board[randomX][randomY]);
				numberOfUnits++;
			}
			// Creates Jedi at random location
			if (random == 1) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
					randomX = rand.nextInt(20);
					randomY = rand.nextInt(20);
				}
				// There is no terrain here:
				// Create a Clone Trooper
				Unit aUnit = factory
						.makeUnit("LukeSkywalker", GUI.getPlayer1());
				board[randomX][randomY].setUnit(aUnit);
				board[randomX][randomY].setHasUnit(true);
				// Add to player1Units:
				player2Units.add(board[randomX][randomY]);
				numberOfUnits++;
			}
			if (random == 2) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
					randomX = rand.nextInt(20);
					randomY = rand.nextInt(20);
				}
				// There is no terrain here:
				// Create a Medic
				Unit aUnit = factory.makeUnit("ImperialMedic", GUI.getPlayer1());
				board[randomX][randomY].setUnit(aUnit);
				board[randomX][randomY].setHasUnit(true);
				// Add to player1Units:
				player2Units.add(board[randomX][randomY]);
				numberOfUnits++;
			}
			if (random == 3) {
				int randomX = rand.nextInt(20);
				int randomY = rand.nextInt(20);
				while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
					randomX = rand.nextInt(20);
					randomY = rand.nextInt(20);
				}
				// There is no terrain here:
				// Create a Spider Tank
				Unit aUnit = factory.makeUnit("SpiderTank", GUI.getPlayer1());
				board[randomX][randomY].setUnit(aUnit);
				board[randomX][randomY].setHasUnit(true);
				// Add to player1Units:
				player2Units.add(board[randomX][randomY]);
				numberOfUnits++;
			}

		}

	}
	/**
	 * Not sure how to do this yet, but adds a monster to the board
	 */
	public void generateMonster() {
		player2Units = new ArrayList<Cell>();
		UnitFactory factory = new UnitFactory();
		Random rand = new Random();
		int number = rand.nextInt(2);
		Unit monster = factory.makeUnit("Wampa", GUI.getPlayer2());
		if (number == 1) {
			monster = factory.makeUnit("Rancor", GUI.getPlayer2());
		}
		
		int randomX = rand.nextInt(20);
		int randomY = rand.nextInt(20);
		while (board[randomX][randomY].getTerrain() != Terrain.Nothing || board[randomX][randomY].hasUnit()) {
			randomX = rand.nextInt(20);
			randomY = rand.nextInt(20);
		}
		board[randomX][randomY].setUnit(monster);
		board[randomX][randomY].setHasUnit(true);
		player2Units.add(board[randomX][randomY]);

	}
	

	/**
	 * Responsible for returning a text version of the current GameBoard:
	 */
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
						str += "V";

					} else if (board[i][j].getTerrain().equals(Terrain.Forest)) {
						str += "F";
					} else if (board[i][j].getTerrain().equals(
							Terrain.QuickSand)) {
						str += "Q";
					} else if (board[i][j].getTerrain().equals(Terrain.Ice)) {
						str += "I";
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
	/**
	 * Checks to see if the unit in this cell can move or not:
	 * 
	 * @param cellWithUnit
	 *            This is given to me from the GUI contains a unit
	 * @param direction
	 *            Direction of Movement desired
	 * @return true is this unit can move, false if it can't
	 */
	public boolean canMoveAIStyle(Cell cellWithUnit, String direction) {
		boolean result = true;
		Unit unit = cellWithUnit.getUnit();
		if (unit.getMovesLeft() <= 0) {
			return false;
		} else {

			if (direction.equals("N")) {
				// Can't move farther north:
				if (cellWithUnit.getLocation().x == 0) {
					result = false;
				}
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x - 1][cellWithUnit
						.getLocation().y].getTerrain() == Terrain.Boulder) {
					result = false;
				}
				// Trying to move into a Unit, return false.
				else if (board[cellWithUnit.getLocation().x - 1][cellWithUnit
						.getLocation().y].hasUnit() == true) {
					result = false;
				}
			} else if (direction.equals("S")) {
				// Can't move farther south:
				if (cellWithUnit.getLocation().x == 19) {
					return false;
				}
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x + 1][cellWithUnit
						.getLocation().y].getTerrain() == Terrain.Boulder) {
					result = false;
				}
				// Trying to move into a Unit, return false.
				else if (board[cellWithUnit.getLocation().x + 1][cellWithUnit
						.getLocation().y].hasUnit() == true) {
					result = false;
				}

			} else if (direction.equals("L")) {
				// Can't move farther left:
				if (cellWithUnit.getLocation().y == 0) {
					result = false;
				}
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y - 1].getTerrain() == Terrain.Boulder) {
					result = false;
				}
				// Trying to move into a Unit, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y - 1].hasUnit() == true) {
					result = false;
				}

			} else if (direction.equals("R")) {
				// Can't move farther right:
				if (cellWithUnit.getLocation().y == 19) {
					return false;
				}
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y + 1].getTerrain() == Terrain.Boulder) {
					result = false;
				}
				// Trying to move into a Unit, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y + 1].hasUnit() == true) {
					result = false;
				}
			}
		}
		return result;
	}


	/**
	 * Checks to see if the unit in this cell can move or not:
	 * 
	 * @param cellWithUnit
	 *            This is given to me from the GUI contains a unit
	 * @param direction
	 *            Direction of Movement desired
	 * @return true is this unit can move, false if it can't
	 */
	public boolean canMove(Cell cellWithUnit, String direction) {
		boolean result = true;
		Unit unit = cellWithUnit.getUnit();
		if (unit.getMovesLeft() <= 0) {
			// Tell them that they are out of moves:
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage("Your Unit is out of Moves");
			JDialog dialog = optionPane.createDialog(":~(");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
			return false;
		} else {

			if (direction.equals("N")) {
				// Can't move farther north:
				if (cellWithUnit.getLocation().x == 0) {
					// Tell them that they can't move off of the map:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("What are you trying to do? Move off the Map?, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x - 1][cellWithUnit
						.getLocation().y].getTerrain() == Terrain.Boulder) {
					// Tell them that they can't move into a boulder:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You can't move into a Boulder, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}
				// Trying to move into a Unit, return false.
				else if (board[cellWithUnit.getLocation().x - 1][cellWithUnit
						.getLocation().y].hasUnit() == true) {
					// Tell them that they can't move into a Unit:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You can't move into another Unit, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}
			} else if (direction.equals("S")) {
				// Can't move farther south:
				if (cellWithUnit.getLocation().x == 19) {
					// Tell them that they can't move off of the map:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("What are you trying to do? Move off the Map?, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					return false;
				}
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x + 1][cellWithUnit
						.getLocation().y].getTerrain() == Terrain.Boulder) {
					// Tell them that they can't move into a boulder:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You can't move into a Boulder, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}
				// Trying to move into a Unit, return false.
				else if (board[cellWithUnit.getLocation().x + 1][cellWithUnit
						.getLocation().y].hasUnit() == true) {
					// Tell them that they can't move into a Unit:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You can't move into another Unit, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}

			} else if (direction.equals("L")) {
				// Can't move farther left:
				if (cellWithUnit.getLocation().y == 0) {
					// Tell them that they can't move off of the map:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("What are you trying to do? Move off the Map?, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y - 1].getTerrain() == Terrain.Boulder) {
					// Tell them that they can't move into a boulder:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You can't move into a Boulder, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}
				// Trying to move into a Unit, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y - 1].hasUnit() == true) {
					// Tell them that they can't move into a Unit:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You can't move into another Unit, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}

			} else if (direction.equals("R")) {
				// Can't move farther right:
				if (cellWithUnit.getLocation().y == 19) {
					// Tell them that they can't move off of the map:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("What are you trying to do? Move off the Map?, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					return false;
				}
				// Trying to move into a Boulder, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y + 1].getTerrain() == Terrain.Boulder) {
					// Tell them that they can't move into a boulder:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You can't move into a Boulder, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}
				// Trying to move into a Unit, return false.
				else if (board[cellWithUnit.getLocation().x][cellWithUnit
						.getLocation().y + 1].hasUnit() == true) {
					// Tell them that they can't move into a Unit:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You can't move into another Unit, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					result = false;
				}
			}
		}
		return result;
	}

	/**
	 * This method moves the unit to a new cell, and returns the new cell with
	 * the unit in it It check for terrain that reduces health or movement, or
	 * both
	 * 
	 * @param cellWithUnit
	 *            This is given to me from the GUI contains a unit
	 * @param direction
	 *            Direction of movement desired
	 * @return Returns the new cell with the unit in it
	 */
	public Cell move(Cell cellWithUnit, String direction) {
		
		if (cellWithUnit.getUnit().toString().equals("Rancor") || cellWithUnit.getUnit().toString().equals("Wampa")) {
			if (direction.equals("N")) {
				// Reduce movement by 1:
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setMovesLeft(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y]
												.getUnit()
												.getMovesLeft() - 1);

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
			}
			else if (direction.equals("S")) { 
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
			}
			else if (direction.equals("L")) {
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
			}
			else {
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
		else {
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

					// Tell the user of their foolish actions:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("Your Unit "
									+ board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit().toString()
									+ " has lost 2 heath, and 2 movement due to lava, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);

					// Check see if unit has died:
					if (board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].getUnit().getHealth() <= 0) {
						// Unit has died:
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].getUnit().setMovesLeft(0);
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].getUnit().setCanAttack(false);
						// Check which player owned the unit:
						optionPane.setMessage("Your Unit "
								+ board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit().toString()
								+ " Has Died!");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
						// Will always be player1Units since this is in the move
						// method:
						player1Units
								.remove(board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y]);
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].removeUnit();
						return board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y];

					}

				}
				// Quick-sand reduces moves by 4:
				if (t == Terrain.QuickSand) {
					board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 4);

					// Tell the user of their foolish actions:
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Your Unit "
							+ board[cellWithUnit.getLocation().x][cellWithUnit
									.getLocation().y].getUnit().toString()
							+ " has 4 movement due to Quicksand, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				// Ice moves the user according to the slipping principle:
				if (t == Terrain.Ice) {
					return iceMoveNorth(cellWithUnit);
				}
				// Reduce movement by 1:
				board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
						.getUnit()
						.setMovesLeft(
								board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y]
												.getUnit()
												.getMovesLeft() - 1);

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
					// Tell the user of their foolish actions:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("Your Unit "
									+ board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit().toString()
									+ " has lost 2 heath, and 2 movement due to lava, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);

					// Check see if unit has died:
					if (board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].getUnit().getHealth() <= 0) {
						// Unit has died:
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].getUnit().setMovesLeft(0);
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].getUnit().setCanAttack(false);
						// Check which player owned the unit:
						optionPane.setMessage("Your Unit "
								+ board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit().toString()
								+ " Has Died!");

						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
						// Will always be player1Units since this is in the move
						// method:
						player1Units
								.remove(board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y]);
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].removeUnit();
						return board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y];

					}
				}
				// Quick-sand reduces moves by 4:
				if (t == Terrain.QuickSand) {
					board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 4);
					// Tell the user of their foolish actions:
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Your Unit "
							+ board[cellWithUnit.getLocation().x][cellWithUnit
									.getLocation().y].getUnit().toString()
							+ " has 4 movement due to Quicksand, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				// Ice moves the user according to the slipping principle:
				if (t == Terrain.Ice) {
					return iceMoveSouth(cellWithUnit);
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
					// Tell the user of their foolish actions:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("Your Unit "
									+ board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit().toString()
									+ " has lost 2 heath, and 2 movement due to lava, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);

					// Check see if unit has died:
					if (board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].getUnit().getHealth() <= 0) {
						// Unit has died:
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].getUnit().setMovesLeft(0);
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].getUnit().setCanAttack(false);
						// Check which player owned the unit:
						optionPane.setMessage("Your Unit "
								+ board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit().toString()
								+ " Has Died!");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
						// Will always be player1Units since this is in the move
						// method:
						player1Units
								.remove(board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y]);
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].removeUnit();
						return board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y];

					}
				}
				// Quick-sand reduces moves by 4:
				if (t == Terrain.QuickSand) {
					board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 4);
					// Tell the user of their foolish actions:
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Your Unit "
							+ board[cellWithUnit.getLocation().x][cellWithUnit
									.getLocation().y].getUnit().toString()
							+ " has 4 movement due to Quicksand, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				// Ice moves the user according to the slipping principle:
				if (t == Terrain.Ice) {
					return iceMoveLeft(cellWithUnit);
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
					// Tell the user of their foolish actions:
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("Your Unit "
									+ board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit().toString()
									+ " has lost 2 heath, and 2 movement due to lava, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);

					// Check see if unit has died:
					if (board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].getUnit().getHealth() <= 0) {
						// Unit has died:
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].getUnit().setMovesLeft(0);
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].getUnit().setCanAttack(false);
						// Check which player owned the unit:
						optionPane.setMessage("Your Unit "
								+ board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y].getUnit().toString()
								+ " Has Died!");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
						// Will always be player1Units since this is in the move
						// method:
						player1Units
								.remove(board[cellWithUnit.getLocation().x][cellWithUnit
										.getLocation().y]);
						board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y].removeUnit();
						return board[cellWithUnit.getLocation().x][cellWithUnit
								.getLocation().y];

					}
				}
				// Quick-sand reduces moves by 4:
				if (t == Terrain.QuickSand) {
					board[cellWithUnit.getLocation().x][cellWithUnit.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 4);
					// Tell the user of their foolish actions:
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Your Unit "
							+ board[cellWithUnit.getLocation().x][cellWithUnit
									.getLocation().y].getUnit().toString()
							+ " has 4 movement due to Quicksand, fool");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				// Ice for now does nothing but tell the user its cold here:
				if (t == Terrain.Ice) {
					return iceMoveRight(cellWithUnit);
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


	}

	/**
	 * Helper for moving through ice going north:
	 * 
	 * @param cellWithUnit
	 *            Cell containing the Unit moving through the ice
	 * @param direction
	 *            String representing the direction of movement desired
	 * @return Returns the new cell with the unit in it
	 */
	public Cell iceMoveNorth(Cell cellWithUnit) {
		for (int i = cellWithUnit.getLocation().x - 1; i >= 0; i--) {
			// If we have moved all the way to the top of the board:
			if (i == 0) {
				// Check for Boulder:
				if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.Boulder
						|| board[i][cellWithUnit.getLocation().y].hasUnit() == true) {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to location just below the Boulder:
					board[i + 1][cellWithUnit.getLocation().y]
							.setUnit(cellWithUnit.getUnit());
					board[i + 1][cellWithUnit.getLocation().y].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// return the cell the unit moved to:
					return board[i + 1][cellWithUnit.getLocation().y];
				} else {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to this location
					board[i][cellWithUnit.getLocation().y].setUnit(cellWithUnit
							.getUnit());
					board[i][cellWithUnit.getLocation().y].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// See what kind of Terrain is at this location:
					// Quicksand case:
					if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.QuickSand) {
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setMovesLeft(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getMovesLeft() - 4);
						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Your Unit "
								+ board[i][cellWithUnit.getLocation().y]
										.getUnit().toString()
								+ " has 4 movement due to Quicksand, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					// Lava Case:
					if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.Lava) {
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setMovesLeft(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getMovesLeft() - 2);
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setHealth(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getHealth() - 2);

						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane
								.setMessage("Your Unit "
										+ board[i][cellWithUnit.getLocation().y]
												.getUnit().toString()
										+ " has lost 2 heath, and 2 movement due to lava, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);

						// Check see if unit has died:
						if (board[i][cellWithUnit.getLocation().y].getUnit()
								.getHealth() <= 0) {
							// Unit has died:
							board[i][cellWithUnit.getLocation().y].getUnit()
									.setMovesLeft(0);
							board[i][cellWithUnit.getLocation().y].getUnit()
									.setCanAttack(false);
							// Check which player owned the unit:
							optionPane.setMessage("Your Unit "
									+ board[i][cellWithUnit.getLocation().y]
											.getUnit().toString()
									+ " Has Died!");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
							// Will always be player1Units since this is in the
							// move
							// method:
							player1Units.remove(board[i][cellWithUnit
									.getLocation().y]);
							board[i][cellWithUnit.getLocation().y].removeUnit();
							return board[i][cellWithUnit.getLocation().y];

						}

					}
					// return the cell the unit moved to:
					return board[i][cellWithUnit.getLocation().y];
				}
			}
			// All other cases i !=0:
			if (board[i][cellWithUnit.getLocation().y].getTerrain() != Terrain.Ice
					|| board[i][cellWithUnit.getLocation().y].hasUnit() == true) {
				// Check for Boulder:
				if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.Boulder
						|| board[i][cellWithUnit.getLocation().y].hasUnit() == true) {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to location just below the Boulder:
					board[i + 1][cellWithUnit.getLocation().y]
							.setUnit(cellWithUnit.getUnit());
					board[i + 1][cellWithUnit.getLocation().y].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// return the cell the unit moved to:
					return board[i + 1][cellWithUnit.getLocation().y];
				} else {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to this location
					board[i][cellWithUnit.getLocation().y].setUnit(cellWithUnit
							.getUnit());
					board[i][cellWithUnit.getLocation().y].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// See what kind of Terrain is at this location:
					// Quicksand case:
					if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.QuickSand) {
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setMovesLeft(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getMovesLeft() - 4);
						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Your Unit "
								+ board[i][cellWithUnit.getLocation().y]
										.getUnit().toString()
								+ " has 4 movement due to Quicksand, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					// Lava Case:
					if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.Lava) {
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setMovesLeft(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getMovesLeft() - 2);
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setHealth(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getHealth() - 2);

						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane
								.setMessage("Your Unit "
										+ board[i][cellWithUnit.getLocation().y]
												.getUnit().toString()
										+ " has lost 2 heath, and 2 movement due to lava, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);

						// Check see if unit has died:
						if (board[i][cellWithUnit.getLocation().y].getUnit()
								.getHealth() <= 0) {
							// Unit has died:
							board[i][cellWithUnit.getLocation().y].getUnit()
									.setMovesLeft(0);
							board[i][cellWithUnit.getLocation().y].getUnit()
									.setCanAttack(false);
							// Check which player owned the unit:
							optionPane.setMessage("Your Unit "
									+ board[i][cellWithUnit.getLocation().y]
											.getUnit().toString()
									+ " Has Died!");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
							// Will always be player1Units since this is in the
							// move
							// method:
							player1Units.remove(board[i][cellWithUnit
									.getLocation().y]);
							board[i][cellWithUnit.getLocation().y].removeUnit();
							return board[i][cellWithUnit.getLocation().y];

						}

					}
					// return the cell the unit moved to:
					return board[i][cellWithUnit.getLocation().y];
				}
			}
		}
		// Should never make it here!
		return cellWithUnit;
	}

	/**
	 * Helper for moving through ice going south:
	 * 
	 * @param cellWithUnit
	 *            Cell containing the Unit moving through the ice
	 * @param direction
	 *            String representing the direction of movement desired
	 * @return Returns the new cell with the unit in it
	 */
	public Cell iceMoveSouth(Cell cellWithUnit) {
		for (int i = cellWithUnit.getLocation().x + 1; i < 20; i++) {
			// If we have moved all the way to the bottom of the board:
			if (i == 19) {
				// Check for Boulder:
				if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.Boulder
						|| board[i][cellWithUnit.getLocation().y].hasUnit() == true) {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to location just above the Boulder:
					board[i - 1][cellWithUnit.getLocation().y]
							.setUnit(cellWithUnit.getUnit());
					board[i - 1][cellWithUnit.getLocation().y].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// return the cell the unit moved to:
					return board[i - 1][cellWithUnit.getLocation().y];
				} else {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to this location
					board[i][cellWithUnit.getLocation().y].setUnit(cellWithUnit
							.getUnit());
					board[i][cellWithUnit.getLocation().y].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// See what kind of Terrain is at this location:
					// Quicksand case:
					if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.QuickSand) {
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setMovesLeft(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getMovesLeft() - 4);
						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Your Unit "
								+ board[i][cellWithUnit.getLocation().y]
										.getUnit().toString()
								+ " has 4 movement due to Quicksand, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					// Lava Case:
					if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.Lava) {
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setMovesLeft(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getMovesLeft() - 2);
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setHealth(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getHealth() - 2);

						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane
								.setMessage("Your Unit "
										+ board[i][cellWithUnit.getLocation().y]
												.getUnit().toString()
										+ " has lost 2 heath, and 2 movement due to lava, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);

						// Check see if unit has died:
						if (board[i][cellWithUnit.getLocation().y].getUnit()
								.getHealth() <= 0) {
							// Unit has died:
							board[i][cellWithUnit.getLocation().y].getUnit()
									.setMovesLeft(0);
							board[i][cellWithUnit.getLocation().y].getUnit()
									.setCanAttack(false);
							// Check which player owned the unit:
							optionPane.setMessage("Your Unit "
									+ board[i][cellWithUnit.getLocation().y]
											.getUnit().toString()
									+ " Has Died!");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
							// Will always be player1Units since this is in the
							// move
							// method:
							player1Units.remove(board[i][cellWithUnit
									.getLocation().y]);
							board[i][cellWithUnit.getLocation().y].removeUnit();
							return board[i][cellWithUnit.getLocation().y];

						}

					}
					// return the cell the unit moved to:
					return board[i][cellWithUnit.getLocation().y];
				}
			}
			// All other cases i !=0:
			if (board[i][cellWithUnit.getLocation().y].getTerrain() != Terrain.Ice
					|| board[i][cellWithUnit.getLocation().y].hasUnit() == true) {
				// Check for Boulder:
				if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.Boulder
						|| board[i][cellWithUnit.getLocation().y].hasUnit() == true) {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to location just below the Boulder:
					board[i - 1][cellWithUnit.getLocation().y]
							.setUnit(cellWithUnit.getUnit());
					board[i - 1][cellWithUnit.getLocation().y].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// return the cell the unit moved to:
					return board[i - 1][cellWithUnit.getLocation().y];
				} else {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to this location
					board[i][cellWithUnit.getLocation().y].setUnit(cellWithUnit
							.getUnit());
					board[i][cellWithUnit.getLocation().y].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// See what kind of Terrain is at this location:
					// Quicksand case:
					if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.QuickSand) {
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setMovesLeft(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getMovesLeft() - 4);
						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Your Unit "
								+ board[i][cellWithUnit.getLocation().y]
										.getUnit().toString()
								+ " has 4 movement due to Quicksand, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					// Lava Case:
					if (board[i][cellWithUnit.getLocation().y].getTerrain() == Terrain.Lava) {
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setMovesLeft(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getMovesLeft() - 2);
						board[i][cellWithUnit.getLocation().y].getUnit()
								.setHealth(
										board[i][cellWithUnit.getLocation().y]
												.getUnit().getHealth() - 2);

						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane
								.setMessage("Your Unit "
										+ board[i][cellWithUnit.getLocation().y]
												.getUnit().toString()
										+ " has lost 2 heath, and 2 movement due to lava, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);

						// Check see if unit has died:
						if (board[i][cellWithUnit.getLocation().y].getUnit()
								.getHealth() <= 0) {
							// Unit has died:
							board[i][cellWithUnit.getLocation().y].getUnit()
									.setMovesLeft(0);
							board[i][cellWithUnit.getLocation().y].getUnit()
									.setCanAttack(false);
							// Check which player owned the unit:
							optionPane.setMessage("Your Unit "
									+ board[i][cellWithUnit.getLocation().y]
											.getUnit().toString()
									+ " Has Died!");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
							// Will always be player1Units since this is in the
							// move
							// method:
							player1Units.remove(board[i][cellWithUnit
									.getLocation().y]);
							board[i][cellWithUnit.getLocation().y].removeUnit();
							return board[i][cellWithUnit.getLocation().y];

						}

					}
					// return the cell the unit moved to:
					return board[i][cellWithUnit.getLocation().y];
				}
			}
		}
		// Should never make it here!
		return cellWithUnit;
	}

	/**
	 * Helper for moving through ice going right:
	 * 
	 * @param cellWithUnit
	 *            Cell containing the Unit moving through the ice
	 * @param direction
	 *            String representing the direction of movement desired
	 * @return Returns the new cell with the unit in it
	 */
	public Cell iceMoveRight(Cell cellWithUnit) {
		for (int i = cellWithUnit.getLocation().y + 1; i < 20; i++) {
			// If we have moved all the way to the right of the board:
			if (i == 19) {
				// Check for Boulder:
				if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.Boulder
						|| board[cellWithUnit.getLocation().x][i].hasUnit() == true) {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to location just left of the Boulder:
					board[cellWithUnit.getLocation().x][i - 1]
							.setUnit(cellWithUnit.getUnit());
					board[cellWithUnit.getLocation().x][i - 1].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// return the cell the unit moved to:
					return board[cellWithUnit.getLocation().x][i - 1];
				} else {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to this location
					board[cellWithUnit.getLocation().x][i].setUnit(cellWithUnit
							.getUnit());
					board[cellWithUnit.getLocation().x][i].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// See what kind of Terrain is at this location:
					// Quicksand case:
					if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.QuickSand) {
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setMovesLeft(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getMovesLeft() - 4);
						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Your Unit "
								+ board[cellWithUnit.getLocation().x][i]
										.getUnit().toString()
								+ " has 4 movement due to Quicksand, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					// Lava Case:
					if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.Lava) {
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setMovesLeft(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getMovesLeft() - 2);
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setHealth(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getHealth() - 2);

						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane
								.setMessage("Your Unit "
										+ board[cellWithUnit.getLocation().x][i]
												.getUnit().toString()
										+ " has lost 2 heath, and 2 movement due to lava, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);

						// Check see if unit has died:
						if (board[cellWithUnit.getLocation().x][i].getUnit()
								.getHealth() <= 0) {
							// Unit has died:
							board[cellWithUnit.getLocation().x][i].getUnit()
									.setMovesLeft(0);
							board[cellWithUnit.getLocation().x][i].getUnit()
									.setCanAttack(false);
							// Check which player owned the unit:
							optionPane.setMessage("Your Unit "
									+ board[cellWithUnit.getLocation().x][i]
											.getUnit().toString()
									+ " Has Died!");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
							// Will always be player1Units since this is in the
							// move
							// method:
							player1Units.remove(board[cellWithUnit
									.getLocation().x][i]);
							board[cellWithUnit.getLocation().x][i].removeUnit();
							return board[cellWithUnit.getLocation().x][i];

						}

					}
					// return the cell the unit moved to:
					return board[cellWithUnit.getLocation().x][i];
				}
			}
			// All other cases i != 19:
			if (board[cellWithUnit.getLocation().x][i].getTerrain() != Terrain.Ice
					|| board[cellWithUnit.getLocation().x][i].hasUnit() == true) {
				// Check for Boulder: HERE!!!!!!!
				if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.Boulder
						|| board[cellWithUnit.getLocation().x][i].hasUnit() == true) {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to location just left of the Boulder:
					board[cellWithUnit.getLocation().x][i - 1]
							.setUnit(cellWithUnit.getUnit());
					board[cellWithUnit.getLocation().x][i - 1].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// return the cell the unit moved to:
					return board[cellWithUnit.getLocation().x][i - 1];
				}

				else {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to this location
					board[cellWithUnit.getLocation().x][i].setUnit(cellWithUnit
							.getUnit());
					board[cellWithUnit.getLocation().x][i].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// See what kind of Terrain is at this location:
					// Quicksand case:
					if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.QuickSand) {
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setMovesLeft(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getMovesLeft() - 4);
						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Your Unit "
								+ board[cellWithUnit.getLocation().x][i]
										.getUnit().toString()
								+ " has 4 movement due to Quicksand, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					// Lava Case:
					if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.Lava) {
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setMovesLeft(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getMovesLeft() - 2);
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setHealth(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getHealth() - 2);

						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane
								.setMessage("Your Unit "
										+ board[cellWithUnit.getLocation().x][i]
												.getUnit().toString()
										+ " has lost 2 heath, and 2 movement due to lava, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);

						// Check see if unit has died:
						if (board[cellWithUnit.getLocation().x][i].getUnit()
								.getHealth() <= 0) {
							// Unit has died:
							board[cellWithUnit.getLocation().x][i].getUnit()
									.setMovesLeft(0);
							board[cellWithUnit.getLocation().x][i].getUnit()
									.setCanAttack(false);
							// Check which player owned the unit:
							optionPane.setMessage("Your Unit "
									+ board[cellWithUnit.getLocation().x][i]
											.getUnit().toString()
									+ " Has Died!");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
							// Will always be player1Units since this is in the
							// move
							// method:
							player1Units.remove(board[cellWithUnit
									.getLocation().x][i]);
							board[cellWithUnit.getLocation().x][i].removeUnit();
							return board[cellWithUnit.getLocation().x][i];

						}

					}
					// return the cell the unit moved to:
					return board[cellWithUnit.getLocation().x][i];
				}
			}
		}
		// Should never make it here!
		return cellWithUnit;
	}

	/**
	 * Helper for moving through ice going left:
	 * 
	 * @param cellWithUnit
	 *            Cell containing the Unit moving through the ice
	 * @param direction
	 *            String representing the direction of movement desired
	 * @return Returns the new cell with the unit in it
	 */
	public Cell iceMoveLeft(Cell cellWithUnit) {
		for (int i = cellWithUnit.getLocation().y - 1; i >= 0; i--) {
			// If we have moved all the way to the left of the board:
			if (i == 0) {
				// Check for Boulder:
				if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.Boulder
						|| board[cellWithUnit.getLocation().x][i].hasUnit() == true) {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to location just right of the Boulder:
					board[cellWithUnit.getLocation().x][i + 1]
							.setUnit(cellWithUnit.getUnit());
					board[cellWithUnit.getLocation().x][i + 1].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// return the cell the unit moved to:
					return board[cellWithUnit.getLocation().x][i + 1];
				} else {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to this location
					board[cellWithUnit.getLocation().x][i].setUnit(cellWithUnit
							.getUnit());
					board[cellWithUnit.getLocation().x][i].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// See what kind of Terrain is at this location:
					// Quicksand case:
					if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.QuickSand) {
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setMovesLeft(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getMovesLeft() - 4);
						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Your Unit "
								+ board[cellWithUnit.getLocation().x][i]
										.getUnit().toString()
								+ " has 4 movement due to Quicksand, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					// Lava Case:
					if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.Lava) {
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setMovesLeft(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getMovesLeft() - 2);
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setHealth(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getHealth() - 2);

						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane
								.setMessage("Your Unit "
										+ board[cellWithUnit.getLocation().x][i]
												.getUnit().toString()
										+ " has lost 2 heath, and 2 movement due to lava, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);

						// Check see if unit has died:
						if (board[cellWithUnit.getLocation().x][i].getUnit()
								.getHealth() <= 0) {
							// Unit has died:
							board[cellWithUnit.getLocation().x][i].getUnit()
									.setMovesLeft(0);
							board[cellWithUnit.getLocation().x][i].getUnit()
									.setCanAttack(false);
							// Check which player owned the unit:
							optionPane.setMessage("Your Unit "
									+ board[cellWithUnit.getLocation().x][i]
											.getUnit().toString()
									+ " Has Died!");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
							// Will always be player1Units since this is in the
							// move
							// method:
							player1Units.remove(board[cellWithUnit
									.getLocation().x][i]);
							board[cellWithUnit.getLocation().x][i].removeUnit();
							return board[cellWithUnit.getLocation().x][i];

						}

					}
					// return the cell the unit moved to:
					return board[cellWithUnit.getLocation().x][i];
				}
			}
			// All other cases i !=0:
			if (board[cellWithUnit.getLocation().x][i].getTerrain() != Terrain.Ice
					|| board[cellWithUnit.getLocation().x][i].hasUnit() == true) {
				// Check for Boulder:
				if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.Boulder
						|| board[cellWithUnit.getLocation().x][i].hasUnit() == true) {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to location just right of the Boulder:
					board[cellWithUnit.getLocation().x][i + 1]
							.setUnit(cellWithUnit.getUnit());
					board[cellWithUnit.getLocation().x][i + 1].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// return the cell the unit moved to:
					return board[cellWithUnit.getLocation().x][i + 1];
				} else {
					// Reduce movement by 1:
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y]
							.getUnit()
							.setMovesLeft(
									board[cellWithUnit.getLocation().x][cellWithUnit
											.getLocation().y].getUnit()
											.getMovesLeft() - 1);
					// Add unit to this location
					board[cellWithUnit.getLocation().x][i].setUnit(cellWithUnit
							.getUnit());
					board[cellWithUnit.getLocation().x][i].setHasUnit(true);
					// Remove Unit from old cell
					board[cellWithUnit.getLocation().x][cellWithUnit
							.getLocation().y].removeUnit();
					// See what kind of Terrain is at this location:
					// Quicksand case:
					if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.QuickSand) {
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setMovesLeft(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getMovesLeft() - 4);
						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Your Unit "
								+ board[cellWithUnit.getLocation().x][i]
										.getUnit().toString()
								+ " has 4 movement due to Quicksand, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					// Lava Case:
					if (board[cellWithUnit.getLocation().x][i].getTerrain() == Terrain.Lava) {
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setMovesLeft(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getMovesLeft() - 2);
						board[cellWithUnit.getLocation().x][i].getUnit()
								.setHealth(
										board[cellWithUnit.getLocation().x][i]
												.getUnit().getHealth() - 2);

						// Tell the user of their foolish actions:
						JOptionPane optionPane = new JOptionPane();
						optionPane
								.setMessage("Your Unit "
										+ board[cellWithUnit.getLocation().x][i]
												.getUnit().toString()
										+ " has lost 2 heath, and 2 movement due to lava, fool");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);

						// Check see if unit has died:
						if (board[cellWithUnit.getLocation().x][i].getUnit()
								.getHealth() <= 0) {
							// Unit has died:
							board[cellWithUnit.getLocation().x][i].getUnit()
									.setMovesLeft(0);
							board[cellWithUnit.getLocation().x][i].getUnit()
									.setCanAttack(false);
							// Check which player owned the unit:
							optionPane.setMessage("Your Unit "
									+ board[cellWithUnit.getLocation().x][i]
											.getUnit().toString()
									+ " Has Died!");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
							// Will always be player1Units since this is in the
							// move
							// method:
							player1Units.remove(board[cellWithUnit
									.getLocation().x][i]);
							board[cellWithUnit.getLocation().x][i].removeUnit();
							return board[cellWithUnit.getLocation().x][i];

						}

					}
					// return the cell the unit moved to:
					return board[cellWithUnit.getLocation().x][i];
				}
			}
		}
		// Should never make it here!
		return cellWithUnit;
	}

	/**
	 * Get player1's list of cells that contain units:
	 * 
	 * @return Returns the list of player1Units:
	 */
	public ArrayList<Cell> getPlayer1Units() {
		return player1Units;
	}

	/**
	 * Get player2's list of cells that contain units:
	 * 
	 * @return Returns the list of player2Units
	 */
	public ArrayList<Cell> getPlayer2Units() {
		return player2Units;
	}

	/**
	 * This method returns a list of cells with units in it, that can be
	 * attacked by a cell containing a unit given to it
	 * 
	 * @param cellWithUnit
	 *            The cell containing the unit given to this method
	 * @return Returns an ArrayList<Cell> that contains all Cells that can be
	 *         attacked by this unit
	 */
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

		// Check for Medic:
		if (cellWithUnit.getUnit().toString().equals("Imperial Medic")) {
			for (int i = startRow; i <= finishRow; i++) {
				for (int j = startCol; j <= finishCol; j++) {
					// Have located a unit in the range of the unit given:
					if (board[i][j].hasUnit()) {
						// Make sure it is not the unit in cellWithUnit:
						if (i == cellWithUnit.getLocation().x
								&& j == cellWithUnit.getLocation().y) {
							// Do Nothing!
						}
						// See if this unit is an ALLY!:
						else if (board[i][j].getUnit().getUsername() == cellWithUnit
								.getUnit().getUsername())
							unitsInRange.add(board[i][j]);
					}
				}
			}
		}

		// else its every other type of unit:
		else {
			for (int i = startRow; i <= finishRow; i++) {
				for (int j = startCol; j <= finishCol; j++) {
					// Have located a unit in the range of the unit given:
					if (board[i][j].hasUnit()) {
						// Make sure it is not the unit in cellWithUnit:
						if (i == cellWithUnit.getLocation().x
								&& j == cellWithUnit.getLocation().y) {
							// Do Nothing!
						}
						// See if this unit is an enemy:
						else if (board[i][j].getUnit().getUsername() != cellWithUnit
								.getUnit().getUsername())
							unitsInRange.add(board[i][j]);

					}
				}
			}
		}
		for (int i = 0; i < unitsInRange.size(); i++) {
		}
		return unitsInRange;
	}

	/**
	 * Gets the boolean can attack from the Cell class
	 * 
	 * @param cell
	 * @return true is cell CanAttack, false else
	 */
	public boolean CanAttack(Cell cell) {
		if (cell.getUnit().getCanAttack()) {
			return true;
		}

		return false;

	}

	/**
	 * This method uses the item selected on the unit in this cell, and returns
	 * the cell with the updated unit:
	 * 
	 * @param item
	 *            The Item to be used
	 * @param cell
	 *            The cell containing the unit the item is being used on
	 * @return Returns the new cell with the updated unit information
	 */
	public Cell useItem(Item item, Cell cell) {
		
			cell.getUnit().setAttackRange(
					cell.getUnit().getAttackRange() + item.getModifiers()[2]);
			cell.getUnit().setDamage(
					cell.getUnit().getDamage() + item.getModifiers()[1]);
			cell.getUnit().setHealth(
					cell.getUnit().getHealth() + item.getModifiers()[0]);
			cell.getUnit().setMoveRange(
					cell.getUnit().getMoveRange() + item.getModifiers()[3]);
			cell.getUnit().setMovesLeft(
					cell.getUnit().getMovesLeft() + item.getModifiers()[3]);
		

		return cell;
	}

	/**
	 * Attack method for two cells containing units being given: returns the
	 * cell of unitBeingAttacked to the GUI, that updates the unit info in that
	 * cell:
	 * 
	 * @param cellWithUnitAtacking
	 *            The cell containing the unit that is attacking
	 * @param cellWithUnitBeingAttacked
	 *            The cell containing the unit to be attacked
	 * @return Returns cellWithUnitBeingAttacked with updated information about
	 *         the unit in it
	 */
	public Cell attack(Cell cellWithUnitAtacking, Cell cellWithUnitBeingAttacked) {

		// Check for Medic:
		if (cellWithUnitAtacking.getUnit().toString().equals("Imperial Medic")) {
			
			// Check for if Medic tries to heal a fully healed unit:
			if (cellWithUnitBeingAttacked.getUnit().getHealth()==cellWithUnitBeingAttacked.getUnit().getCurrentHealth()) {
				JOptionPane optionPane = new JOptionPane();
				optionPane.setMessage(cellWithUnitBeingAttacked.getUnit().toString() + " has full health already!");
				JDialog dialog = optionPane.createDialog(":)");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				return cellWithUnitBeingAttacked;
			}
			
			cellWithUnitBeingAttacked.getUnit().setHealth(
					cellWithUnitBeingAttacked.getUnit().getHealth()
							+ cellWithUnitAtacking.getUnit().getDamage());
			// Gave too much health:
			if (cellWithUnitBeingAttacked.getUnit().getHealth() > cellWithUnitBeingAttacked.getUnit().getCurrentHealth()) {
				cellWithUnitBeingAttacked.getUnit().setHealth(cellWithUnitBeingAttacked.getUnit().getCurrentHealth());

				JOptionPane optionPane = new JOptionPane();
				optionPane.setMessage("Your Medic Fully Healed "
						+ cellWithUnitBeingAttacked.getUnit().toString());
				JDialog dialog = optionPane.createDialog(":)");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				cellWithUnitAtacking.getUnit().setMovesLeft(0);
				cellWithUnitAtacking.getUnit().setCanAttack(false);
				return cellWithUnitBeingAttacked;
			}
			else {
				cellWithUnitAtacking.getUnit().setMovesLeft(0);
				cellWithUnitAtacking.getUnit().setCanAttack(false);
				JOptionPane optionPane = new JOptionPane();
				optionPane.setMessage("Your Medic Healed "
						+ cellWithUnitBeingAttacked.getUnit().toString() + " By "
						+ cellWithUnitAtacking.getUnit().getDamage()
						+ " Health!");
				JDialog dialog = optionPane.createDialog(":)");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				return cellWithUnitBeingAttacked;
			}
			
		}

		// Any other Unit:
		else {
			cellWithUnitBeingAttacked.getUnit().setHealth(
					cellWithUnitBeingAttacked.getUnit().getHealth()
							- cellWithUnitAtacking.getUnit().getDamage());
			cellWithUnitAtacking.getUnit().setMovesLeft(0);
			cellWithUnitAtacking.getUnit().setCanAttack(false);
			// unitBeingAttacked has died:
			if (cellWithUnitBeingAttacked.getUnit().getHealth() <= 0) {
				// remove this unit from whomever owns this unit:
				if (cellWithUnitBeingAttacked.getUnit().getUsername()
						.equals(GUI.getPlayer1())) {
					// If Player 1 owns this unit, remove it from player1Units
					// list:
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Your Unit "
							+ cellWithUnitBeingAttacked.getUnit().toString()
							+ " Has Died!");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					player1Units.remove(cellWithUnitBeingAttacked);
					

				}
				if (cellWithUnitBeingAttacked.getUnit().getUsername()
						.equals(GUI.getPlayer2())) {
					// If Player 2 owns this unit, remove it from player2Units
					// list:
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Enemy Unit "
							+ cellWithUnitBeingAttacked.getUnit().toString()
							+ " Has Died!");
					JDialog dialog = optionPane.createDialog(":~D");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					player2Units.remove(cellWithUnitBeingAttacked);
					
					// Deal with leveling up:
					// If you got a kill, your unit levels up for now
					cellWithUnitAtacking.getUnit().levelUp();
					// Tell the user (player1) about it:	
					optionPane = new JOptionPane();
					optionPane.setMessage("Your Unit "
							+ cellWithUnitAtacking.getUnit().toString()
							+ " Has Leveled up! Check unit info to see your improved stats!");
					dialog = optionPane.createDialog(":~D");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					
				}
				// Remove the unit from the Cell
				cellWithUnitBeingAttacked.removeUnit();

				// Return the new cell that now has no unit in it
				return cellWithUnitBeingAttacked;
			}
			// else return cellWithUnitBeingAttacked with updated info
			else {
				JOptionPane optionPane = new JOptionPane();
				optionPane.setMessage("Enemy Unit has "
						+ cellWithUnitBeingAttacked.getUnit().getHealth()
						+ " Health Left");
				JDialog dialog = optionPane.createDialog(":~D");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				return cellWithUnitBeingAttacked;
			}

		}
	}

	/**
	 * Checks to see if the game is over by looking at player1Units List, and
	 * player2Units List to see if either one of them is empty, this version
	 * returns a boolean, we dont know who lost:
	 * 
	 * @param units
	 *            If any ArrayList<Cell> given (assumed to be from a player)
	 *            isEmpty, game is over
	 * @return true is ArrayList<Cell> given is empty, then game is over, else
	 *         false
	 */
	public boolean CheckgameOverBooleanVersion(ArrayList<Cell> units) {
		
		if (units.isEmpty()) {
			// Player 1 has lost:
			return true;
		}
		
		return false;
	}
	
	/**
	 * When the turn is over, update movesLeft, and setCanAttack, and change
	 * units out:
	 * 
	 * @param player1units
	 *            Player1's Cell's with units list
	 * @param player2units
	 *            Player2's Cell's with units list
	 * @param player1username
	 *            Player1's userName
	 * @param player2username
	 *            Player2's userName
	 */
	public void turnOver2(ArrayList<Cell> player1units,
			ArrayList<Cell> player2units, String player1username,
			String player2username) {
		for (int i = 0; i < player1units.size(); i++) {
			player1units.get(i).getUnit()
					.setMovesLeft(player1Units.get(i).getUnit().getMoveRange());
			player1units.get(i).getUnit().setCanAttack(true);
		}
		player1Units = player2units;
		player2Units = player1units;
		GUI.player1 = player2username;
		GUI.player2 = player1username;
		GUI.CurrentUnitSelected = null;
		GUI.EnemyUnitSelected = null;
		

	}

	public void turnOverComputer(ArrayList<Cell> player1units,
			ArrayList<Cell> player2units, String player1username,
			String player2username) {

		GUI.endTurn.doClick();

		
	}

	/**
	 * Returns the unit in this cell, or null if there is no unit in this cell:
	 * 
	 * @param cell
	 *            Cell given to get the unit from
	 * @return null if there is no Unit in this cell, else returns the Unit in
	 *         the cell
	 */
	public Unit getUnit(Cell cell) {
		if (cell.hasUnit())
			return cell.getUnit();
		else
			return null;
	}

	public Unit getCurrentUnitSelected() {
		if (GUI.CurrentUnitSelected != null) {
			return GUI.CurrentUnitSelected.getUnit();

		}
		return null;
	}

	public Unit getEnemyUnitSelected() {
		if (GUI.EnemyUnitSelected != null) {
			return GUI.EnemyUnitSelected.getUnit();

		}
		return null;
	}

	// Getters/Setters:

	/**
	 * Getter for the board
	 * 
	 * @return the Cell[][] which represents the game
	 */
	public Cell[][] getBoard() {
		return board;
	}

	/**
	 * Setter for board
	 * 
	 * @param board
	 *            the new board to set Cell[][] to
	 */
	public void setBoard(Cell[][] board) {
		for(int i = 0; i < 20; i ++){
			for(int j = 0; j < 20; j++){
				this.board[i][j] = board[i][j];
			}
		}
	}

	/**
	 * Getter for a Cell at a specified location:
	 * 
	 * @param x
	 *            x-coordinate of needed cell
	 * @param y
	 *            y-coordinate of needed cell
	 * @return the cell at those coordinates:
	 */
	public Cell getCell(int x, int y) {
		return board[x][y];
	}

	

	/**
	 * This method attempts to save the inventory map in
	 * "./player 1-player 2-gameboard.dat"
	 */
	public void saveData(String p1, String p2) {
		try {
			FileOutputStream fileOut = new FileOutputStream(new File(saveDir + mapName + "-"
					+ p1 + "-" + p2 + "-" + "gameboard.dat"));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(board);
			objectOut.writeObject(player1Units);
			objectOut.writeObject(player2Units);
			objectOut.writeObject(GameBoard.background);
			objectOut.close();
		} catch (Exception e) {
			System.err.println("Error! Could not save data.");
		}
	}

	
}
