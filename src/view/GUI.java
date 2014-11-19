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

public class GUI extends JFrame{
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
	
	JTabbedPane tabbedpane; 
	
	JLabel usernamestring;

	JRadioButton unit1;
	JRadioButton unit2;
	JRadioButton unit3;
	JRadioButton unit4;
	JRadioButton unit5;
	ButtonGroup unitgroup;
	
	JButton moveUp;
	JButton moveDown;
	JButton moveLeft;
	JButton moveRight;
	JButton attack;

	AttackButtonListener AttackButtonListener = new AttackButtonListener();
	DownButtonListener DownButtonListener = new DownButtonListener();
	LeftButtonListener LeftButtonListener = new LeftButtonListener();
	UpButtonListener UpButtonListener = new UpButtonListener();
	RightButtonListener RightButtonListener = new RightButtonListener();
	MapButtonListener MapButtonListener = new MapButtonListener();

	// things to run the game
	public static GameBoard gameboard;
	public static Cell CurrentUnitSelected;

	// pregame lobby GUI items

	JPanel teamSelect;
	JButton Map;
	JLabel usernamelabel1;
	JLabel usernamelabel2;
	JTextField username1;
	JTextField username2;

	public GUI() {
		super();
		frame = new JFrame();
		layoutPregameGUI();
	}

	public static void main(String[] args) {

		new GUI();

	}

	public void newGame() {
		gameboard = new GameBoard("Map 1");
		ArrayList<Cell> player1units = new ArrayList<Cell>();
		player1units.add(gameboard.getCell(0,0));
		
		CurrentUnitSelected = gameboard.getCell(10, 10);
	}

	private void loginGUI() {
		player1 = JOptionPane.showInputDialog("Username");
		
	}
	
	
	private void layoutAttackScreen(){
		
	}

	private void layoutPregameGUI() {

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

	private void layoutGUI() {
		frame.removeAll();
		tabbedpane = new JTabbedPane();
		tabbedpane.setPreferredSize(new Dimension(1280,800));
		
		JPanel contentContainer = new JPanel();
		contentContainer.setPreferredSize(new Dimension(1280, 800));
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
		unit1 = new JRadioButton("Unit1");
		unit2 = new JRadioButton("Unit2");
		unit3 = new JRadioButton("Unit3");
		unit4 = new JRadioButton("Unit4");
		unit5 = new JRadioButton("Unit5");
		unitgroup = new ButtonGroup();
		unitgroup.add(unit1);
		unitgroup.add(unit2);
		unitgroup.add(unit3);
		unitgroup.add(unit4);
		unitgroup.add(unit5);
		
		unitPanel.add(unit1);
		unitPanel.add(unit2);
		unitPanel.add(unit3);
		unitPanel.add(unit4);
		unitPanel.add(unit5);

		movePanel.add(DirectionPanel, BorderLayout.WEST);
		movePanel.add(unitPanel, BorderLayout.CENTER);

		JPanel AttackPanel = new JPanel();
		AttackPanel.setLayout(new BorderLayout());
		AttackPanel.setBackground(Color.YELLOW);
		JPanel attackButtonPanel = new JPanel();
		attackButtonPanel.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel blank = new JPanel();
		JPanel blank1 = new JPanel();
		JPanel filler = new JPanel();
		filler.setBackground(Color.YELLOW);

		attack = new JButton("Attack");
		attackButtonPanel.add(blank);
		attackButtonPanel.add(attack);
		attackButtonPanel.add(blank1);
		AttackPanel.add(attackButtonPanel, BorderLayout.NORTH);
		AttackPanel.add(filler, BorderLayout.CENTER);

		contentContainer.add(textPanel, BorderLayout.NORTH);
		contentContainer.add(playerContainer, BorderLayout.CENTER);

		playerContainer.add(movePanel);
		playerContainer.add(usernamestring);
		playerContainer.add(AttackPanel);

		tabbedpane.add(contentContainer, "Game");
		
		add(tabbedpane);

		setVisible(true);
	}

	public static String getPlayer1() {
		return player1;
	}

	public static String getPlayer2() {
		return player2;
	}

	private void registerListeners() {
		attack.addActionListener(AttackButtonListener);
		moveUp.addActionListener(UpButtonListener);
		moveLeft.addActionListener(LeftButtonListener);
		moveRight.addActionListener(RightButtonListener);
		moveDown.addActionListener(DownButtonListener);

	}


	private class LeftButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (gameboard.canMove(CurrentUnitSelected, "L")) {
				CurrentUnitSelected = gameboard.move(CurrentUnitSelected, "L");
				textPanel.repaint();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Move failed");
			}
		}

	}

	private class DownButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (gameboard.canMove(CurrentUnitSelected, "S")) {
				CurrentUnitSelected = gameboard.move(CurrentUnitSelected, "S");
				textPanel.repaint();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Move failed");
			}
		}

	}

	private class UpButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (gameboard.canMove(CurrentUnitSelected, "N")) {
				CurrentUnitSelected = gameboard.move(CurrentUnitSelected, "N");
				textPanel.repaint();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Move failed");
			}
		}

	}

	private class RightButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (gameboard.canMove(CurrentUnitSelected, "R")) {
				CurrentUnitSelected = gameboard.move(CurrentUnitSelected, "R");
				textPanel.repaint();
			}
			else{
				JOptionPane.showMessageDialog(frame, "Move failed");
			}
		}

	}

	private class AttackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}

	private class MapButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.setVisible(false);
			player1 = username1.getText();
			player2 = username2.getText();
			newGame();
			layoutGUI();
			registerListeners();
		}

	}

	private void repaintEverything(){
		frame.repaint();
	}
	
}
