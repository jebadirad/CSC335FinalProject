package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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

public class CharacterSelectionScreen extends JPanel
{
  private JLabel jlabel;

  private static final String imageDir = System.getProperty("user.dir")
      + File.separator + "images" + File.separator;
  String text = "";
  ArrayList<Cell> units;

  public CharacterSelectionScreen()
  {
    jlabel = new JLabel("Select 5 Units!");
    jlabel.setFont(new Font("Verdana", Font.BOLD, 76));
    jlabel.setForeground(Color.RED);
    this.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
    this.setVisible(true);
    jlabel.setLocation(0, 300);
    this.add(jlabel);

    units = new ArrayList();
    // units = GUI.gameboard.getAllUnits
  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setPaint(Color.BLACK);
    g2.setFont(new Font("Courier New", Font.BOLD, 17));
    int y = 100;
    for (Cell element : units)
    {
      BufferedImage image = null;
      try
      {
        image = ImageIO.read(new File(imageDir
            + element.getUnit().getIconImage()));
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        System.out.println("404:FILE NOT FOUND");
      }
      g2.drawImage(image, 0, y - 100, null);
      g2.drawString(element.getUnit().toString(), 110, y - 80);
      g2.drawString("Damage: " + element.getUnit().getDamage()
          + "  Attack Range: " + element.getUnit().getAttackRange(), 110,
          y - 60);
      g2.drawString("Health: " + element.getUnit().getHealth()
          + "   Moves Left: " + element.getUnit().getMovesLeft(), 110, y - 40);
      if (element.getUnit().getCanAttack().equals(true))
      {
        g2.drawString("Can Attack: Yes", 110, y - 20);
      }
      else
      {
        g2.drawString("Can Attack: No", 110, y - 20);
      }
      g2.drawString(element.getUnit().getDescription(), 110, y);

      y = y + 120;
    }
  }
}
