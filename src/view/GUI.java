package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import unit.CloneTrooper;
import unit.Unit;
import model.Cell;
import model.GameBoard;
import model.Terrain;

public class GUI extends JFrame implements Observer {
	private static final long serialVersionUID = -2853985771911325020L;

	public static String player1;
	public static String player2;

	JFrame frame;
	JPanel textPanel = new GraphicsPanel();
	JPanel graphicsPanel;
	JPanel movePanel;
	JPanel unitPanel;

	JLabel usernamestring;

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
		CurrentUnitSelected = gameboard.getCell(0, 0);
	}

	private void loginGUI() {
		player1 = JOptionPane.showInputDialog("Username");

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
		unitPanel.setBackground(Color.BLUE);

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

		add(contentContainer);

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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	private class LeftButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gameboard.getCell(1, 1).setTerrain(Terrain.Desert);
			gameboard.getCell(1, 2).setTerrain(Terrain.Desert);
			gameboard.getCell(1, 3).setTerrain(Terrain.Desert);
			textPanel.repaint();
		}

	}

	private class DownButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (gameboard.move(CurrentUnitSelected, "S")) {
				textPanel.repaint();
			}
			else{
				JOptionPane.showMessageDialog(frame, "failed");
			}
		}

	}

	private class UpButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class RightButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			gameboard.getCell(2, 1).setTerrain(Terrain.Forest);
			gameboard.getCell(3, 2).setTerrain(Terrain.Forest);
			gameboard.getCell(4, 3).setTerrain(Terrain.Forest);
			textPanel.repaint();
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

}
