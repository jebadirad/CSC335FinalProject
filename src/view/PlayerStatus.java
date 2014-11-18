package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PlayerStatus extends JPanel {

	String text = "";
	
	
	public PlayerStatus(){
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.WHITE);
		g2.fillRect(0,0,1280,500);
		g2.setPaint(Color.BLACK);
		g2.setFont(new Font("Courier New", Font.BOLD, 17));
		int y = 0;
		for(String line: text.split("\n")){
			g2.drawString(line, 0, y+= g.getFontMetrics().getHeight());
		}
	}
}
