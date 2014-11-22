package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

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

import unit.CloneTrooper;
import unit.Unit;
import model.Cell;
import model.GameBoard;
import model.Terrain;

public class GUI extends JFrame
{
  private static final long serialVersionUID = -2853985771911325020L;

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

  JTabbedPane tabbedpane;

  JPanel listOfTargets;
  JLabel usernamestring;
  JPanel attackButtonPanel;
  ButtonGroup unitgroup;

  JButton moveUp;
  JButton moveDown;
  JButton moveLeft;
  JButton moveRight;
  JButton attack;

  ArrayList<JRadioButton> radiobuttons = new ArrayList();
  ArrayList<JRadioButton> targetButtons = new ArrayList();

  MapButtonListener MapButtonListener = new MapButtonListener();
  ButtonGroupListener ButtonGroupListener = new ButtonGroupListener();

  // things to run the game
  public static GameBoard gameboard;
  public static Cell CurrentUnitSelected;
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

  public GUI()
  {
    super();
    frame = new JFrame();
    layoutPregameGUI();
  }

  public static void main(String[] args)
  {

    new GUI();

  }

  public void newGame()
  {
    gameboard = new GameBoard("Map 1");
    player1units = gameboard.getPlayer1Untis();
    player2units = gameboard.getPlayer2Untis();
    CurrentUnitSelected = null;

  }

  private void targets(Cell cellWithUnit){
	  targets = gameboard.getUnitsInAttackRange(cellWithUnit);
  }
  private void loginGUI()
  {
    player1 = JOptionPane.showInputDialog("Username");

  }

  private void layoutAttackScreen()
  {
	  listOfTargets.removeAll();
	  ButtonGroup targetgroup = new ButtonGroup();
	  
	  for(int i = 0; i < targets.size(); i ++){
		  int number = i+1;
		  targetButtons.add(new JRadioButton("Unit" + number));
	  }
	  for(int i = 0; i < targetButtons.size(); i++){
		  targetgroup.add(targetButtons.get(i));
		  listOfTargets.add(targetButtons.get(i));
		  targetButtons.get(i).addActionListener(new ButtonListener());
	  }
  }

  private void layoutPregameGUI()
  {

    frame.setSize(350, 400);
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

    JPanel contentContainer = new JPanel();
    contentContainer.setPreferredSize(new Dimension(1280, 500));
    contentContainer.setLayout(new BorderLayout());

    JPanel playerContainer = new JPanel();
    playerContainer.setLayout(new GridLayout(1, 3, 0, 0));
    playerContainer.setSize(1280, 300);

    usernamestring = new JLabel("Current Player: " + player1);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    JPanel unitPanel = new JPanel();
    unitPanel.setPreferredSize(new Dimension(175, 75));
    unitgroup = new ButtonGroup();

    for (int i = 0; i < player1units.size(); i++)
    {

      int number = i + 1;
      radiobuttons.add(new JRadioButton("Unit" + number));

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
    attackButtonPanel.add(blank);
    attackButtonPanel.add(attack);
    attackButtonPanel.add(blank1);
    AttackPanel.add(attackButtonPanel, BorderLayout.NORTH);
    AttackPanel.add(listOfTargets, BorderLayout.CENTER);
    
    contentContainer.add(tabbedpane, BorderLayout.NORTH);
    contentContainer.add(playerContainer, BorderLayout.CENTER);

    playerContainer.add(movePanel);
    playerContainer.add(usernamestring);
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
    attack.addActionListener(new ButtonListener());
    moveUp.addActionListener(new ButtonListener());
    moveLeft.addActionListener(new ButtonListener());
    moveRight.addActionListener(new ButtonListener());
    moveDown.addActionListener(new ButtonListener());

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

      if (e.getSource() == attack)
      {
        // TODO Auto-generated method stub
      }
      for (int i = 0; i < radiobuttons.size(); i++)
      {
        if (e.getSource() == radiobuttons.get(i))
        {
          CurrentUnitSelected = player1units.get(i);
          System.out.println("This is my current unit: " + i);
          targets(CurrentUnitSelected);
          layoutAttackScreen();
          
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

}
