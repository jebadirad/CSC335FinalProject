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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Cell;
import unit.*;

public class CharacterSelectionScreen extends JPanel
{
  private JLabel jlabel;

  private static final String imageDir = System.getProperty("user.dir")
      + File.separator + "images" + File.separator;
  String text = "";
  ArrayList<Unit> units;

  private UnitFactory factory;

  public CharacterSelectionScreen()
  {
    jlabel = new JLabel("Select 5 Units!");
    jlabel.setFont(new Font("Verdana", Font.BOLD, 76));
    jlabel.setForeground(Color.RED);
    this.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
    this.setVisible(true);
    jlabel.setLocation(0, 300);
    this.add(jlabel);

    units = new ArrayList<Unit>();
    // units = GUI.gameboard.getAllUnits
    factory = new UnitFactory();

    units.add(factory.makeUnit("CloneTrooper", "?"));
    units.add(factory.makeUnit("BattleDroid", "?"));
    units.add(factory.makeUnit("ImperialMedic", "?"));
    units.add(factory.makeUnit("LukeSkywalker", "?"));
    units.add(factory.makeUnit("DarthVader", "?"));
    units.add(factory.makeUnit("SpiderTank", "?"));
    units.add(factory.makeUnit("Droideka", "?"));
    units.add(factory.makeUnit("ArtilleryDroid", "?"));
    units.add(factory.makeUnit("Walker", "?"));

  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setPaint(Color.BLACK);
    g2.setFont(new Font("Courier New", Font.BOLD, 10));
    int y = 220;
    int x = 110;
    int counter = 0;
    for (Unit element : units)
    {
      BufferedImage image = null;
      try
      {
        image = ImageIO.read(new File(imageDir + element.getIconImage()));
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        System.out.println("404:FILE NOT FOUND");
      }
      if (counter == 5)
      {
        x = x + 625;
        y = 260;
      }
      g2.drawImage(image, x - 110, y - 100, null);
      g2.drawString(element.toString(), x, y - 80);
      g2.drawString("Damage: " + element.getDamage() + "  Attack Range: "
          + element.getAttackRange(), x, y - 60);
      g2.drawString("Health: " + element.getHealth() + "   Moves Left: "
          + element.getMovesLeft(), x, y - 40);
      if (element.getCanAttack().equals(true))
      {
        g2.drawString("Can Attack: Yes", x, y - 20);
      }
      else
      {
        g2.drawString("Can Attack: No", x, y - 20);
      }
      if (element.getDescription().length() >= 45)
      {
        String desc = element.getDescription();
        String desc1 = desc.substring(0, 45);
        String desc2 = desc.substring(45);
        String desc3 = "";
        if (desc2.length() >= 45)
        {
          desc3 = desc2.substring(45);
          desc2 = desc2.substring(0, 45);
        }
        g2.drawString(desc1, x, y);
        g2.drawString(desc2, x, y + 20);
        if (desc3.equals(""))
        {

        }
        else
        {
          g2.drawString(desc3, x, y + 40);
        }
      }
      else
      {
        g2.drawString(element.getDescription(), x, y);
      }

      y = y + 130;
      counter++;
    }
  }
}
