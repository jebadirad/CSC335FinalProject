package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class GUI extends JFrame implements Observer {
	private static final long serialVersionUID = -2853985771911325020L;

	public static String username; 
	
	JFrame frame;
	JPanel textPanel;
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
	public GUI(){
		super();
		frame = new JFrame();
		loginGUI();
		layoutGUI();
		registerListeners();
		setUpObservers();
		
	}
	
	public static void main(String[] args) {
		new GUI();
		
	}
	
	private void loginGUI(){
		username = JOptionPane.showInputDialog("Username");
		
	}
	
	
	private void layoutGUI(){
		
		JPanel contentContainer = new JPanel();
		contentContainer.setPreferredSize(new Dimension(1280,800));
		contentContainer.setLayout(new BorderLayout());
		
		
		JPanel playerContainer = new JPanel();
		playerContainer.setLayout(new GridLayout(1,3,0,0));
		playerContainer.setSize(1280,300);
		
		
		
		usernamestring = new JLabel("Current Player: " + username);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280,800);
		setResizable(false);
		setLayout(new BorderLayout());
		moveUp = new JButton("up");
		moveDown = new JButton("down");
		moveLeft = new JButton("left");
		moveRight = new JButton("right");
		moveUp.setPreferredSize(new Dimension(50, 100));
		moveDown.setPreferredSize(new Dimension(50, 50));
		moveLeft.setPreferredSize(new Dimension(75,50));
		moveRight.setPreferredSize(new Dimension(75, 50));
		
		
		textPanel = new JPanel();
		textPanel.setBackground(Color.BLACK);
		textPanel.setPreferredSize(new Dimension(1280,500));
		
		
		
		movePanel = new JPanel();
		
		
		movePanel.setLayout(new BorderLayout());
		
		
		JPanel DirectionPanel = new JPanel();
		DirectionPanel.setPreferredSize(new Dimension(250,75));
		DirectionPanel.setLayout(new GridLayout(2,3,0,0));
		JPanel blankPanel = new JPanel();
		JPanel blankpanel1 = new JPanel();
					DirectionPanel.add(blankPanel);
					DirectionPanel.add(moveUp);
					DirectionPanel.add(blankpanel1);
					DirectionPanel.add(moveDown);
					DirectionPanel.add(moveLeft);
					DirectionPanel.add(moveRight);
					
		JPanel unitPanel = new JPanel();
		unitPanel.setPreferredSize(new Dimension(175,75));
		unitPanel.setBackground(Color.BLUE);
		
		
		movePanel.add(DirectionPanel, BorderLayout.WEST);
		movePanel.add(unitPanel, BorderLayout.CENTER);
		
		JPanel AttackPanel = new JPanel();
		AttackPanel.setLayout(new BorderLayout());
		AttackPanel.setBackground(Color.YELLOW);
		JPanel attackButtonPanel = new JPanel();
		attackButtonPanel.setLayout(new GridLayout(1,3,0,0));
		
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
	
	public static String getUsername(){
		return username;
	}
	
	private void registerListeners(){
		attack.addActionListener(AttackButtonListener);
		moveUp.addActionListener(UpButtonListener);
		moveLeft.addActionListener(LeftButtonListener);
		moveRight.addActionListener(RightButtonListener);
		moveDown.addActionListener(DownButtonListener);
		
	}
	private void setUpObservers(){
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	private class LeftButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class DownButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class UpButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class RightButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class AttackButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
