package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame implements Observer {
	JFrame frame;
	JPanel textPanel;
	JPanel graphicsPanel;
	JPanel movePanel;
	JPanel unitPanel;
	
	
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
		
		layoutGUI();
		registerListeners();
		setUpObservers();
		
	}
	
	public static void main(String[] args) {
		new GUI();
		
	}
	
	private void layoutGUI(){
		
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,800);
		setResizable(false);
		setLayout(new BorderLayout());
		moveUp = new JButton();
		moveDown = new JButton();
		moveLeft = new JButton();
		moveRight = new JButton();
		moveUp.setPreferredSize(new Dimension(75, 66));
		moveDown.setPreferredSize(new Dimension(75, 66));
		moveLeft.setPreferredSize(new Dimension(75, 66));
		moveRight.setPreferredSize(new Dimension(75, 66));
		textPanel = new JPanel();
		textPanel.setBackground(Color.BLACK);
		textPanel.setPreferredSize(new Dimension(800,500));
		movePanel = new JPanel();
		add(textPanel, BorderLayout.NORTH);
		
		movePanel.setLayout(new BorderLayout());
		movePanel.setSize(200,200);
		movePanel.add(moveUp, BorderLayout.CENTER);
		movePanel.add(moveDown, BorderLayout.SOUTH);
		movePanel.add(moveLeft, BorderLayout.WEST);
		movePanel.add(moveRight, BorderLayout.EAST);
		
		add(movePanel, BorderLayout.SOUTH);
		
		
		
		
		
		
		
		
		setVisible(true);
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
