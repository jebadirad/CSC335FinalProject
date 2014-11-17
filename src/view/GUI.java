package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
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
		playerContainer.setLayout(new GridBagLayout());
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
		moveUp.setPreferredSize(new Dimension(50, 50));
		moveDown.setPreferredSize(new Dimension(50, 50));
		moveLeft.setPreferredSize(new Dimension(50,50));
		moveRight.setPreferredSize(new Dimension(50, 50));
		
		
		textPanel = new JPanel();
		textPanel.setBackground(Color.BLACK);
		textPanel.setPreferredSize(new Dimension(1280,500));
		
		
		
		movePanel = new JPanel();
		movePanel.setLayout(new BorderLayout());
		movePanel.setPreferredSize(new Dimension(50,200));
		movePanel.add(moveUp, BorderLayout.NORTH);
		movePanel.add(moveDown, BorderLayout.CENTER);
		movePanel.add(moveLeft, BorderLayout.WEST);
		movePanel.add(moveRight, BorderLayout.EAST);
		
		
		
		
		
		contentContainer.add(textPanel, BorderLayout.NORTH);
		contentContainer.add(playerContainer, BorderLayout.SOUTH);
		
		
		
		playerContainer.add(movePanel);
		playerContainer.add(usernamestring);
		
		
		
		add(contentContainer);
		
		
		
		
		
		
		
		
		setVisible(true);
	}
	
	public static String getUsername(){
		return username;
	}
	
	private void registerListeners(){
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
	
	
}
