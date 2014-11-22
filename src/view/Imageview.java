package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Cell;
import model.Terrain;

public class Imageview extends JPanel
{
  private BufferedImage image;
  private Cell[][] theModel;

  private BufferedImage grassTile;
  private BufferedImage itemSheet;
  private BufferedImage cloneTrooper;
  private BufferedImage jediSheet;
  private BufferedImage lavaSheet;
  private BufferedImage boulderSheet;
  private BufferedImage medicSheet;
  private BufferedImage iceSheet;
  private BufferedImage quicksandSheet;
  private BufferedImage desertSheet;
  private static BufferedImage backgroundSheet;

  private static final String imageDir = System.getProperty("user.dir")
      + File.separator + "images" + File.separator;

  public Imageview()
  {
    // Loads all the images into BufferedImaged
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
        // Places
        image = backgroundSheet;
        g2.drawImage(image, j * 64, i * 23, 64, 23, null);

        if (theModel[i][j].getTerrain() == Terrain.Boulder)
        {
          image = boulderSheet;
          g2.drawImage(image, j * 64, i * 23, 64, 23, null);
        }
        if (theModel[i][j].getTerrain() == Terrain.Desert)
        {
          image = desertSheet;
          g2.drawImage(image, j * 64, i * 23, 64, 23, null);
        }
        if (theModel[i][j].getTerrain() == Terrain.Ice)
        {
          image = iceSheet;
          g2.drawImage(image, j * 64, i * 23, 64, 23, null);
        }
        if (theModel[i][j].getTerrain() == Terrain.Lava)
        {
          image = lavaSheet;
          g2.drawImage(image, j * 64, i * 23, 64, 23, null);
        }
        if (theModel[i][j].getTerrain() == Terrain.QuickSand)
        {
          image = quicksandSheet;
          g2.drawImage(image, j * 64, i * 23, 64, 23, null);
        }
        if (theModel[i][j].hasUnit())
        {
          String unitName = theModel[i][j].getUnit().getIconImage();
          if (unitName.equals("CloneTrooper.png"))
          {
            image = cloneTrooper;
            g2.drawImage(image, j * 64, i * 23, 64, 23, null);
          }

          if (unitName.equals("Medic.png"))
          {
            image = medicSheet;
            g2.drawImage(image, j * 64, i * 23, 64, 23, null);
          }
        }

      }
    }
  }

  public static void setBackground(String imagestring)
  {

    try
    {
      if (imagestring.equals("Grass.png"))
      {
        backgroundSheet = ImageIO.read(new File(imageDir + "Grass.png"));

      }
      if (imagestring.equals("desert.png"))
      {
        backgroundSheet = ImageIO.read(new File(imageDir + "desert.png"));
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void loadImages()
  {
    try
    {
      grassTile = ImageIO.read(new File(imageDir + "Grass.png"));
      itemSheet = ImageIO.read(new File(imageDir + "all_items.png"));
      cloneTrooper = ImageIO.read(new File(imageDir + "CloneTrooper.png"));
      jediSheet = ImageIO.read(new File(imageDir + "jedi-spritesheet.png"));
      itemSheet = ImageIO
          .read(new File(imageDir + "range-tank-spritesheet.png"));
      medicSheet = ImageIO.read(new File(imageDir + "Medic.png"));
      lavaSheet = ImageIO.read(new File(imageDir + "Lava.png"));
      boulderSheet = ImageIO.read(new File(imageDir + "Boulder.png"));
      iceSheet = ImageIO.read(new File(imageDir + "Ice.png"));
      quicksandSheet = ImageIO.read(new File(imageDir + "sand.png"));
    }
    catch (IOException e)
    {
      System.out.println("Could not find an Image!");
    }

  }

}
