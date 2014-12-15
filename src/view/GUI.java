package view;

import item.Inventory;
import item.Item;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import javax.swing.Timer;

import model.AI;
import model.Cell;
import model.Command;
import model.GameBoard;
import model.Terrain;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import unit.SpriteObject;
import unit.Unit;
import unit.UnitFactory;

/**
 * Public class that everything that has to do with user action with the game.
 * 
 * @author JonDavid Ebadirad
 * 
 */
public class GUI extends JFrame
{
  private static final long serialVersionUID = -2853985771911325020L;
  private static final String saveDir = System.getProperty("user.dir")
      + File.separator + "gamesaves" + File.separator;

  public static String player1 = "1";
  public static String player2 = "2";
  public static int player1FlagPoints;
  public static int player2FlagPoints;
  JPanel panel;

  public ArrayList<Cell> playerunits = new ArrayList<Cell>();
  AI computer;

  JFrame frame;

  JPanel title;
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
  JPanel teamselectContainer;
  JPanel usernameSelect;

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
  JButton Map2 = new JButton("Ice Madness Map");
  JButton RandomMap = new JButton("Random Map");
  JButton vsAI = new JButton("vs AI");
  JButton toMap = new JButton("Choose Map");
  JButton startAI = new JButton("Start AI Game");
  JButton startAIMonster = new JButton("Start Monster Game");
  JButton instructionButton = new JButton("Okay");
  JButton LoadTitleButton;
  private static List<SpriteObject> splosions;
  String mapName;
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
  private TitleScreen titleScreen;
  private JButton vsAITitle;
  private JButton vsHuman;
  private Component jlabel;
  private JButton next;
  private JLabel selectUnitsLabel;
  private JLabel selectCloneTrooperLabel;
  private JLabel selectBattleDroidLabel;
  private JLabel selectImperialMedicLabel;
  private JLabel selectLukeSkywalkerLabel;
  private JLabel selectDarthVaderLabel;
  private JLabel selectSpderTankLabel;
  private JLabel selectDroidekaLabel;
  private JLabel selectArtilleryDroidLabel;
  private JLabel selectWalkerLabel;
  private JTextField selectNumberOfCloneTrooper;
  private JLabel tellEmHowManyLabel;
  private JTextField selectNumberOfBattleDroid;
  private JTextField selectNumberOfImperialMedic;
  private JTextField selectNumberOfLukeSkywalker;
  private JTextField selectNumberOfDarthVader;
  private JTextField selectNumberOfSpiderTank;
  private JTextField selectNumberOfDroideka;
  private JTextField selectNumberOfArtilleryDroid;
  private JTextField selectNumberOfWalker;
  private ArrayList<Unit> units = new ArrayList<Unit>();
  private MapSelectionScreen mapSelect;
  private InstructionsScreen InstructionPane;
  Thread commandThread;

  private static final String clipsDir = System.getProperty("user.dir")
      + File.separator + "clips" + File.separator;

  /**
   * Simple Constructor
   */
  public GUI()
  {
    super();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    AudioStream as = null;
    String fileName = clipsDir + "StarWars.mp3";
    InputStream in = null;

    try
    {
      in = new FileInputStream(fileName);
    }
    catch (FileNotFoundException e)
    {
    }

    try
    {
      as = new AudioStream(in);
    }
    catch (IOException e)
    {
    }

    AudioPlayer.player.start(as);

    // AudioStream as = null;
    // String fileName = clipsDir + "StarWars.mp3";
    // InputStream in = null;

    try
    {
      in = new FileInputStream(fileName);
    }
    catch (FileNotFoundException e)
    {
    }

    try
    {
      as = new AudioStream(in);
    }
    catch (IOException e)
    {
    }

    // AudioPlayer.player.start(as);

    layoutTitleGUI();
  }

  /**
   * Main method that runs the whole program.
   * 
   * @param args
   *          does nothing in this case.
   */
  public static void main(String[] args)
  {

    new GUI().setDefaultCloseOperation(EXIT_ON_CLOSE);

  }

  /**
   * Officially creates a newgame().
   */
  public void newGame(String map, ArrayList<Unit> units)
  {
    if (AIgame)
    {
      AI computer = new AI();
    }

    gameboard = new GameBoard(map, units);
    commandThread = new Thread(new Runner());
    commandThread.start();

    // create inventories for both players
    p1inv = new Inventory(player1);
    p2inv = new Inventory(player2);
    // both players start with a super item. WOW. how generous of us.
    p1inv.addItem(Item.superitem);
    p2inv.addItem(Item.superitem);
    player1units = gameboard.getPlayer1Units();
    player2units = gameboard.getPlayer2Units();
    CurrentUnitSelected = null;
    EnemyUnitSelected = null;
    targetButtons = new ArrayList();

  }

  private class GameKeyListener implements KeyListener
  {

    public void keyPressed(KeyEvent e)
    {


      if (e.getKeyCode() == 37)
      {
        moveLeft.doClick();
      }
      if (e.getKeyCode() == 38)
      {
        moveUp.doClick();

      }
      if (e.getKeyCode() == 39)
      {
        moveRight.doClick();

      }
      if (e.getKeyCode() == 40)
      {
        moveDown.doClick();

      }
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void keyTyped(KeyEvent e)
    {
    }

  }

  private void targets(Cell cellWithUnit)
  {
    targets = gameboard.getUnitsInAttackRange(cellWithUnit);

  }

  @SuppressWarnings("unused")
  private void loginGUI()
  {
    player1 = JOptionPane.showInputDialog("Username");

  }

  private void UpdateItemScreen()
  {
    inventoryPanel.removeAll();
    itemBoxes = new ArrayList<JCheckBox>();
    items = new ArrayList<String>();
    Iterator it = p1inv.getInventory().entrySet().iterator();
    while (it.hasNext())
    {
      Map.Entry pairs = (Map.Entry) it.next();
      itemBoxes.add(new JCheckBox(pairs.getKey().toString()));
    }

    for (int i = 0; i < itemBoxes.size(); i++)
    {
      itemBoxes.get(i).addItemListener(new itemListener());
      inventoryPanel.add(itemBoxes.get(i));

    }

    playerContainer.repaint();
    revalidate();

  }

  private void toggleShopScreen()
  {
    if (shopscreen)
    {
      contentContainer.removeAll();
      JPanel shopNavButtons = new JPanel();
      JPanel Creditpanel = new JPanel();
      credits = new JLabel("Credits: " + p1inv.getCredits());
      shop = new JPanel();
      shop.setLayout(new GridLayout(1, 2, 0, 0));
      shopNav = new JPanel();

      shopNav.setLayout(new BorderLayout());

      shopNavButtons.setLayout(new GridLayout(2, 3, 0, 0));

      shopNav.add(shopNavButtons, BorderLayout.NORTH);
      shopNav.add(Creditpanel, BorderLayout.CENTER);

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
      imagePanel.setPreferredSize(new Dimension(1280, 500));
      shopgroup = new ButtonGroup();
      shopitems.addItem("Super Item");
      shopitems.addItem("Hyper Potion");
      shopitems.addItem("Apollo's Helm");
      shopitems.addItem("Speed Enhancer");

      inv = shopitems.getInventory();
      listItems.setLayout(new GridLayout(inv.size(), 1, 0, 0));
      shopbuttons = new ArrayList<JRadioButton>();
      for (String key : inv.keySet())
      {

        shopbuttons.add(new JRadioButton(key));

      }
      for (JRadioButton element : shopbuttons)
      {
        shopgroup.add(element);
        if (p1inv.hasItem(element.getText()))
        {
          listItems.add(new JLabel(element.getText()));
        }
        else
        {
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
    else
    {
      contentContainer.remove(imagePanel);
      contentContainer.remove(shop);
      imagePanel.removeAll();
      imagePanel = new Imageview(GameBoard.background);
      imagePanel.setPreferredSize(new Dimension(1280, 500));
      contentContainer.add(imagePanel, BorderLayout.NORTH);
      contentContainer.add(playerContainer, BorderLayout.CENTER);
      imagePanel.updateUI();
      playerContainer.updateUI();
      revalidate();
      repaint();
    }
  }

  private void toggleUnitScreen()
  {
    if (unitdisplay)
    {
      contentContainer.remove(imagePanel);
      imagePanel.removeAll();
      imagePanel = new Imageview(GameBoard.background);
      contentContainer.add(imagePanel);
      imagePanel.updateUI();
      revalidate();
      repaint();
    }
    else
    {
      contentContainer.remove(imagePanel);
      imagePanel.removeAll();
      imagePanel = new PlayerStatus();
      contentContainer.add(imagePanel);
      imagePanel.updateUI();
      revalidate();
      repaint();
    }

  }

  private void UpdateUnitScreen()
  {
    unitPanel.removeAll();
    unitgroup = new ButtonGroup();
    radiobuttons = new ArrayList<JRadioButton>();
    for (int i = 0; i < player1units.size(); i++)
    {

      radiobuttons.add(new JRadioButton(player1units.get(i).getUnit()
          .toString()));

    }
    for (int i = 0; i < radiobuttons.size(); i++)
    {
      unitgroup.add(radiobuttons.get(i));
      unitPanel.add(radiobuttons.get(i));
      radiobuttons.get(i).addActionListener(new ButtonListener());
    }
    movePanel.add(unitPanel, BorderLayout.CENTER);
    revalidate();
    movePanel.repaint();
  }

  private void layoutAttackScreen()
  {
    if (targets.size() <= 0)
    {
      EnemyUnitSelected = null;
    }

    targetButtons = new ArrayList();
    AttackPanel.remove(listOfTargets);
    listOfTargets = new JPanel();
    targetGroup = new ButtonGroup();
    for (int i = 0; i < targets.size(); i++)
    {
      int number = i + 1;
      targetButtons.add(new JRadioButton(targets.get(i).getUnit().toString()));
    }
    for (int i = 0; i < targetButtons.size(); i++)
    {
      targetGroup.add(targetButtons.get(i));
      listOfTargets.add(targetButtons.get(i));
      targetButtons.get(i).addActionListener(new ButtonListener());
    }

    AttackPanel.add(listOfTargets, BorderLayout.CENTER);
    listOfTargets.setVisible(true);
    revalidate();
  }

  private void clearAttackScreen()
  {
    targetButtons = new ArrayList();
    AttackPanel.remove(listOfTargets);
    listOfTargets = new JPanel();
    targetGroup = new ButtonGroup();

    AttackPanel.add(listOfTargets, BorderLayout.CENTER);
    listOfTargets.setVisible(true);
    revalidate();
  }

  private void layoutTitleGUI()
  {
    this.setSize(1280, 800);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    titleScreen = new TitleScreen();

    titleScreen.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    vsAITitle = new JButton("Play VS. AI");
    vsHuman = new JButton("Play VS. Human");
    LoadTitleButton = new JButton("Load Game");

    SelectionButtonListener SelectionButtonListener = new SelectionButtonListener();
    vsAITitle.addActionListener(SelectionButtonListener);
    vsHuman.addActionListener(SelectionButtonListener);
    LoadTitleButton.addActionListener(SelectionButtonListener);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30; // make this component tall
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy = 1;
    titleScreen.add(vsAITitle, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30; // make this component tall
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy = 2;
    titleScreen.add(vsHuman, c);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30; // make this component tall
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy = 3;
    titleScreen.add(LoadTitleButton, c);
    

    this.add(titleScreen);
    this.setVisible(true);
  }

  private class SelectionButtonListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == vsAITitle)
      {
        layoutPregameAIGUI();

      }

      if (e.getSource() == vsHuman)
      {
        layoutPregameGUI();
      }
      if(e.getSource() == LoadTitleButton){
    	  String s1 = (String)JOptionPane.showInputDialog(frame, "Enter Player 1 Username From Previous Game \n", "Loading Game", JOptionPane.QUESTION_MESSAGE,null,null,"");
    	  String s2 = (String)JOptionPane.showInputDialog(frame, "Enter Player 2 Username From Previous Game \n", "Loading Game", JOptionPane.QUESTION_MESSAGE,null,null,"");
    	  if(s1.equals("") || s1.isEmpty() || s1 == null || s2.equals("") || s2.isEmpty() || s2 == null){
    		  JOptionPane optionPane = new JOptionPane();
    	        optionPane.setMessage("Invalid load file, try again");
    	        JDialog dialog = optionPane.createDialog(":~(");
    	        dialog.setAlwaysOnTop(true);
    	        dialog.setVisible(true);
    	  }
    	  else{
    		  player1 = s1;
    		  player2 = s2;
    		  //load = new JButton("Load game");
    		    //load.addActionListener(MapButtonListener);
    		    if (new File(saveDir + "Map 1-" + player1 + "-" + player2 + "-" + "gameboard.dat")
                .exists())
            {
    		    	mapName = "Map 1";
    		    	loadData();
            }
    		    else if (new File(saveDir + "Map 2-" + player1 + "-" + player2 + "-" + "gameboard.dat")
                .exists())
            {
    		    	mapName = "Map 2";
              loadData();
            }
    		    else if (new File(saveDir + "Random-" + player1 + "-" + player2 + "-" + "gameboard.dat")
                .exists())
            {
    		    	mapName = "Random";
    		    	loadData();
            }
    		    else if (new File(saveDir + "Monster-" + player1 + "-" + player2 + "-" + "gameboard.dat")
                .exists())
            {
    		    	mapName = "Monster";
              loadData();
            }
    		    else if (new File(saveDir + "vsAi-" + player1 + "-" + player2 + "-" + "gameboard.dat")
                .exists())
            {
    		    	
    		    	mapName = "vsAi";
              loadData();
            }
            else
            {
            	JOptionPane optionPane = new JOptionPane();
    	        optionPane.setMessage("You don't have a save file!");
    	        JDialog dialog = optionPane.createDialog(":~(");
    	        dialog.setAlwaysOnTop(true);
    	        dialog.setVisible(true);
             
            }
    	  }
      }
    	  
    }

  }

  private void layoutPregameAIGUI()
  {
    // For Player Vs. AI
    this.remove(titleScreen);
    teamSelect = new CharacterSelectionScreen();
    title = new TitleDrawingScreen("BUILD YOUR TEAM OF 5 UNITS!");
    teamselectContainer = new JPanel();
    JPanel UnitselectContainer = new JPanel();
    JPanel usernameSelect = new JPanel();

    JPanel unitSelect = new JPanel();
    unitSelect.setLayout(new GridLayout(3, 7, 5, 0));

    usernameSelect.setLayout(new FlowLayout());
    usernameSelect.add(title);

    UnitselectContainer.setLayout(new GridLayout(2, 1, 0, 0));
    UnitselectContainer.setPreferredSize(new Dimension(1280, 500));

    teamselectContainer.setLayout(new GridLayout(2, 1, 0, 0));
    teamselectContainer.setPreferredSize(new Dimension(1280, 300));
    teamSelect.setLayout(new FlowLayout());

    usernamelabel1 = new JLabel("Player 1 username: ");
    username1 = new JTextField(15);

    selectCloneTrooperLabel = new JLabel("Clone Trooper: ");
    selectBattleDroidLabel = new JLabel("Battle Droid: ");
    selectImperialMedicLabel = new JLabel("Imperial Medic: ");
    selectLukeSkywalkerLabel = new JLabel("Luke Skywalker: ");
    selectDarthVaderLabel = new JLabel("Darth Vader: ");
    selectSpderTankLabel = new JLabel("Spider Tank: ");
    selectDroidekaLabel = new JLabel("Droideka: ");
    selectArtilleryDroidLabel = new JLabel("Artillery Droid: ");
    selectWalkerLabel = new JLabel("Walker: ");

    selectNumberOfCloneTrooper = new JTextField(10);
    selectNumberOfCloneTrooper.setText("0");
    selectNumberOfBattleDroid = new JTextField(10);
    selectNumberOfBattleDroid.setText("0");
    selectNumberOfImperialMedic = new JTextField(10);
    selectNumberOfImperialMedic.setText("0");
    selectNumberOfLukeSkywalker = new JTextField(10);
    selectNumberOfLukeSkywalker.setText("0");
    selectNumberOfDarthVader = new JTextField(10);
    selectNumberOfDarthVader.setText("0");
    selectNumberOfSpiderTank = new JTextField(10);
    selectNumberOfSpiderTank.setText("0");
    selectNumberOfDroideka = new JTextField(10);
    selectNumberOfDroideka.setText("0");
    selectNumberOfArtilleryDroid = new JTextField(10);
    selectNumberOfArtilleryDroid.setText("0");
    selectNumberOfWalker = new JTextField(10);
    selectNumberOfWalker.setText("0");

    usernameSelect.add(usernamelabel1);
    usernameSelect.add(username1);
    unitSelect.add(selectCloneTrooperLabel);
    unitSelect.add(selectNumberOfCloneTrooper);

    unitSelect.add(selectBattleDroidLabel);
    unitSelect.add(selectNumberOfBattleDroid);

    unitSelect.add(selectImperialMedicLabel);
    unitSelect.add(selectNumberOfImperialMedic);

    unitSelect.add(selectLukeSkywalkerLabel);
    unitSelect.add(selectNumberOfLukeSkywalker);

    unitSelect.add(selectDarthVaderLabel);
    unitSelect.add(selectNumberOfDarthVader);

    unitSelect.add(selectSpderTankLabel);
    unitSelect.add(selectNumberOfSpiderTank);

    unitSelect.add(selectDroidekaLabel);
    unitSelect.add(selectNumberOfDroideka);

    unitSelect.add(selectArtilleryDroidLabel);
    unitSelect.add(selectNumberOfArtilleryDroid);

    unitSelect.add(selectWalkerLabel);
    unitSelect.add(selectNumberOfWalker);

    usernameSelect.add(startAI);
    usernameSelect.add(startAIMonster);
    startAI.addActionListener(MapButtonListener);
    startAIMonster.addActionListener(MapButtonListener);

    UnitselectContainer.add(usernameSelect);
    UnitselectContainer.add(unitSelect);

    teamselectContainer.add(UnitselectContainer);
    teamselectContainer.add(teamSelect);

    // begin persistence code
    if (new File(saveDir + player1 + "-" + player2 + "-" + "gameboard.dat")
        .exists())
    {
      JPanel loadSave = new JPanel();
      JCheckBox chkLoad = new JCheckBox("Load saved game?");
      chkLoad.addItemListener(new ItemListener()
      {
        public void itemStateChanged(ItemEvent e)
        {
          loadGame = e.getStateChange() == 1 ? true : false;
        }
      });
      loadSave.add(chkLoad).setVisible(true);
      teamSelect.add(loadSave);
    }
    // end persistence code
    this.add(teamselectContainer);
    this.setVisible(true);
    this.repaint();
  }

  private void layoutPregameGUI()
  {
    // For Player Vs. Player
    // For Player Vs. AI
    this.remove(titleScreen);
    next = new JButton("Next");
    teamSelect = new CharacterSelectionScreen();
    title = new TitleDrawingScreen("BUILD YOUR TEAM OF 5 UNITS!");

    teamselectContainer = new JPanel();
    JPanel UnitselectContainer = new JPanel();
    usernameSelect = new JPanel();

    JPanel unitSelect = new JPanel();
    unitSelect.setLayout(new GridLayout(3, 7, 5, 0));

    usernameSelect.setLayout(new FlowLayout());
    usernameSelect.add(title);

    UnitselectContainer.setLayout(new GridLayout(2, 1, 0, 0));
    UnitselectContainer.setPreferredSize(new Dimension(1280, 500));

    teamselectContainer.setLayout(new GridLayout(2, 1, 0, 0));
    teamselectContainer.setPreferredSize(new Dimension(1280, 300));
    teamSelect.setLayout(new FlowLayout());

    usernamelabel1 = new JLabel("Player 1 username: ");
    username1 = new JTextField(15);
    usernamelabel2 = new JLabel("Player 2 username: ");
    username2 = new JTextField(15);

    selectCloneTrooperLabel = new JLabel("Clone Trooper: ");
    selectBattleDroidLabel = new JLabel("Battle Droid: ");
    selectImperialMedicLabel = new JLabel("Imperial Medic: ");
    selectLukeSkywalkerLabel = new JLabel("Luke Skywalker: ");
    selectDarthVaderLabel = new JLabel("Darth Vader: ");
    selectSpderTankLabel = new JLabel("Spider Tank: ");
    selectDroidekaLabel = new JLabel("Droideka: ");
    selectArtilleryDroidLabel = new JLabel("Artillery Droid: ");
    selectWalkerLabel = new JLabel("Walker: ");

    selectNumberOfCloneTrooper = new JTextField(10);
    selectNumberOfCloneTrooper.setText("0");
    selectNumberOfBattleDroid = new JTextField(10);
    selectNumberOfBattleDroid.setText("0");
    selectNumberOfImperialMedic = new JTextField(10);
    selectNumberOfImperialMedic.setText("0");
    selectNumberOfLukeSkywalker = new JTextField(10);
    selectNumberOfLukeSkywalker.setText("0");
    selectNumberOfDarthVader = new JTextField(10);
    selectNumberOfDarthVader.setText("0");
    selectNumberOfSpiderTank = new JTextField(10);
    selectNumberOfSpiderTank.setText("0");
    selectNumberOfDroideka = new JTextField(10);
    selectNumberOfDroideka.setText("0");
    selectNumberOfArtilleryDroid = new JTextField(10);
    selectNumberOfArtilleryDroid.setText("0");
    selectNumberOfWalker = new JTextField(10);
    selectNumberOfWalker.setText("0");

    usernameSelect.add(usernamelabel1);
    usernameSelect.add(username1);
    usernameSelect.add(usernamelabel2);
    usernameSelect.add(username2);
    unitSelect.add(selectCloneTrooperLabel);
    unitSelect.add(selectNumberOfCloneTrooper);

    unitSelect.add(selectBattleDroidLabel);
    unitSelect.add(selectNumberOfBattleDroid);

    unitSelect.add(selectImperialMedicLabel);
    unitSelect.add(selectNumberOfImperialMedic);

    unitSelect.add(selectLukeSkywalkerLabel);
    unitSelect.add(selectNumberOfLukeSkywalker);

    unitSelect.add(selectDarthVaderLabel);
    unitSelect.add(selectNumberOfDarthVader);

    unitSelect.add(selectSpderTankLabel);
    unitSelect.add(selectNumberOfSpiderTank);

    unitSelect.add(selectDroidekaLabel);
    unitSelect.add(selectNumberOfDroideka);

    unitSelect.add(selectArtilleryDroidLabel);
    unitSelect.add(selectNumberOfArtilleryDroid);

    unitSelect.add(selectWalkerLabel);
    unitSelect.add(selectNumberOfWalker);

    usernameSelect.add(next);
    next.addActionListener(MapButtonListener);

    UnitselectContainer.add(usernameSelect);
    UnitselectContainer.add(unitSelect);

    teamselectContainer.add(UnitselectContainer);
    teamselectContainer.add(teamSelect);

    // begin persistence code
    if (new File(saveDir + player1 + "-" + player2 + "-" + "gameboard.dat")
        .exists())
    {
      JPanel loadSave = new JPanel();
      JCheckBox chkLoad = new JCheckBox("Load saved game?");
      chkLoad.addItemListener(new ItemListener()
      {
        public void itemStateChanged(ItemEvent e)
        {
          loadGame = e.getStateChange() == 1 ? true : false;
        }
      });
      loadSave.add(chkLoad).setVisible(true);
      teamSelect.add(loadSave);
    }
    // end persistence code
    this.add(teamselectContainer);
    this.setVisible(true);
    this.repaint();
  }

  private void layoutMapScreen()
  {

    this.remove(teamselectContainer);
    mapSelect = new MapSelectionScreen();

    mapSelect.setLayout(new FlowLayout());

    Map = new JButton("Standard Map");
    next = new JButton("Continue to player 2 unit selection");
    next.addActionListener(MapButtonListener);
    Map.addActionListener(MapButtonListener);
    Map2.addActionListener(MapButtonListener);
    RandomMap.addActionListener(MapButtonListener);
    mapSelect.add(Map);
    mapSelect.add(Map2);
    mapSelect.add(RandomMap);
    

    this.add(mapSelect);
    this.setVisible(true);
    this.repaint();
  }

  public boolean checkPlayer2UnitSelecions()
  {

    int numberOfCloneTrooper = 0;
    int numberOfBattleDroid = 0;
    int numberOfImperialMedic = 0;
    int numberOfLukeSkywalker = 0;
    int numberOfDarthVader = 0;
    int numberOfSpiderTank = 0;
    int numberOfDroideka = 0;
    int numberOfArtilleryDroid = 0;
    int numberOfWalker = 0;

    int total = 0;
    while (total != 5)
    {
      try
      {
        numberOfCloneTrooper = Integer.parseInt(selectNumberOfCloneTrooper
            .getText());
        numberOfBattleDroid = Integer.parseInt(selectNumberOfBattleDroid
            .getText());
        numberOfImperialMedic = Integer.parseInt(selectNumberOfImperialMedic
            .getText());
        numberOfLukeSkywalker = Integer.parseInt(selectNumberOfLukeSkywalker
            .getText());
        numberOfDarthVader = Integer.parseInt(selectNumberOfDarthVader
            .getText());
        numberOfSpiderTank = Integer.parseInt(selectNumberOfSpiderTank
            .getText());
        numberOfDroideka = Integer.parseInt(selectNumberOfDroideka.getText());
        numberOfArtilleryDroid = Integer.parseInt(selectNumberOfArtilleryDroid
            .getText());
        numberOfWalker = Integer.parseInt(selectNumberOfWalker.getText());
      }
      catch (Exception e)
      {
        // TODO @bug After this popup we need to go back to the team selection
        // instead of map select
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Please make better units selections... ");
        JDialog dialog = optionPane.createDialog(":~(");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);

      }

      total = numberOfCloneTrooper + numberOfBattleDroid
          + numberOfImperialMedic + numberOfLukeSkywalker + numberOfDarthVader
          + numberOfSpiderTank + numberOfDroideka + numberOfArtilleryDroid
          + numberOfWalker;
      if (total != 5)
      {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("You must select five units, fool");
        JDialog dialog = optionPane.createDialog(":~(");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        total = 0;
        return false;
      }
    }
    // Add these five to the units list:
    UnitFactory factory = new UnitFactory();
    int spotInUnitsList = 5;
    for (int i = 0; i < numberOfCloneTrooper; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("CloneTrooper", player2));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfBattleDroid; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("BattleDroid", player2));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfImperialMedic; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("ImperialMedic", player2));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfLukeSkywalker; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("LukeSkywalker", player2));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfDarthVader; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("DarthVader", player2));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfSpiderTank; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("SpiderTank", player2));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfDroideka; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("Droideka", player2));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfArtilleryDroid; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("ArtilleryDroid", player2));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfWalker; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("Walker", player2));
      spotInUnitsList++;
    }

    return true;
  }

  public boolean checkUserSelections()
  {

    int numberOfCloneTrooper = 0;
    int numberOfBattleDroid = 0;
    int numberOfImperialMedic = 0;
    int numberOfLukeSkywalker = 0;
    int numberOfDarthVader = 0;
    int numberOfSpiderTank = 0;
    int numberOfDroideka = 0;
    int numberOfArtilleryDroid = 0;
    int numberOfWalker = 0;

    int total = 0;
    while (total != 5)
    {
      try
      {
        numberOfCloneTrooper = Integer.parseInt(selectNumberOfCloneTrooper
            .getText());
        numberOfBattleDroid = Integer.parseInt(selectNumberOfBattleDroid
            .getText());
        numberOfImperialMedic = Integer.parseInt(selectNumberOfImperialMedic
            .getText());
        numberOfLukeSkywalker = Integer.parseInt(selectNumberOfLukeSkywalker
            .getText());
        numberOfDarthVader = Integer.parseInt(selectNumberOfDarthVader
            .getText());
        numberOfSpiderTank = Integer.parseInt(selectNumberOfSpiderTank
            .getText());
        numberOfDroideka = Integer.parseInt(selectNumberOfDroideka.getText());
        numberOfArtilleryDroid = Integer.parseInt(selectNumberOfArtilleryDroid
            .getText());
        numberOfWalker = Integer.parseInt(selectNumberOfWalker.getText());
      }
      catch (Exception e)
      {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Please make better units selections... ");
        JDialog dialog = optionPane.createDialog(":~(");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);

      }

      total = numberOfCloneTrooper + numberOfBattleDroid
          + numberOfImperialMedic + numberOfLukeSkywalker + numberOfDarthVader
          + numberOfSpiderTank + numberOfDroideka + numberOfArtilleryDroid
          + numberOfWalker;
      if (total != 5)
      {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("You must select five units, fool");
        JDialog dialog = optionPane.createDialog(":~(");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        total = 0;
        return false;
      }
    }
    // Add these five to the units list:
    units = new ArrayList<Unit>();
    UnitFactory factory = new UnitFactory();
    int spotInUnitsList = 0;
    for (int i = 0; i < numberOfCloneTrooper; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("CloneTrooper", player1));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfBattleDroid; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("BattleDroid", player1));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfImperialMedic; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("ImperialMedic", player1));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfLukeSkywalker; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("LukeSkywalker", player1));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfDarthVader; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("DarthVader", player1));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfSpiderTank; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("SpiderTank", player1));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfDroideka; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("Droideka", player1));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfArtilleryDroid; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("ArtilleryDroid", player1));
      spotInUnitsList++;
    }
    for (int i = 0; i < numberOfWalker; i++)
    {
      units.add(spotInUnitsList, factory.makeUnit("Walker", player1));
      spotInUnitsList++;
    }

    return true;
  }

  private void layoutInstructionsGUI()
  {
    if (mapSelect != null)
    {
      this.remove(mapSelect);

    }
    if (teamSelect != null)
    {
      this.remove(teamselectContainer);

    }

    InstructionPane = new InstructionsScreen();
    InstructionPane.setLayout(new FlowLayout());

    instructionButton.addActionListener(MapButtonListener);
    instructionButton.setPreferredSize(new Dimension(200, 100));

    InstructionPane.add(instructionButton);
    this.add(InstructionPane);

    this.setVisible(true);
    this.repaint();

  }

  private void layoutGUI()
  {
	this.remove(titleScreen);
    if (mapSelect != null)
    {
      this.remove(mapSelect);

    }
    if (teamSelect != null)
    {
      this.remove(teamSelect);

    }
    if (InstructionPane != null)
    {
      this.remove(InstructionPane);

    }
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
    inventorystring = new JLabel(player1 + "'s inventory: " + p1inv.toString());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    // KeyListener listener = new GameKeyListener();
    // addKeyListener(listener);

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
    for (int i = 0; i < player1units.size(); i++)
    {

      radiobuttons.add(new JRadioButton(player1units.get(i).getUnit()
          .toString()));

    }
    for (int i = 0; i < radiobuttons.size(); i++)
    {
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
    if (AIgame)
    {

      attackButtonPanel.add(endTurnAI);
    }
    else
    {
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
    while (it.hasNext())
    {
      Map.Entry pairs = (Map.Entry) it.next();
      itemBoxes.add(new JCheckBox(pairs.getKey().toString()));
    }

    for (int i = 0; i < itemBoxes.size(); i++)
    {
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
    repaint();
  }

  /**
   * @return the username of the player that is set from the pregame lobby.
   */
  public static String getPlayer1()
  {
    return player1;
  }

  /**
   * @return the username of the player that is set from the pregame lobby.
   */
  public static String getPlayer2()
  {
    return player2;
  }

  private void registerListeners()
  {
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

  public void attack(Cell cellattack, Cell celldefense)
  {
    CurrentUnitSelected = cellattack;
    EnemyUnitSelected = celldefense;
    if (EnemyUnitSelected == null)
    {
      JOptionPane optionPane = new JOptionPane();
      optionPane.setMessage("Please Select an Enemy Unit");
      JDialog dialog = optionPane.createDialog(":~(");
      dialog.setAlwaysOnTop(true);
      dialog.setVisible(true);
    }
    else if (CurrentUnitSelected == null)
    {
      JOptionPane optionPane = new JOptionPane();
      optionPane.setMessage("Please Select a Friendly Unit to attack with");
      JDialog dialog = optionPane.createDialog(":~(");
      dialog.setAlwaysOnTop(true);
      dialog.setVisible(true);
    }
    else if (!gameboard.CanAttack(CurrentUnitSelected))
    {
      JOptionPane optionPane = new JOptionPane();
      optionPane.setMessage("You have attacked already!");
      JDialog dialog = optionPane.createDialog(":~(");
      dialog.setAlwaysOnTop(true);
      dialog.setVisible(true);
    }
    else
    {
      gameboard.attack(CurrentUnitSelected, EnemyUnitSelected);
      // TODO Auto-generated catch block

      splosions = new LinkedList<SpriteObject>();
      panel = new JPanel()
      {
        public void paintComponent(Graphics g)
        {
          super.paintComponent(g);
          for (SpriteObject explosion : splosions)
            explosion.draw(imagePanel.getGraphics()); // replace
                                                      // imagePanel.getGraphics()
                                                      // w/ g
        }
      };

      panel.setPreferredSize(new Dimension(1, 1)); // animation doesn't work if
                                                   // it's 0, 0 and any larger
                                                   // it's... ugly.

      Timer animTimer = new Timer(15, new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
          try
          {
            // splosions.stream().filter(s -> s.isFinished()).forEach(d ->
            // splosions.remove(d));

            LinkedList<SpriteObject> dead = new LinkedList<SpriteObject>();
            for (SpriteObject s : splosions)
              if (s.isFinished())
              {
                dead.add(s);

              }
            for (SpriteObject s : dead)
            {
              splosions.remove(s);
              repaint();

            }
          }
          catch (Exception e)
          {
          }

          panel.repaint();

        }

      });

      imagePanel.add(panel);
      imagePanel.repaint();
      animTimer.start();
      repaint();
      revalidate();

      Explosion explosion = new Explosion(EnemyUnitSelected.getLocation().y * 63, EnemyUnitSelected.getLocation().x * 24);
       
     
       
      splosions.add(explosion); 
      explosion.start();

      targets(CurrentUnitSelected);
      layoutAttackScreen();
      if (gameboard.CheckgameOverBooleanVersion(player2units))
      {
        Object[] options = {"New Game", "Quit"};
        int n = JOptionPane.showOptionDialog(frame, player1
            + " HAS WON THE GAME!! Would you like to start a new game?",
            "VICTORY!!", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (n == JOptionPane.YES_OPTION)
        {
          dispose();
          new GUI();
        }
        else
        {
          if (n == JOptionPane.NO_OPTION)
          {
            System.exit(0);
          }
        }
      }

      revalidate();
      repaint();
      playerContainer.repaint();
      playerContainer.revalidate();
    }
  }

  public void changeUnit(Cell cellwithunit)
  {
    CurrentUnitSelected = cellwithunit;
  }

  public void move(String direction, Cell cellwithunit)
  {

    if (CurrentUnitSelected == null)
    {
      JOptionPane optionPane = new JOptionPane();
      optionPane.setMessage("Please Select a Unit");
      JDialog dialog = optionPane.createDialog(":~(");
      dialog.setAlwaysOnTop(true);
      dialog.setVisible(true);
    }
    else
    {
      if (gameboard.canMove(CurrentUnitSelected, direction))
      {
        // Thread animation = new Thread(new Animate(cellwithunit, direction));
        // animation.start();
        // animation.run();
        int i = player1units.indexOf(CurrentUnitSelected);
        player1units.remove(i);

        CurrentUnitSelected = gameboard.move(CurrentUnitSelected, direction);

        if (CurrentUnitSelected.hasUnit())
        {
          player1units.add(i, CurrentUnitSelected);
          targets(CurrentUnitSelected);

        }
        else
        {
          targets.clear();
          UpdateUnitScreen();

        }

        layoutAttackScreen();

        imagePanel.repaint();
        if (gameboard.CheckgameOverBooleanVersion(player1units))
        {
          Object[] options = {"New Game", "Quit"};
          int n = JOptionPane.showOptionDialog(frame, player1
              + " HAS WON THE GAME!! Would you like to start a new game?",
              "VICTORY!!", JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
          if (n == JOptionPane.YES_OPTION)
          {
            dispose();
            new GUI();
          }
          else
          {
            if (n == JOptionPane.NO_OPTION)
            {
              System.exit(0);
            }
          }
        }
      }
      else
      {

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage("Move failed");
        JDialog dialog = optionPane.createDialog(":~(");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
      }
    }
  }

  private class Runner implements Runnable
  {

    @Override
    public void run()
    {
      try
      {
        synchronized (this)
        {
          while (true)
          {

            if (!GUI.gameboard.commandqueue.isEmpty())
            {
              Command<GUI> command = GUI.gameboard.commandqueue.poll();
              command.execute(GUI.this);


            }
          }
        }

      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

  }

  private class ButtonListener implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == UseItem)
      {
        if (CurrentUnitSelected == null)
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("Please Select a Unit");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else
        {
          for (int i = 0; i < itemBoxes.size(); i++)
          {
            if (itemBoxes.get(i).isSelected())
            {
              Item item = p1inv.getItem(itemBoxes.get(i).getText());
              CurrentUnitSelected = gameboard
                  .useItem(item, CurrentUnitSelected);
              p1inv.removeItem(item);
            }

          }
          UpdateItemScreen();
          items.clear();
        }
      }
      if (e.getSource() == moveUp)
      {
        move("N", CurrentUnitSelected);

      }

      if (e.getSource() == moveLeft)
      {
        move("L", CurrentUnitSelected);
      }

      if (e.getSource() == moveDown)
      {
        move("S", CurrentUnitSelected);
      }

      if (e.getSource() == moveRight)
      {
        move("R", CurrentUnitSelected);

      }
      if (e.getSource() == endTurn)
      {

        for (int i = 0; i < gameboard.getPlayer1Units().size(); i++)
        {
          if (gameboard.getPlayer1Units().get(i).getTerrain() == Terrain.Flag)
          {
            player1FlagPoints++;
            JOptionPane optionPane = new JOptionPane();
            int timesLeft = 5 - player1FlagPoints;
            optionPane
                .setMessage("You have ended you turn on the flag!, do this "
                    + timesLeft + " more times to win the game!");
            JDialog dialog = optionPane.createDialog(":~)");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
          }

        }

        if (player1FlagPoints >= 5)
        {
          // tell them they win
          Object[] options = {"Quit", "Quit"};
          int n = JOptionPane.showOptionDialog(frame, player1
              + " HAS WON THE GAME!!",
              "VICTORY!!", JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
          if (n == JOptionPane.YES_OPTION)
          {
        	  System.exit(0);
          }
          else
          {
            if (n == JOptionPane.NO_OPTION)
            {
            	System.exit(0);
            }
          }

        }

        Inventory tempinventory = p1inv;
        p1inv = p2inv;
        p2inv = tempinventory;
        gameboard.turnOver2(player1units, player2units, player1, player2);
        player1units = gameboard.getPlayer1Units();
        player2units = gameboard.getPlayer2Units();
        int tempPoints = player1FlagPoints;
        player1FlagPoints = player2FlagPoints;
        player2FlagPoints = tempPoints;
        usernamestring.setText("Current Player: " + player1);
        inventorystring.setText(player1 + "'s inventory: " + p1inv.toString());
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
      if (e.getSource() == endTurnAI)
      {
        Inventory tempinventory = p1inv;
        p1inv = p2inv;
        p2inv = tempinventory;

        gameboard
            .turnOverComputer(player1units, player2units, player1, player2);
        for (int i = 0; i < player1units.size(); i++)
        {
          computer.makeMove(player1units.get(i));
        }

        endTurn.doClick();
        player1units = gameboard.getPlayer1Units();
        player2units = gameboard.getPlayer2Units();

        usernamestring.setText("Current Player: " + player1);
        inventorystring.setText(player1 + "'s inventory: " + p1inv.toString());
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

      if (e.getSource() == attack)
      {
        attack(CurrentUnitSelected, EnemyUnitSelected);
      }
      if (e.getSource() == Shop || e.getSource() == closeShop)
      {
        shopscreen = !shopscreen;
        toggleShopScreen();

      }
      if (e.getSource() == UnitInfo)
      {
        toggleUnitScreen();
        unitdisplay = !unitdisplay;
      }
      if (shopbuttons == null)
      {

      }
      else
      {
        for (JRadioButton element : shopbuttons)
        {
          if (e.getSource() == element)
          {

          }
        }
      }
      if (e.getSource() == buyItem)
      {
        if (shopgroup.getSelection() == null)
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("Please select an item first!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else
        {
          String selection = shopgroup.getSelection().getActionCommand();
          Item item = inv.get(selection);
          int cost = item.getCost();
          if (p1inv.getCredits() < cost)
          {
            JOptionPane optionPane = new JOptionPane();
            optionPane.setMessage("You are too poor to buy this item!");
            JDialog dialog = optionPane.createDialog(":~(");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
          }
          else
          {
            p1inv.setCredits(p1inv.getCredits() - cost);
            p1inv.addItem(item);
            credits.setText("Credits: " + p1inv.getCredits());
            UpdateItemScreen();
            toggleShopScreen();
            repaint();
            revalidate();
          }
        }

      }
      if (radiobuttons.isEmpty() || radiobuttons.equals(null))
      {

      }
      else
      {
        for (int i = 0; i < radiobuttons.size(); i++)
        {
          if (e.getSource() == radiobuttons.get(i))
          {
            CurrentUnitSelected = player1units.get(i);
            targets(CurrentUnitSelected);
            layoutAttackScreen();
            repaint();

          }
        }
      }
      if (targetButtons.isEmpty() || targetButtons.equals(null))
      {

      }
      else
      {
        for (int i = 0; i < targetButtons.size(); i++)
        {
          if (e.getSource() == targetButtons.get(i))
          {
            EnemyUnitSelected = targets.get(i);
            repaint();
          }
        }
      }

      revalidate();
      repaint();

    }

  }

  private class itemListener implements ItemListener
  {

    @Override
    public void itemStateChanged(ItemEvent e)
    {
      for (int i = 0; i < itemBoxes.size(); i++)
      {
        if (e.getSource() == itemBoxes.get(i))
        {
          if (e.getStateChange() == 1)
          {
            items.add(itemBoxes.get(i).getText());
          }
          else
          {
            items.remove(itemBoxes.get(i).getText());
          }
        }
      }

    }

  }

  private class MapButtonListener implements ActionListener
  {

    private String mapType;

    @Override
    public void actionPerformed(ActionEvent e)
    {
      if (e.getSource() == toMap)
      {
        player1 = username1.getText();
        player2 = username2.getText();
        if (player1.equals("") || player1.equals(null) || player2.equals("")
            || player2.equals(null))
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("You need to enter valid usernames!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else if (player1.equals(player2) || player2.equals(player1))
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("User names must be unique!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }

        else
        {
          if (checkPlayer2UnitSelecions() == false)
          {

          }
          else
            layoutMapScreen();
        }
      }
      if (e.getSource() == instructionButton)
      {
        if (mapType.equals("AIMap"))
        {
          AIgame = true;
          computer = new AI();
          setVisible(false);
          newGame("vsAi", units);
          layoutGUI();
          registerListeners();
        }
        
        if(mapType.equals("Monster")){
        	AIgame = true;
        	computer = new AI();
        	setVisible(false);
        	newGame("Monster", units);
        	layoutGUI();
        	registerListeners();
        }
        if (mapType.equals("Map1"))
        {
          newGame("Map 1", units);
          layoutGUI();
          registerListeners();
        }
        if (mapType.equals("Map2"))
        {
          newGame("Map 2", units);
          layoutGUI();
          registerListeners();
        }
        if (mapType.equals("Random"))
        {
          newGame("Random", units);
          layoutGUI();
          registerListeners();
        }
        repaint();

      }
      if (e.getSource() == startAI)
      {
        player1 = username1.getText();
        player2 = "Computer";
        if (player1.equals("") || player1.equals(null) || player2.equals("")
            || player2.equals(null))
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("You need to enter valid usernames!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else if (player1.equals(player2) || player2.equals(player1))
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("User names must be unique!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else
        {

          if (checkUserSelections() == false)
          {

          }
          else
          {
            mapType = "AIMap";
            layoutInstructionsGUI();
          }

        }

      }
      if (e.getSource() == startAIMonster)
      {
        player1 = username1.getText();
        player2 = "Computer";
        if (player1.equals("") || player1.equals(null) || player2.equals("")
            || player2.equals(null))
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("You need to enter valid usernames!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else if (player1.equals(player2) || player2.equals(player1))
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("User names must be unique!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else
        {

          if (checkUserSelections() == false)
          {

          }
          else
          {
            mapType = "Monster";
            layoutInstructionsGUI();
          }

        }

      }
      if (e.getSource() == next)
      {
        player1 = username1.getText();
        player2 = username2.getText();
        if (player1.equals("") || player1.equals(null) || player2.equals("")
            || player2.equals(null))
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("You need to enter valid usernames!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else if (player1.equals(player2) || player2.equals(player1))
        {
          JOptionPane optionPane = new JOptionPane();
          optionPane.setMessage("User names must be unique!");
          JDialog dialog = optionPane.createDialog(":~(");
          dialog.setAlwaysOnTop(true);
          dialog.setVisible(true);
        }
        else
        {

          if (checkUserSelections() == false)
          {

          }
          else
          {
            toMap.addActionListener(MapButtonListener);
            usernameSelect.remove(next);

            usernameSelect.add(toMap);

            selectNumberOfCloneTrooper.setText("0");
            selectNumberOfBattleDroid.setText("0");
            selectNumberOfImperialMedic.setText("0");
            selectNumberOfLukeSkywalker.setText("0");
            selectNumberOfDarthVader.setText("0");
            selectNumberOfSpiderTank.setText("0");
            selectNumberOfDroideka.setText("0");
            selectNumberOfArtilleryDroid.setText("0");
            selectNumberOfWalker.setText("0");

            JOptionPane optionPane = new JOptionPane();
            optionPane
                .setMessage("Player 2 make your unit selections, then click the map desired ");
            JDialog dialog = optionPane.createDialog(":~)");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            teamSelect.remove(next);

            username1.setEditable(false);
            username2.setEditable(false);

            repaint();
            revalidate();
          }

        }

      }

      if (e.getSource() == Map)
      {
        mapType = "Map1";
        layoutInstructionsGUI();
      }
      else if (e.getSource() == RandomMap)
      {
        mapType = "Random";
        layoutInstructionsGUI();
      }
      else if (e.getSource() == Map2)
      {
        mapType = "Map2";
        layoutInstructionsGUI();
      }
   
    }
  }

  /**
   * This listener triggers when the frame is closed, and saves the gameboard
   * data.
   */
  private class SaveDataListener implements WindowListener
  {
    @Override
    public void windowClosing(WindowEvent arg0)
    {
      saveData();
    }

    public void windowActivated(WindowEvent arg0)
    {
    }

    public void windowClosed(WindowEvent arg0)
    {
    }

    public void windowDeactivated(WindowEvent arg0)
    {
    }

    public void windowDeiconified(WindowEvent arg0)
    {
    }

    public void windowIconified(WindowEvent arg0)
    {
    }

    public void windowOpened(WindowEvent arg0)
    {
    }
  }

  /**
   * This method attempts to load gameboard data from
   * "./player 1-player 2-gameboard.dat"
   * 
   * @return true if successful, false otherwise
   */
  @SuppressWarnings("unchecked")
  public boolean loadData()
  {
    try
    {
    
      // loads saved data into the gameboard if there is a saved game
      if (new File(saveDir + mapName +"-"+ player1 + "-" + player2 + "-" + "gameboard.dat")
          .exists())
      {
    	  units.clear();
    	  for(int i = 0; i < 10; i ++){
    		  UnitFactory factory = new UnitFactory();
    			Unit unit = factory.makeUnit("CloneTrooper", GUI.getPlayer1());
    			units.add(unit);
    	  }
    	  gameboard = new GameBoard("Map 1", units);
    	  units.clear();
    	  Cell[][] real = new Cell[20][20];
    	  for(int i = 0; i < 20; i++){
    		  for(int j = 0; j < 20; j ++){
    			 real[i][j] = new Cell(Terrain.Nothing,i,j);
    		  }
    	  }
    	  try{
    		  FileInputStream fileIn = new FileInputStream(new File(saveDir + mapName +"-"+ player1 + "-" + player2 + "-" + "gameboard.dat"));
    		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
    		real = (Cell[][]) objectIn.readObject();
    		  player1units = (ArrayList<Cell>) objectIn.readObject();
    		  player2units = (ArrayList<Cell>) objectIn.readObject();
    		  GameBoard.background = (String) objectIn.readObject();
    		  objectIn.close();
    		  
    	  }
    	  catch(Exception e){
    		  e.printStackTrace();
    	  }
    	  for(int i = 0; i < 20; i++){
    		  for(int j = 0; j < 20; j ++){
    			  gameboard.getBoard()[i][j] = real[i][j];
    			  if(gameboard.getBoard()[i][j].hasUnit()){
    				  gameboard.getBoard()[i][j].setUnit(real[i][j].getUnit());
    			  }
    		  }
    	  }

      }

      // create inventories for both players
      // both players start with a super item. WOW. how generous of us.
      p1inv = new Inventory(player1);
      if (new File(saveDir + player1 + "-inventory.dat").exists())
      {
        p1inv.loadData(player1);
      }
      else
      {
        //p1inv.addItem(Item.superitem);
      }
      p2inv = new Inventory(player2);
      if (new File(saveDir + player2 + "-inventory.dat").exists())
      {
        p2inv.loadData(player2);
      }
      else
      {
        //p2inv.addItem(Item.superitem);
      }
      //player1units = gameboard.getPlayer1Units();
      //player2units = gameboard.getPlayer2Units();
      
      CurrentUnitSelected = null;
      EnemyUnitSelected = null;
      
      if (mapName.equals("vsAi"))
      {
        AIgame = true;
        computer = new AI();
        setVisible(false);
        layoutGUI();
        registerListeners();
      }
      
      if(mapName.equals("Monster")){
      	AIgame = true;
      	computer = new AI();
      	setVisible(false);
      	layoutGUI();
      	registerListeners();
      }
      if (mapName.equals("Map 1"))
      {
        layoutGUI();
        registerListeners();
      }
      if (mapName.equals("Map 2"))
      {
        layoutGUI();
        registerListeners();
      }
      if (mapName.equals("Random"))
      {
        layoutGUI();
        registerListeners();
      }
      repaint();
      
      
      
      
      endTurn.doClick();
      endTurn.doClick();
      return true;
    }
    catch (Exception e)
    {
    	e.printStackTrace();
      System.err.println("Unable to load data!");
    }
    return false;
  }

  /**
   * This method attempts to save the gameboard and local inventories in
   * "./player 1-player 2-gameboard.dat" and "./player1-inventory.dat"
   * respectively.
   */
  public void saveData()
  {
    try
    {
      gameboard.saveData(player1, player2);
      p1inv.saveData();
      p2inv.saveData();
    }
    catch (Exception e)
    {
      System.err.println("Error! Could not save data.");
    }
  }

  public class Animate implements Runnable
  {
    Cell cell;
    String d;

    public Animate(Cell cell, String direction)
    {
      this.cell = cell;
      this.d = direction;
    }

    @Override
    public void run()
    {
      if (cell.getUnit() != null)
      {
      }
      Image image = Imageview.getSheet("lukeSkywalker");
      // for (Image image : b) {
      // if(cell.getUnit().equals(image)){}
      Image scaledImg = image.getScaledInstance(24, 63, Image.SCALE_DEFAULT);
      int initX = cell.getLocation().x;
      int x = cell.getLocation().x;
      int initY = cell.getLocation().y;
      int y = cell.getLocation().y;
      int endX = initX + 24;
      int endY = initY + 63;
      if (d == "N")
      {
        while (initY > endY)
        {
          initY = initY - 1;
          // cell.getLocation().translate(0, 1);
          imagePanel.getGraphics().drawImage(scaledImg, initX, initY, null);
          imagePanel.repaint();
          imagePanel.revalidate();
          revalidate();
          repaint();
          // set the cell to the next one up
        }
        CurrentUnitSelected = gameboard.move(cell, d);
        this.cell = CurrentUnitSelected;
        return;
      }
      else
        return; // TODO get rid of this else and add conditionals
      // for S, L, & R.
      // update where the image is drawn
      // }
    }
  }

  public static List<SpriteObject> getSplosions()
  {
    return splosions;
  }

  public void paintComponents(Graphics g)
  {
    super.paintComponents(g);
    if (!splosions.isEmpty())
      for (SpriteObject explosion : splosions)
        explosion.draw(g);
  }
}