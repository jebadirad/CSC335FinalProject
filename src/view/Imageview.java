package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class Imageview extends JPanel
{
  private BufferedImage image;
  private Point pos;
  private Cell[][] theModel;
  private JLabel message;

  private BufferedImage grassTile;
  private BufferedImage itemSheet;
  private BufferedImage cloneTrooper;
  private BufferedImage jediSheet;

  public Imageview()
  {
    loadImages();
    this.setVisible(true);
    repaint();

  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    theModel = GUI.gameboard.getBoard();
    Graphics2D g2 = (Graphics2D) g;
    g2.setPaint(Color.WHITE);
    g2.fillRect(0, 0, 1280, 500);

    for (int i = 0; i < theModel.length; i++)
    {
      for (int j = 0; j < theModel[i].length; j++)
      {

        image = grassTile;
        g2.drawImage(image, j * 64, i * 23, 64, 23, null);

        if (theModel[i][j].hasUnit())
        {
          if (theModel[i][j].getUnit().getIconImage()
              .equals("CloneTrooper.png"))
          {
            image = cloneTrooper;
            g2.drawImage(image, j * 64, i * 23, 64, 23, null);
          }
        }
      }
    }
  }

  public void loadImages()
  {
    try
    {
      grassTile = ImageIO
          .read(new File("images" + File.separator + "Grass.png"));
    }
    catch (IOException e)
    {
      System.out.println("Could not find 'Grass.png'");
    }
    try
    {
      itemSheet = ImageIO.read(new File("images" + File.separator
          + "all_items.png"));

    }
    catch (IOException e)
    {
      System.out.println("Could not find 'all_items.png'");
    }
    try
    {
      cloneTrooper = ImageIO.read(new File("images" + File.separator
          + "CloneTrooper.png"));

    }
    catch (IOException e)
    {
      System.out.println("Could not find 'CloneTrooper.png'");
    }
    try
    {
      jediSheet = ImageIO.read(new File("images" + File.separator
          + "jedi-spritesheet.png"));

    }
    catch (IOException e)
    {
      System.out.println("Could not find 'jedi-spritesheet.png'");
    }
    try
    {
      itemSheet = ImageIO.read(new File("images" + File.separator
          + "range-tank-spritesheet.png"));

    }
    catch (IOException e)
    {
      System.out.println("Could not find 'range-tank-spritesheet.png'");
    }
    try
    {
      itemSheet = ImageIO
          .read(new File("images" + File.separator + "Medic.png"));

    }
    catch (IOException e)
    {
      System.out.println("Could not find 'Medic.png'");
    }
  }

}
