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
  private static String backGroundString;
  private static BufferedImage itemSheet;
  private static BufferedImage cloneTrooper;
  private static BufferedImage jediSheet;
  private static BufferedImage lavaSheet;
  private static BufferedImage boulderSheet;
  private static BufferedImage medicSheet;
  private static BufferedImage iceSheet;
  private static BufferedImage quicksandSheet;
  private static BufferedImage backgroundSheet;
  private static BufferedImage grassSheet;
  private static BufferedImage desertSheet;
  private static BufferedImage lukeSkyWalkerJedi;
  private static BufferedImage spiderTank;
  private static BufferedImage selectedImage;
  private static final int WIDTH = 23;
  private static final int HEIGHT = 64;

  private static final String imageDir = System.getProperty("user.dir")
      + File.separator + "images" + File.separator;

  public Imageview(String background)
  {
    // Loads all the images into BufferedImaged with selected BackGround.
    setBackground(background);
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
        g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);

        if (theModel[i][j].getTerrain() == Terrain.Boulder)
        {
          image = boulderSheet;
          g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
        }
        if (theModel[i][j].getTerrain() == Terrain.Ice)
        {
          image = iceSheet;
          g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
        }
        if (theModel[i][j].getTerrain() == Terrain.Lava)
        {
          image = lavaSheet;
          g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
        }
        if (theModel[i][j].getTerrain() == Terrain.QuickSand)
        {
          image = quicksandSheet;
          g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
        }
        if (theModel[i][j].hasUnit())
        {
          if (theModel[i][j].getUnit() == GUI.gameboard
              .getCurrentUnitSelected())
          {
            image = selectedImage;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
          String unitName = theModel[i][j].getUnit().getIconImage();
          if (unitName.equals("CloneTrooper.png"))
          {
            image = cloneTrooper;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }

          if (unitName.equals("Medic.png"))
          {
            image = medicSheet;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }

          if (unitName.equals("Luke_Skywalker_stance.png"))
          {
            image = lukeSkyWalkerJedi;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }

          if (unitName.equals("SpiderTank.png"))
          {
            image = spiderTank;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
        }

      }
    }
  }

  public static void setBackground(String imagestring)
  {
    backGroundString = imagestring;

  }

  public static void loadImages()
  {
    try
    {
      itemSheet = ImageIO.read(new File(imageDir + "all_items.png"));
      cloneTrooper = ImageIO.read(new File(imageDir + "CloneTrooper.png"));
      lukeSkyWalkerJedi = ImageIO.read(new File(imageDir
          + "Luke_Skywalker_stance.png"));
      spiderTank = ImageIO.read(new File(imageDir + "SpiderTank.png"));
      itemSheet = ImageIO
          .read(new File(imageDir + "range-tank-spritesheet.png"));
      medicSheet = ImageIO.read(new File(imageDir + "Medic.png"));
      lavaSheet = ImageIO.read(new File(imageDir + "Lava.png"));
      boulderSheet = ImageIO.read(new File(imageDir + "Boulder.png"));
      iceSheet = ImageIO.read(new File(imageDir + "Ice.png"));
      quicksandSheet = ImageIO.read(new File(imageDir + "sand.png"));
      desertSheet = ImageIO.read(new File(imageDir + "desert.png"));
      grassSheet = ImageIO.read(new File(imageDir + "Grass.png"));
      selectedImage = ImageIO.read(new File(imageDir + "selectSquare.png"));
      if (backGroundString.equals("Grass.png"))
      {
        backgroundSheet = grassSheet;

      }
      if (backGroundString.equals("desert.png"))
      {
        backgroundSheet = desertSheet;
      }

    }
    catch (IOException e)
    {
      System.out.println("Could not find an Image!");
    }

  }

}
