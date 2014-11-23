package view;

import item.Inventory;
import item.Item;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
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

public class GUI extends JFrame
{
  private static final long serialVersionUID = -2853985771911325020L;
  private static final String saveDir = System.getProperty("user.dir") + File.separator + "gamesaves" + File.separator;

  public static String player1;
  public static String player2;

  public ArrayList<Cell> playerunits = new ArrayList<Cell>();

  JFrame frame;

  JPanel textPanel = new GraphicsPanel();
  JPanel graphicsPanel;
  JPanel movePanel;
  JPanel unitPanel;
  JPanel playerstatus;
  JPanel imagePanel = new Imageview();
  JPanel contentContainer;
  JTabbedPane tabbedpane;

  JPanel listOfTargets;
  JLabel usernamestring, inventorystring;
  JPanel attackButtonPanel;
  ButtonGroup unitgroup;
  ButtonGroup targetGroup;
  JButton moveUp;
  JButton moveDown;
  JButton moveLeft;
  JButton moveRight;
  JButton attack;
  JButton endTurn;

  ArrayList<JRadioButton> radiobuttons;
  ArrayList<JRadioButton> targetButtons;

  MapButtonListener MapButtonListener = new MapButtonListener();
  ButtonGroupListener ButtonGroupListener = new ButtonGroupListener();

  // things to run the game
  public static GameBoard gameboard;
  public static Cell CurrentUnitSelected;
  public static Cell EnemyUnitSelected;
  private ArrayList<Cell> player1units;
  private ArrayList<Cell> player2units;
  private ArrayList<Cell> targets;

  // pregame lobby GUI items

  JPanel teamSelect;
  JButton Map;
  JLabel usernamelabel1;
  JLabel usernamelabel2;
  JTextField username1;
  JTextField username2;

  private JPanel AttackPanel;
  private Inventory p1inv, p2inv;

  public GUI()
  {
    super();
    frame = new JFrame();
    layoutPregameGUI();
  }

  public static void main(String[] args)
  {

    new GUI().setDefaultCloseOperation(EXIT_ON_CLOSE);

  }

  public void newGame()
  {
    gameboard = new GameBoard("Map 1");
    // create inventories for both players
    p1inv = new Inventory(player1);
    p2inv = new Inventory(player2);
    // both players start with a super item. WOW. how generous of us.
    p1inv.addItem(Item.superitem);
    System.out.println(player1 + "'s inventory: " + p1inv.toString());
    p2inv.addItem(Item.superitem);
    System.out.println(player2 + "'s inventory: " + p2inv.toString());
    player1units = gameboard.getPlayer1Untis();
    player2units = gameboard.getPlayer2Untis();
    CurrentUnitSelected = null;
    EnemyUnitSelected = null;

  }

  private void targets(Cell cellWithUnit){
	  targets = gameboard.getUnitsInAttackRange(cellWithUnit);
	  
  }
  private void loginGUI()
  {
    player1 = JOptionPane.showInputDialog("Username");

  }

  private void UpdateUnitScreen(){
	  unitPanel.removeAll();
	  unitgroup = new ButtonGroup();
	    radiobuttons = new ArrayList();
	    for (int i = 0; i < player1units.size(); i++)
	    {

	      radiobuttons.add(new JRadioButton(player1units.get(i).getUnit().toString()));

	    }
	    for (int i = 0; i < radiobuttons.size(); i++)
	    {
	      unitgroup.add(radiobuttons.get(i));
	      unitPanel.add(radiobuttons.get(i));
	      radiobuttons.get(i).addActionListener(new ButtonListener());
	    }
	    movePanel.add(unitPanel, BorderLayout.CENTER);
	    revalidate();
  }
  private void layoutAttackScreen()
  {
	  if(targets.size() <= 0){
		  EnemyUnitSelected = null;
	  }
	 
	  
	  targetButtons = new ArrayList();
	  AttackPanel.remove(listOfTargets);
	  listOfTargets = new JPanel();
	  targetGroup = new ButtonGroup();
	  for(int i = 0; i < targets.size(); i ++){
		  int number = i+1;
		  targetButtons.add(new JRadioButton(targets.get(i).getUnit().toString()));
	  }
	  for(int i = 0; i < targetButtons.size(); i++){
		  targetGroup.add(targetButtons.get(i));
		  listOfTargets.add(targetButtons.get(i));
		  targetButtons.get(i).addActionListener(new ButtonListener());
	  }
	  
	  AttackPanel.add(listOfTargets, BorderLayout.CENTER);
	  listOfTargets.setVisible(true);
	  revalidate();
  }
  private void clearAttackScreen(){
	  targetButtons = new ArrayList();
	  AttackPanel.remove(listOfTargets);
	  listOfTargets = new JPanel();
	  targetGroup = new ButtonGroup();
	 
	 
	  
	  AttackPanel.add(listOfTargets, BorderLayout.CENTER);
	  listOfTargets.setVisible(true);
	  revalidate();
  }

  private void layoutPregameGUI()
  {

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
    Map = new JButton("Map 1");
    Map.addActionListener(MapButtonListener);
    teamSelect.add(Map);
    frame.add(teamSelect);

    frame.setVisible(true);

  }

  private void layoutGUI()
  {
    frame.removeAll();
    tabbedpane = new JTabbedPane();
    tabbedpane.setPreferredSize(new Dimension(1280, 500));

    contentContainer = new JPanel();
    contentContainer.setPreferredSize(new Dimension(1280, 500));
    contentContainer.setLayout(new BorderLayout());

    JPanel playerContainer = new JPanel();
    playerContainer.setLayout(new GridLayout(1, 3, 0, 0));
    playerContainer.setSize(1280, 300);

    usernamestring = new JLabel("Current Player: " + player1);
    inventorystring = new JLabel(player1 + "'s inventory: " + p1inv.toString());
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

    textPanel.setPreferredSize(new Dimension(1280, 500));
    imagePanel.setPreferredSize(new Dimension(1280, 500));

    movePanel = new JPanel();

    movePanel.setLayout(new BorderLayout());

    JPanel DirectionPanel = new JPanel();
    DirectionPanel.setPreferredSize(new Dimension(250, 75));
    DirectionPanel.setLayout(new GridLayout(2, 3, 0, 0));
    JPanel blankPanel = new JPanel();
    JPanel blankpanel1 = new JPanel();
    DirectionPanel.add(blankPanel);
    DirectionPanel.add(moveUp);
    DirectionPanel.add(blankpanel1);
    DirectionPanel.add(moveLeft);
    DirectionPanel.add(moveDown);
    DirectionPanel.add(moveRight);

    unitPanel = new JPanel();
    unitPanel.setPreferredSize(new Dimension(175, 75));
    unitgroup = new ButtonGroup();
    radiobuttons = new ArrayList();
    for (int i = 0; i < player1units.size(); i++)
    {

      int number = i + 1;
      radiobuttons.add(new JRadioButton(player1units.get(i).getUnit().toString()));

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
    attackButtonPanel = new JPanel();
    attackButtonPanel.setLayout(new GridLayout(1, 3, 0, 0));
    listOfTargets = new JPanel();
    JPanel blank = new JPanel();
    JPanel blank1 = new JPanel();

    attack = new JButton("Attack");
    endTurn = new JButton("End turn");
    attackButtonPanel.add(blank);
    attackButtonPanel.add(attack);
    attackButtonPanel.add(endTurn);
    AttackPanel.add(attackButtonPanel, BorderLayout.NORTH);
    AttackPanel.add(listOfTargets, BorderLayout.CENTER);
    
    contentContainer.add(tabbedpane, BorderLayout.NORTH);
    contentContainer.add(playerContainer, BorderLayout.CENTER);

    playerContainer.add(movePanel);
    playerContainer.add(usernamestring);
    playerContainer.add(inventorystring);
    playerContainer.add(AttackPanel);

    tabbedpane.add(textPanel, "Game");
    tabbedpane.add(imagePanel, "Graphical View");

    add(contentContainer);

    setVisible(true);
  }

  public static String getPlayer1()
  {
    return player1;
  }

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

  }

  private class ButtonListener implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      // TODO Auto-generated method stub
      if (e.getSource() == moveUp)
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
          if (gameboard.canMove(CurrentUnitSelected, "N"))
          {
            int i = player1units.indexOf(CurrentUnitSelected);
            player1units.remove(i);

            CurrentUnitSelected = gameboard.move(CurrentUnitSelected, "N");
            player1units.add(i, CurrentUnitSelected);
            targets(CurrentUnitSelected);
            layoutAttackScreen();
            textPanel.repaint();
            imagePanel.repaint();
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

      if (e.getSource() == moveLeft)
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
          if (gameboard.canMove(CurrentUnitSelected, "L"))
          {
            int i = player1units.indexOf(CurrentUnitSelected);
            player1units.remove(i);

            CurrentUnitSelected = gameboard.move(CurrentUnitSelected, "L");
            player1units.add(i, CurrentUnitSelected);
            targets(CurrentUnitSelected);
            layoutAttackScreen();            
            textPanel.repaint();
            imagePanel.repaint();
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

      if (e.getSource() == moveDown)
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
          if (gameboard.canMove(CurrentUnitSelected, "S"))
          {
            int i = player1units.indexOf(CurrentUnitSelected);
            player1units.remove(i);

            CurrentUnitSelected = gameboard.move(CurrentUnitSelected, "S");
            player1units.add(i, CurrentUnitSelected);
            targets(CurrentUnitSelected);
            layoutAttackScreen();
            textPanel.repaint();
            imagePanel.repaint();
            

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

      if (e.getSource() == moveRight)
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
          if (gameboard.canMove(CurrentUnitSelected, "R"))
          {
            int i = player1units.indexOf(CurrentUnitSelected);
            player1units.remove(i);

            CurrentUnitSelected = gameboard.move(CurrentUnitSelected, "R");
            player1units.add(i, CurrentUnitSelected);
            targets(CurrentUnitSelected);
            layoutAttackScreen();
            textPanel.repaint();
            imagePanel.repaint();
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
      if(e.getSource() == endTurn){
    	  System.out.println("end of " + player1 + " turn");
    	  gameboard.turnOver2(player1units, player2units,player1,player2);
    	  player1units = gameboard.getPlayer1Untis();
    	  player2units = gameboard.getPlayer2Untis();
    	  usernamestring.setText("Current Player: " + player1);
    	  inventorystring.setText(player1 + "'s inventory: " + p1inv.toString());
    	  UpdateUnitScreen();
    	  clearAttackScreen();
    	  CurrentUnitSelected = null;
    	  EnemyUnitSelected = null;
    	  revalidate();
    	  textPanel.repaint();
    	  imagePanel.repaint();
      }
      
      
      if (e.getSource() == attack)
      {
    	  if(EnemyUnitSelected == null){
    		  JOptionPane optionPane = new JOptionPane();
              optionPane.setMessage("Please Select an Enemy Unit");
              JDialog dialog = optionPane.createDialog(":~(");
              dialog.setAlwaysOnTop(true);
              dialog.setVisible(true);
    	  }
    	  else if(CurrentUnitSelected == null){
    		  JOptionPane optionPane = new JOptionPane();
              optionPane.setMessage("Please Select a Friendly Unit to attack with");
              JDialog dialog = optionPane.createDialog(":~(");
              dialog.setAlwaysOnTop(true);
              dialog.setVisible(true);
    	  }
    	  else if(!gameboard.CanAttack(CurrentUnitSelected)){
    		  JOptionPane optionPane = new JOptionPane();
              optionPane.setMessage("You have attacked already!");
              JDialog dialog = optionPane.createDialog(":~(");
              dialog.setAlwaysOnTop(true);
              dialog.setVisible(true);
    	  }
    	  else{
    		  System.out.println(EnemyUnitSelected.getUnit().getHealth());
    		  
    		  gameboard.attack(CurrentUnitSelected, EnemyUnitSelected);
    		  targets(CurrentUnitSelected);
    		  layoutAttackScreen();
    		  if(gameboard.CheckgameOverBooleanVersion(player2units)){
    			 Object[] options = {"New Game", "Quit"};
    			 int n = JOptionPane.showOptionDialog(frame, player1 + " HAS WON THE GAME!!! Would you like to start a new game?", "VICTORY!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
    			 if(n == JOptionPane.YES_OPTION){
    				 System.out.println("new game");
    				 dispose();
    				 new GUI();
    			 }
    			 else {
    				 if(n == JOptionPane.NO_OPTION){
    					 System.out.println("no option");
    					 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    				 }
    			 }
    		  }
    		  
    		  revalidate();
    		  repaint();
    	  }
        // TODO Auto-generated method stub
      }
      for (int i = 0; i < radiobuttons.size(); i++)
      {
        if (e.getSource() == radiobuttons.get(i))
        {
          CurrentUnitSelected = player1units.get(i);
          System.out.println(CurrentUnitSelected.getUnit().getUnitStatus());
          targets(CurrentUnitSelected);
          layoutAttackScreen();
          
        }
      }
      for(int i =0; i < targetButtons.size(); i ++){
    	  if(e.getSource() == targetButtons.get(i)){
    		  EnemyUnitSelected = targets.get(i);
    		  System.out.println(EnemyUnitSelected.getUnit().getUnitStatus());
    	  }
      }

    }
  }

  
  
  private class MapButtonListener implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      // TODO Auto-generated method stub
      frame.setVisible(false);
      player1 = username1.getText();
      player2 = username2.getText();
      newGame();
      layoutGUI();
      registerListeners();
    }

  }

  private class ButtonGroupListener implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      // TODO Auto-generated method stub

    }

  }

  private void repaintEverything()
  {
    frame.repaint();
    imagePanel.repaint();
  }

	/**
	 * This listener triggers when the frame is closed, and saves the gameboard data.
	 */
	private class SaveDataListener implements WindowListener {
		@Override
		public void windowClosing(WindowEvent arg0) {
			saveData();
		}
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}

	/**
	 * This method attempts to load gameboard data from "./gameboard.dat"
	 * 
	 * @return true if successful, false otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean loadData(String username) {
		try {
			FileInputStream fileIn = new FileInputStream(new File(saveDir + "gameboard.dat"));
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			gameboard = (GameBoard) objectIn.readObject();
			objectIn.close();
			return true;
		} catch (Exception e){
			System.err.println("Unable to load data!");
		}
		return false;
	}

	/**
	 * This method attempts to save the gameboard in "./gameboard.dat"
	 */
	public void saveData() {
		try {
			FileOutputStream fileOut = new FileOutputStream(new File(saveDir + "gameboard.dat"));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(gameboard);
			objectOut.close();
		} catch (Exception e){
			System.err.println("Error! Could not save data.");
		}
	}

}
