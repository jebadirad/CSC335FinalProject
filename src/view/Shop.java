package view;

import item.Inventory;
import item.Item;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Shop extends JPanel {
	private static final String imageDir = System.getProperty("user.dir")
			+ File.separator + "images" + File.separator;
	String text = "";
	Inventory shopitems = new Inventory("Shop");
	HashMap<String, Item> inv;
	public Shop() {
		shopitems.addItem("Super Item");
		shopitems.addItem("Hyper Potion");
		shopitems.addItem("Apollo's Helm");
		shopitems.addItem("Speed Enhancer");
		inv = shopitems.getInventory();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, 1280, 500);
		g2.setPaint(Color.BLACK);
		g2.setFont(new Font("Courier New", Font.BOLD, 17));
		int y = 100;
		for(String key:inv.keySet()){
			int[] modifiers = inv.get(key).getModifiers();
			String modstring = "";
			int cost = inv.get(key).getCost();
			String path = inv.get(key).getPath();
			modstring = "Modifies HP by: " + modifiers[0] + ", Attack by: " + modifiers[1] + ", Attack Range by: " + modifiers[2] + ", Move Range by: " + modifiers[3] + " Cost: " + cost;
			BufferedImage image = null;
			try{
				image = ImageIO.read(new File(path));
				
			}catch (IOException e){
				System.out.println("404: Image file not found");
			}
			g2.drawImage(image,0,y-100,null);
			g2.drawString(key + ": " + modstring,110,y);
			
			y=y+120;
			
		}
	}
}
