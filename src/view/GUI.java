package view;

import item.Inventory;
import model.AI;
import item.Item;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.Cell;
import model.GameBoard;

/**
 * Public class that everything that has to do with user action with the game.
 * 
 * @author JonDavid Ebadirad
 * 
 */
public class GUI extends JFrame {
	private static final long serialVersionUID = -2853985771911325020L;
	private static final String saveDir = System.getProperty("user.dir")
			+ File.separator + "gamesaves" + File.separator;

	public static String player1;
	public static String player2;

	public ArrayList<Cell> playerunits = new ArrayList<Cell>();
	AI computer = new AI();

	JFrame frame;
	JPanel shop;
	JPanel movePanel;
	JPanel listItems;
	JPanel shopNav;
	JPanel unitPanel;
	JPanel playerstatus;
	JPanel imagePanel;
	JPanel contentContainer;
	JPanel playerContainer;
	JPanel playerInfoContainer;
	JTabbedPane tabbedpane;
	JPanel listOfTargets;
	
	
	JLabel usernamestring, inventorystring, credits;
	
	JPanel inventoryPanel;
	JPanel attackButtonPanel;
	ButtonGroup unitgroup;
	ButtonGroup targetGroup;
	public static JButton moveUp;
	public static JButton moveDown;
	public static JButton moveLeft;
	public static JButton moveRight;
	public static JButton attack;
	public static JButton endTurn;
	public static JButton endTurnAI;
	JButton UseItem;
	JButton closeShop;
	JButton buyItem;
	JButton Shop;
	JButton UnitInfo;
	JButton Map2 = new JButton("Map 2");
	JButton RandomMap = new JButton("Random Map");
	JButton vsAI = new JButton("vs AI");
	ArrayList<JRadioButton> radiobuttons;
	ArrayList<JRadioButton> targetButtons;
	ArrayList<JCheckBox> itemBoxes;
	ArrayList<String> items;

	
	
	MapButtonListener MapButtonListener = new MapButtonListener();

	 Inventory shopitems = new Inventory("Shop");
	  ArrayList<JRadioButton> shopbuttons = null;
	  ButtonGroup shopgroup;
	  HashMap<String, Item> inv;
	
	// things to run the game
	public static GameBoard gameboard;
	public static Cell CurrentUnitSelected;
	public static Cell EnemyUnitSelected;
	private ArrayList<Cell> player1units;
	private ArrayList<Cell> player2units;
	private ArrayList<Cell> targets;
	private boolean unitdisplay = false;
	private boolean shopscreen = false;
	private boolean AIgame = false;

	// pregame lobby GUI items

	JPanel teamSelect;
	JButton Map;
	JLabel usernamelabel1;
	JLabel usernamelabel2;
	JTextField username1;
	JTextField username2;

	private JPanel AttackPanel;
	private Inventory p1inv, p2inv;
	public boolean loadGame;
	private JButton load;

	/**
	 * Simple Constructor
	 */
	public GUI() {
		super();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		layoutPregameGUI();
	}

	/**
	 * Main method that runs the whole program.
	 * 
	 * @param args
	 *            does nothing in this case.
	 */
	public static void main(String[] args) {

		new GUI().setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/**
	 * Officially creates a newgame().
	 */
	public void newGame(String map) {
		gameboard = new GameBoard(map);
		// create inventories for both players
		p1inv = new Inventory(player1);
		p2inv = new Inventory(player2);
		// both players start with a super item. WOW. how generous of us.
		p1inv.addItem(Item.superitem);
		System.out.println(player1 + "'s inventory: " + p1inv.toString());
		p2inv.addItem(Item.superitem);
		System.out.println(player2 + "'s inventory: " + p2inv.toString());
		player1units = gameboard.getPlayer1Units();
		player2units = gameboard.getPlayer2Units();
		CurrentUnitSelected = null;
		EnemyUnitSelected = null;
		targetButtons = new ArrayList();

	}

	private void targets(Cell cellWithUnit) {
		targets = gameboard.getUnitsInAttackRange(cellWithUnit);

	}

	@SuppressWarnings("unused")
	private void loginGUI() {
		player1 = JOptionPane.showInputDialog("Username");

	}

	private void UpdateItemScreen() {
		inventoryPanel.removeAll();
		itemBoxes = new ArrayList<JCheckBox>();
		items = new ArrayList<String>();
		Iterator it = p1inv.getInventory().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			itemBoxes.add(new JCheckBox(pairs.getKey().toString()));
		}

		for (int i = 0; i < itemBoxes.size(); i++) {
			itemBoxes.get(i).addItemListener(new itemListener());
			inventoryPanel.add(itemBoxes.get(i));

		}

		playerContainer.repaint();
		revalidate();

	}

	private void toggleShopScreen(){
	  if(shopscreen){
		  contentContainer.removeAll();
		  JPanel shopNavButtons = new JPanel();
		  JPanel Creditpanel = new JPanel();
		  credits = new JLabel("Credits: "+ p1inv.getCredits());
		  shop = new JPanel();
		  shop.setLayout(new GridLayout(1,2,0,0));
		  shopNav = new JPanel();
		  
		  shopNav.setLayout(new BorderLayout());
		  
		  shopNavButtons.setLayout(new GridLayout(2,3,0,0));
		  
		  shopNav.add(shopNavButtons, BorderLayout.NORTH);
		  shopNav.add(Creditpanel,BorderLayout.CENTER);
		  
		  closeShop = new JButton("Close Shop");
		  buyItem = new JButton("Buy");
		  JPanel blank = new JPanel();
		  JPanel blank2 = new JPanel();
		  JPanel blank3 = new JPanel();
		  shopNavButtons.add(blank);
		  shopNavButtons.add(buyItem);
		  shopNavButtons.add(closeShop);
		  shopNavButtons.add(blank2);
		  shopNavButtons.add(credits);
		  shopNavButtons.add(blank3);
		  listItems = new JPanel();
		  contentContainer.remove(imagePanel);
		  contentContainer.remove(playerContainer);
		  imagePanel.removeAll();
		  imagePanel = new Shop();
		  imagePanel.setPreferredSize(new Dimension(1280,500));
		  shopgroup = new ButtonGroup();
		  shopitems.addItem("Super Item");
		  shopitems.addItem("Hyper Potion");
		  shopitems.addItem("Apollo's Helm");
		  shopitems.addItem("Speed Enhancer");
		  
		  inv = shopitems.getInventory();
		  listItems.setLayout(new GridLayout(inv.size(), 1,0,0));
		  shopbuttons = new ArrayList<JRadioButton>();
		  for(String key:inv.keySet()){
			  
				 shopbuttons.add(new JRadioButton(key));
			  
			  
			  
		  }
		  for(JRadioButton element: shopbuttons){
			  shopgroup.add(element);
			  if(p1inv.hasItem(element.getText())){
				  listItems.add(new JLabel(element.getText()));
			  }
			  else{
				  listItems.add(element);
			  }
			 
			  element.addActionListener(new ButtonListener());
			  element.setActionCommand(element.getText());
		  }
		  
		  shop.add(listItems);
		  shop.add(shopNav);
		  contentContainer.add(imagePanel, BorderLayout.NORTH);
		  contentContainer.add(shop, BorderLayout.CENTER);
			closeShop.addActionListener(new ButtonListener());
			buyItem.addActionListener(new ButtonListener());
		  imagePanel.updateUI();
		  revalidate();
		  repaint();
	  }
	  else{
		  contentContainer.remove(imagePanel);
		  contentContainer.remove(shop);
		  imagePanel.removeAll();
		  imagePanel = new Imageview(GameBoard.background);
		  imagePanel.setPreferredSize(new Dimension(1280,500));
		  contentContainer.add(imagePanel,BorderLayout.NORTH);
		  contentContainer.add(playerContainer,BorderLayout.CENTER);
		  imagePanel.updateUI();
		  playerContainer.updateUI();
		  revalidate();
		  repaint();
	  }
  }

	private void toggleUnitScreen() {
		if (unitdisplay) {
			contentContainer.remove(imagePanel);
			imagePanel.removeAll();
			imagePanel = new Imageview(GameBoard.background);
			contentContainer.add(imagePanel);
			imagePanel.updateUI();
			revalidate();
			repaint();
		} else {
			contentContainer.remove(imagePanel);
			imagePanel.removeAll();
			imagePanel = new PlayerStatus();
			contentContainer.add(imagePanel);
			imagePanel.updateUI();
			revalidate();
			repaint();
		}

	}

	private void UpdateUnitScreen() {
		unitPanel.removeAll();
		unitgroup = new ButtonGroup();
		radiobuttons = new ArrayList<JRadioButton>();
		for (int i = 0; i < player1units.size(); i++) {

			radiobuttons.add(new JRadioButton(player1units.get(i).getUnit()
					.toString()));

		}
		for (int i = 0; i < radiobuttons.size(); i++) {
			unitgroup.add(radiobuttons.get(i));
			unitPanel.add(radiobuttons.get(i));
			radiobuttons.get(i).addActionListener(new ButtonListener());
		}
		movePanel.add(unitPanel, BorderLayout.CENTER);
		revalidate();
		movePanel.repaint();
	}

	private void layoutAttackScreen() {
		if (targets.size() <= 0) {
			EnemyUnitSelected = null;
		}

		targetButtons = new ArrayList();
		AttackPanel.remove(listOfTargets);
		listOfTargets = new JPanel();
		targetGroup = new ButtonGroup();
		for (int i = 0; i < targets.size(); i++) {
			int number = i + 1;
			targetButtons.add(new JRadioButton(targets.get(i).getUnit()
					.toString()));
		}
		for (int i = 0; i < targetButtons.size(); i++) {
			targetGroup.add(targetButtons.get(i));
			listOfTargets.add(targetButtons.get(i));
			targetButtons.get(i).addActionListener(new ButtonListener());
		}

		AttackPanel.add(listOfTargets, BorderLayout.CENTER);
		listOfTargets.setVisible(true);
		revalidate();
	}

	private void clearAttackScreen() {
		targetButtons = new ArrayList();
		AttackPanel.remove(listOfTargets);
		listOfTargets = new JPanel();
		targetGroup = new ButtonGroup();

		AttackPanel.add(listOfTargets, BorderLayout.CENTER);
		listOfTargets.setVisible(true);
		revalidate();
	}

	private void layoutPregameGUI() {

		frame.setSize(350, 400);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		teamSelect = new JPanel();
		teamSelect.setPreferredSize(new Dimension(800, 600));
		teamSelect.setLayout(new FlowLayout());
		usernamelabel1 = new JLabel("Team 1 username: ");
		username1 = new JTextField(15);
		usernamelabel2 = new JLabel("Team 2 username: ");
		username2 = new JTextField(15);
		teamSelect.add(usernamelabel1);
		teamSelect.add(username1);
		teamSelect.add(usernamelabel2);
		teamSelect.add(username2);
		vsAI.addActionListener(MapButtonListener);
		Map = new JButton("Map 1");
		Map.addActionListener(MapButtonListener);
		Map2.addActionListener(MapButtonListener);
		RandomMap.addActionListener(MapButtonListener);
		teamSelect.add(Map);
		teamSelect.add(Map2);
		teamSelect.add(RandomMap);
		teamSelect.add(vsAI);
		load = new JButton("Load game");
		load.addActionListener(MapButtonListener);
		teamSelect.add(load);
		// begin persistence code
		// if(new File(saveDir + player1 + "-" + player2 + "-" +
		// "gameboard.dat").exists()) {
		// JPanel loadSave = new JPanel();
		// JCheckBox chkLoad = new JCheckBox("Load saved game?");
		// chkLoad.addItemListener(new ItemListener() {
		// public void itemStateChanged(ItemEvent e) {
		// loadGame = e.getStateChange() == 1 ? true : false;
		// }
		// });
		// loadSave.add(chkLoad).setVisible(true);
		// teamSelect.add(loadSave);
		// }
		// end persistence code
		frame.add(teamSelect);

		frame.setVisible(true);

	}

	private void layoutGUI() {
		frame.removeAll();
		tabbedpane = new JTabbedPane();
		tabbedpane.setPreferredSize(new Dimension(1280, 500));

		contentContainer = new JPanel();
		contentContainer.setPreferredSize(new Dimension(1280, 500));
		contentContainer.setLayout(new BorderLayout());

		playerContainer = new JPanel();
		playerContainer.setLayout(new GridLayout(1, 3, 0, 0));
		playerContainer.setSize(1280, 300);
		playerInfoContainer = new JPanel();
		playerInfoContainer.setPreferredSize(new Dimension(100, 300));
		usernamestring = new JLabel("Current Player: " + player1);
		playerInfoContainer.add(usernamestring);
		inventorystring = new JLabel(player1 + "'s inventory: "
				+ p1inv.toString());
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 800);
		setResizable(false);
		setLayout(new BorderLayout());
		moveUp = new JButton("up");
		moveDown = new JButton("down");
		moveLeft = new JButton("left");
		moveRight = new JButton("right");
		moveUp.setPreferredSize(new Dimension(50, 100));
		moveDown.setPreferredSize(new Dimension(50, 50));
		moveLeft.setPreferredSize(new Dimension(75, 50));
		moveRight.setPreferredSize(new Dimension(75, 50));

		imagePanel = new Imageview(GameBoard.background);
		imagePanel.setPreferredSize(new Dimension(1280, 500));

		movePanel = new JPanel();
		movePanel.setPreferredSize(new Dimension(450, 150));
		movePanel.setLayout(new BorderLayout());

		JPanel DirectionPanel = new JPanel();
		DirectionPanel.setPreferredSize(new Dimension(250, 150));
		DirectionPanel.setLayout(new GridLayout(2, 3, 0, 0));
		JPanel blankPanel = new JPanel();
		JPanel blankpanel1 = new JPanel();
		JPanel blankPanel2 = new JPanel();
		DirectionPanel.add(blankPanel);
		DirectionPanel.add(moveUp);
		DirectionPanel.add(blankpanel1);
		DirectionPanel.add(moveLeft);
		DirectionPanel.add(moveDown);
		DirectionPanel.add(moveRight);

		unitPanel = new JPanel();
		unitPanel.setPreferredSize(new Dimension(200, 150));
		unitgroup = new ButtonGroup();
		radiobuttons = new ArrayList();
		for (int i = 0; i < player1units.size(); i++) {

			radiobuttons.add(new JRadioButton(player1units.get(i).getUnit()
					.toString()));

		}
		for (int i = 0; i < radiobuttons.size(); i++) {
			unitgroup.add(radiobuttons.get(i));
			unitPanel.add(radiobuttons.get(i));
			radiobuttons.get(i).addActionListener(new ButtonListener());
		}

		movePanel.add(DirectionPanel, BorderLayout.WEST);
		movePanel.add(unitPanel, BorderLayout.CENTER);

		AttackPanel = new JPanel();
		AttackPanel.setLayout(new BorderLayout());
		AttackPanel.setPreferredSize(new Dimension(450, 150));
		attackButtonPanel = new JPanel();
		attackButtonPanel.setLayout(new GridLayout(2, 3, 0, 0));
		listOfTargets = new JPanel();
		JPanel blank = new JPanel();
		UseItem = new JButton("Use Item");
		attack = new JButton("Attack");
		endTurn = new JButton("End turn");
		endTurnAI = new JButton("End turn");
		Shop = new JButton("Shop");
		UnitInfo = new JButton("Unit Info");
		attackButtonPanel.add(UseItem);
		attackButtonPanel.add(attack);
		if (AIgame) {
			attackButtonPanel.add(endTurnAI);
		} else {
			attackButtonPanel.add(endTurn);
		}

		attackButtonPanel.add(Shop);
		attackButtonPanel.add(UnitInfo);
		attackButtonPanel.add(blankPanel2);
		AttackPanel.add(attackButtonPanel, BorderLayout.NORTH);
		AttackPanel.add(listOfTargets, BorderLayout.CENTER);

		contentContainer.add(imagePanel, BorderLayout.NORTH);
		contentContainer.add(playerContainer, BorderLayout.CENTER);

		inventoryPanel = new JPanel();
		inventoryPanel.setPreferredSize(new Dimension(200, 150));
		itemBoxes = new ArrayList<JCheckBox>();
		items = new ArrayList<String>();
		Iterator it = p1inv.getInventory().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			itemBoxes.add(new JCheckBox(pairs.getKey().toString()));
		}

		for (int i = 0; i < itemBoxes.size(); i++) {
			itemBoxes.get(i).addItemListener(new itemListener());
			inventoryPanel.add(itemBoxes.get(i));

		}
		playerContainer.add(movePanel);
		// playerContainer.add(playerInfoContainer);
		playerContainer.add(inventoryPanel);
		playerContainer.add(AttackPanel);

		add(contentContainer);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		revalidate();
	}

	/**
	 * @return the username of the player that is set from the pregame lobby.
	 */
	public static String getPlayer1() {
		return player1;
	}

	/**
	 * @return the username of the player that is set from the pregame lobby.
	 */
	public static String getPlayer2() {
		return player2;
	}

	private void registerListeners() {
		this.addWindowListener(new SaveDataListener());
		attack.addActionListener(new ButtonListener());
		moveUp.addActionListener(new ButtonListener());
		moveLeft.addActionListener(new ButtonListener());
		moveRight.addActionListener(new ButtonListener());
		moveDown.addActionListener(new ButtonListener());
		endTurn.addActionListener(new ButtonListener());
		UseItem.addActionListener(new ButtonListener());
		endTurnAI.addActionListener(new ButtonListener());
		Shop.addActionListener(new ButtonListener());
		UnitInfo.addActionListener(new ButtonListener());
	

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == UseItem) {
				if (CurrentUnitSelected == null) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Please Select a Unit");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					for (int i = 0; i < items.size(); i++) {
						Item item = p1inv.getItem(items.get(i));
						CurrentUnitSelected = gameboard.useItem(item,
								CurrentUnitSelected);
						p1inv.removeItem(item);
						UpdateItemScreen();
					}
					items.clear();
				}
			}
			if (e.getSource() == moveUp) {
				if (CurrentUnitSelected == null) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Please Select a Unit");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					if (gameboard.canMove(CurrentUnitSelected, "N")) {
						int i = player1units.indexOf(CurrentUnitSelected);
						player1units.remove(i);

						CurrentUnitSelected = gameboard.move(
								CurrentUnitSelected, "N");
						if (CurrentUnitSelected.hasUnit()) {
							player1units.add(i, CurrentUnitSelected);
							targets(CurrentUnitSelected);

						} else {
							targets.clear();
							UpdateUnitScreen();

						}

						layoutAttackScreen();

						imagePanel.repaint();
						if (gameboard.CheckgameOverBooleanVersion(player1units)) {
							Object[] options = { "New Game", "Quit" };
							int n = JOptionPane
									.showOptionDialog(
											frame,
											player1
													+ " HAS WON THE GAME!! Would you like to start a new game?",
											"VICTORY!!",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null,
											options, options[1]);
							if (n == JOptionPane.YES_OPTION) {
								System.out.println("new game");
								dispose();
								new GUI();
							} else {
								if (n == JOptionPane.NO_OPTION) {
									System.out.println("no option");
									frame.dispatchEvent(new WindowEvent(frame,
											WindowEvent.WINDOW_CLOSING));
								}
							}
						}
					} else {
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Move failed");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}

			}

			if (e.getSource() == moveLeft) {
				if (CurrentUnitSelected == null) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Please Select a Unit");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					if (gameboard.canMove(CurrentUnitSelected, "L")) {
						int i = player1units.indexOf(CurrentUnitSelected);
						player1units.remove(i);

						CurrentUnitSelected = gameboard.move(
								CurrentUnitSelected, "L");
						if (CurrentUnitSelected.hasUnit()) {
							player1units.add(i, CurrentUnitSelected);
							targets(CurrentUnitSelected);

						} else {
							targets.clear();
							UpdateUnitScreen();
						}

						layoutAttackScreen();
						imagePanel.repaint();
						if (gameboard.CheckgameOverBooleanVersion(player1units)) {
							Object[] options = { "New Game", "Quit" };
							int n = JOptionPane
									.showOptionDialog(
											frame,
											player1
													+ " HAS WON THE GAME!! Would you like to start a new game?",
											"VICTORY!!",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null,
											options, options[1]);
							if (n == JOptionPane.YES_OPTION) {
								System.out.println("new game");
								dispose();
								new GUI();
							} else {
								if (n == JOptionPane.NO_OPTION) {
									System.out.println("no option");
									frame.dispatchEvent(new WindowEvent(frame,
											WindowEvent.WINDOW_CLOSING));
								}
							}
						}
					} else {
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Move failed");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}

			}

			if (e.getSource() == moveDown) {
				if (CurrentUnitSelected == null) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Please Select a Unit");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					if (gameboard.canMove(CurrentUnitSelected, "S")) {
						int i = player1units.indexOf(CurrentUnitSelected);
						player1units.remove(i);

						CurrentUnitSelected = gameboard.move(
								CurrentUnitSelected, "S");

						if (CurrentUnitSelected.hasUnit()) {
							player1units.add(i, CurrentUnitSelected);
							targets(CurrentUnitSelected);

						} else {
							targets.clear();
							UpdateUnitScreen();
						}

						layoutAttackScreen();
						imagePanel.repaint();
						System.out.println("before check");
						if (gameboard.CheckgameOverBooleanVersion(player1units)) {
							Object[] options = { "New Game", "Quit" };
							System.out.println("after check");
							int n = JOptionPane
									.showOptionDialog(
											frame,
											player2
													+ " HAS WON THE GAME!! Would you like to start a new game?",
											"VICTORY!!",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null,
											options, options[1]);
							if (n == JOptionPane.YES_OPTION) {
								System.out.println("new game");
								dispose();
								new GUI();
							} else {
								if (n == JOptionPane.NO_OPTION) {
									System.out.println("no option");
									frame.dispatchEvent(new WindowEvent(frame,
											WindowEvent.WINDOW_CLOSING));
								}
							}
						}

					} else {
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Move failed");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}

			}

			if (e.getSource() == moveRight) {
				if (CurrentUnitSelected == null) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Please Select a Unit");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					if (gameboard.canMove(CurrentUnitSelected, "R")) {
						int i = player1units.indexOf(CurrentUnitSelected);
						player1units.remove(i);

						CurrentUnitSelected = gameboard.move(
								CurrentUnitSelected, "R");
						if (CurrentUnitSelected.hasUnit()) {
							player1units.add(i, CurrentUnitSelected);
							targets(CurrentUnitSelected);

						} else {
							targets.clear();
							UpdateUnitScreen();
						}

						layoutAttackScreen();
						imagePanel.repaint();
						if (gameboard.CheckgameOverBooleanVersion(player1units)) {
							Object[] options = { "New Game", "Quit" };
							int n = JOptionPane
									.showOptionDialog(
											frame,
											player1
													+ " HAS WON THE GAME!! Would you like to start a new game?",
											"VICTORY!!",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null,
											options, options[1]);
							if (n == JOptionPane.YES_OPTION) {
								System.out.println("new game");
								dispose();
								new GUI();
							} else {
								if (n == JOptionPane.NO_OPTION) {
									System.out.println("no option");
									frame.dispatchEvent(new WindowEvent(frame,
											WindowEvent.WINDOW_CLOSING));
								}
							}
						}
					} else {
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Move failed");
						JDialog dialog = optionPane.createDialog(":~(");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}

			}
			if (e.getSource() == endTurn) {
				System.out.println("end of " + player1 + " turn");
				Inventory tempinventory = p1inv;
				p1inv = p2inv;
				p2inv = tempinventory;
				gameboard.turnOver2(player1units, player2units, player1,
						player2);
				player1units = gameboard.getPlayer1Units();
				player2units = gameboard.getPlayer2Units();
				usernamestring.setText("Current Player: " + player1);
				inventorystring.setText(player1 + "'s inventory: "
						+ p1inv.toString());
				p2inv.setCredits(p2inv.getCredits() + 5);
				UpdateUnitScreen();
				clearAttackScreen();
				UpdateItemScreen();
				CurrentUnitSelected = null;
				EnemyUnitSelected = null;
				unitdisplay = true;
				toggleUnitScreen();
				unitdisplay = false;
				revalidate();
				movePanel.repaint();
				AttackPanel.repaint();
				imagePanel.repaint();

			}
			if (e.getSource() == endTurnAI) {
				System.out.println("end of " + player1 + " turn");
				Inventory tempinventory = p1inv;
				p1inv = p2inv;
				p2inv = tempinventory;

				gameboard.turnOverComputer(player1units, player2units, player1,
						player2);
				computer.makeMove();
				endTurn.doClick();
				player1units = gameboard.getPlayer1Units();
				player2units = gameboard.getPlayer2Units();

				usernamestring.setText("Current Player: " + player1);
				inventorystring.setText(player1 + "'s inventory: "
						+ p1inv.toString());
				UpdateUnitScreen();
				clearAttackScreen();
				UpdateItemScreen();
				CurrentUnitSelected = null;
				EnemyUnitSelected = null;
				unitdisplay = true;
				toggleUnitScreen();
				unitdisplay = false;
				revalidate();
				movePanel.repaint();
				AttackPanel.repaint();
				imagePanel.repaint();
			}

			if (e.getSource() == attack) {
				if (EnemyUnitSelected == null) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Please Select an Enemy Unit");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else if (CurrentUnitSelected == null) {
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("Please Select a Friendly Unit to attack with");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else if (!gameboard.CanAttack(CurrentUnitSelected)) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("You have attacked already!");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					System.out.println(EnemyUnitSelected.getUnit().getHealth());

					gameboard.attack(CurrentUnitSelected, EnemyUnitSelected);
					targets(CurrentUnitSelected);
					layoutAttackScreen();
					if (gameboard.CheckgameOverBooleanVersion(player2units)) {
						Object[] options = { "New Game", "Quit" };
						int n = JOptionPane
								.showOptionDialog(
										frame,
										player1
												+ " HAS WON THE GAME!! Would you like to start a new game?",
										"VICTORY!!", JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										options, options[1]);
						if (n == JOptionPane.YES_OPTION) {
							System.out.println("new game");
							dispose();
							new GUI();
						} else {
							if (n == JOptionPane.NO_OPTION) {
								System.out.println("no option");
								frame.dispatchEvent(new WindowEvent(frame,
										WindowEvent.WINDOW_CLOSING));
							}
						}
					}

					revalidate();
					repaint();
				}
				// TODO Auto-generated method stub
			}
			if (e.getSource() == Shop || e.getSource() == closeShop) {
				shopscreen = !shopscreen;
				toggleShopScreen();

			}
			if (e.getSource() == UnitInfo) {
				toggleUnitScreen();
				unitdisplay = !unitdisplay;
			}
			if(shopbuttons == null){
				
			}
			else{
				for(JRadioButton element:shopbuttons){
					if(e.getSource() == element){
						
					}
				}
			}
			if(e.getSource() == buyItem){
				String selection = shopgroup.getSelection().getActionCommand();
				Item item = inv.get(selection);
				int cost = item.getCost();
				if(p1inv.getCredits() < cost){
					JOptionPane optionPane = new JOptionPane();
					optionPane
							.setMessage("You are too poor to buy this item!");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				else{
					p1inv.setCredits(p1inv.getCredits() - cost);
					p1inv.addItem(item);
					credits.setText("Credits: "+ p1inv.getCredits());
					UpdateItemScreen();
					toggleShopScreen();
					repaint();
					revalidate();
				}
				
			}
			if (radiobuttons.isEmpty() || radiobuttons.equals(null)) {

			} else {
				for (int i = 0; i < radiobuttons.size(); i++) {
					if (e.getSource() == radiobuttons.get(i)) {
						CurrentUnitSelected = player1units.get(i);
						System.out.println(CurrentUnitSelected.getUnit()
								.getUnitStatus());
						targets(CurrentUnitSelected);
						layoutAttackScreen();
						repaint();

					}
				}
			}
			if (targetButtons.isEmpty() || targetButtons.equals(null)) {

			} else {
				for (int i = 0; i < targetButtons.size(); i++) {
					if (e.getSource() == targetButtons.get(i)) {
						EnemyUnitSelected = targets.get(i);
						System.out.println(EnemyUnitSelected.getUnit()
								.getUnitStatus());
						repaint();
					}
				}
			}

			revalidate();
			repaint();

		}

	}

	private class itemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < itemBoxes.size(); i++) {
				if (e.getSource() == itemBoxes.get(i)) {
					if (e.getStateChange() == 1) {
						items.add(itemBoxes.get(i).getText());
					} else {
						items.remove(itemBoxes.get(i).getText());
					}
					System.out.println(items.toString() + "\n");
				}
			}

		}

	}

	private class MapButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == Map) {

				player1 = username1.getText();
				player2 = username2.getText();
				if (player1.equals("") || player1.equals(null)
						|| player2.equals("") || player2.equals(null)) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("You need to enter valid usernames!");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					frame.setVisible(false);
					newGame("Map 1");
					layoutGUI();
					registerListeners();
				}

			} else if (e.getSource() == RandomMap) {
				player1 = username1.getText();
				player2 = username2.getText();
				if (player1.equals("") || player1.equals(null)
						|| player2.equals("") || player2.equals(null)) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("You need to enter valid usernames!");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					frame.setVisible(false);
					newGame("Random");
					layoutGUI();
					registerListeners();
				}
			} else if (e.getSource() == Map2) {
				player1 = username1.getText();
				player2 = username2.getText();
				if (player1.equals("") || player1.equals(null)
						|| player2.equals("") || player2.equals(null)) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("You need to enter valid usernames!");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					frame.setVisible(false);
					newGame("Map 2");
					layoutGUI();
					registerListeners();
				}
			} else if (e.getSource() == vsAI) {
				player1 = username1.getText();
				player2 = username2.getText();
				if (player1.equals("") || player1.equals(null)
						|| player2.equals("") || player2.equals(null)) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("You need to enter valid usernames!");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					AIgame = true;
					frame.setVisible(false);
					newGame("vsAi");
					layoutGUI();
					registerListeners();
				}
			} else if (e.getSource() == load) {

				player1 = username1.getText();
				player2 = username2.getText();
				if (player1.equals("") || player1.equals(null)
						|| player2.equals("") || player2.equals(null)) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("You need to enter valid usernames!");
					JDialog dialog = optionPane.createDialog(":~(");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else {
					frame.setVisible(false);
					if (new File(saveDir + player1 + "-" + player2 + "-"
							+ "gameboard.dat").exists()) {
						loadData();
					} else {
						System.out
								.println("You don't have a save file! Creating new game...");
						newGame("Map 1");
					}
					layoutGUI();
					registerListeners();
				}

			}
		}

	}

	/**
	 * This listener triggers when the frame is closed, and saves the gameboard
	 * data.
	 */
	private class SaveDataListener implements WindowListener {
		@Override
		public void windowClosing(WindowEvent arg0) {
			saveData();
		}

		public void windowActivated(WindowEvent arg0) {
		}

		public void windowClosed(WindowEvent arg0) {
		}

		public void windowDeactivated(WindowEvent arg0) {
		}

		public void windowDeiconified(WindowEvent arg0) {
		}

		public void windowIconified(WindowEvent arg0) {
		}

		public void windowOpened(WindowEvent arg0) {
		}
	}

	/**
	 * This method attempts to load gameboard data from
	 * "./player 1-player 2-gameboard.dat"
	 * 
	 * @return true if successful, false otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean loadData() {
		try {

			// loads saved data into the gameboard if there is a saved game
			gameboard = new GameBoard("Map 2");
			if (new File(saveDir + player1 + "-" + player2 + "-"
					+ "gameboard.dat").exists()) {
				gameboard.loadData(player1, player2);
			}

			// create inventories for both players
			// both players start with a super item. WOW. how generous of us.
			p1inv = new Inventory(player1);
			if (new File(saveDir + player1 + "-inventory.dat").exists()) {
				p1inv.loadData(player1);
			} else {
				p1inv.addItem(Item.superitem);
			}
			p2inv = new Inventory(player2);
			if (new File(saveDir + player2 + "-inventory.dat").exists()) {
				p2inv.loadData(player2);
			} else {
				p2inv.addItem(Item.superitem);
			}
			System.out.println(player1 + "'s inventory: " + p1inv.toString());
			System.out.println(player2 + "'s inventory: " + p2inv.toString());
			player1units = gameboard.getPlayer1Units();
			player2units = gameboard.getPlayer2Units();
			CurrentUnitSelected = null;
			EnemyUnitSelected = null;

			return true;
		} catch (Exception e) {
			System.err.println("Unable to load data!");
		}
		return false;
	}

	/**
	 * This method attempts to save the gameboard and local inventories in
	 * "./player 1-player 2-gameboard.dat" and "./player1-inventory.dat"
	 * respectively.
	 */
	public void saveData() {
		try {
			gameboard.saveData(player1, player2);
			p1inv.saveData();
			p2inv.saveData();
		} catch (Exception e) {
			System.err.println("Error! Could not save data.");
		}
	}

}
