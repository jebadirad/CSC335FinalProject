package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Cell;

public class PlayerStatus extends JPanel {
	private static final String imageDir = System.getProperty("user.dir")
		      + File.separator + "images" + File.separator;
	String text = "";
	ArrayList<Cell> units;
	public PlayerStatus(){
		units = new ArrayList();
		units = GUI.gameboard.getPlayer1Units();
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.WHITE);
		g2.fillRect(0,0,1280,500);
		g2.setPaint(Color.BLACK);
		g2.setFont(new Font("Courier New", Font.BOLD, 17));
		int y = 100;
		int x = 110;
		int counter = 0;
for(Cell element:units){
	BufferedImage image = null;
	try {
		image = ImageIO.read(new File(imageDir + element.getUnit().getIconImage()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("404:FILE NOT FOUND");
	}
	if(counter == 3 ){
		x = x + 700;
		y=100;
	}
	g2.drawImage(image, x-110, y-100 , null);
	g2.drawString(element.getUnit().toString(),x,y-80);
	g2.drawString("Damage: " + element.getUnit().getDamage() +"  Attack Range: " + element.getUnit().getAttackRange(),x,y-60);
	g2.drawString("Health: " + element.getUnit().getHealth() + "   Moves Left: " + element.getUnit().getMovesLeft(), x, y-40);
	if(element.getUnit().getCanAttack().equals(true)){
		g2.drawString("Can Attack: Yes", x, y-20);
	}
	else{
		g2.drawString("Can Attack: No", x, y-20);
	}
	if(element.getUnit().getDescription().length() >= 45){
		String desc = element.getUnit().getDescription();
		String desc1 = desc.substring(0,45);
		String desc2 = desc.substring(45);
		String desc3 = "";
		if(desc2.length() >= 45){
			desc3 = desc2.substring(45);
			desc2 = desc2.substring(0,45);
		}
		g2.drawString(desc1, x, y);
		g2.drawString(desc2,x, y+20);
		if(desc3.equals("")){
			
		}
		else{
			g2.drawString(desc3, x,y+40);
		}
	}
	else{
		g2.drawString(element.getUnit().getDescription(), x, y);
	}
	
	
	y = y + 160;
	counter++;
		}
	}
}
