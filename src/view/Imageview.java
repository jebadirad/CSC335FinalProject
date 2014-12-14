package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import unit.SpriteObject;
import model.Cell;
import model.Terrain;

public class Imageview extends JPanel implements Runnable
{
  private BufferedImage image;
  private Cell[][] theModel;
  private Object direction;
  private static boolean isLoaded;
  private static List<Image> sheetList;
  private static String backGroundString;
  private static BufferedImage cloneTrooper;
  private static BufferedImage lavaSheet;
  private static BufferedImage boulderSheet;
  private static BufferedImage medicSheet;
  private static BufferedImage iceSheet;
  private static BufferedImage quicksandSheet;
  private static BufferedImage backgroundSheet;
  private static BufferedImage grassSheet;
  private static BufferedImage desertSheet;
  private static BufferedImage lukeSkyWalkerJedi;
  private static BufferedImage EnemyUnitSelectedImage;
  private static BufferedImage spiderTank;
  private static BufferedImage CurrentUnitSelectedImage;
  private static BufferedImage battleDroid;
  private static BufferedImage darthVader;
  private static BufferedImage rancor;
  private static BufferedImage wampa;
  private static BufferedImage imperialmedic;
  private static BufferedImage flag;
  private static BufferedImage artilleryDroid;
  private static BufferedImage walker;
  private static BufferedImage droideka;

  private static final int WIDTH = 24;
  private static final int HEIGHT = 63;

  private static final String imageDir = System.getProperty("user.dir")
      + File.separator + "images" + File.separator;

  public Imageview(String background)
  {
    // Loads all the images into BufferedImaged with selected BackGround.
    setBackground(background);
    isLoaded = false;
    sheetList = null;
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
          /*
           * l = new Lava(j*HEIGHT+HEIGHT/2, i*WIDTH+WIDTH/2); l.start();
           * l.draw(g2); new Timer(250, new ActionListener(){
           * 
           * @Override public void actionPerformed(ActionEvent arg0){ l.start();
           * if(l.isFinished()) l.reset(); repaint(); } } ).start();
           */
        }
        if (theModel[i][j].getTerrain() == Terrain.QuickSand)
        {
          image = quicksandSheet;
          g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
        }
        if (theModel[i][j].getTerrain() == Terrain.Flag)
        {
          image = flag;
          g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
        }
        if (theModel[i][j].hasUnit())
        {
          if (theModel[i][j].getUnit() == GUI.gameboard
              .getCurrentUnitSelected())
          {
            image = CurrentUnitSelectedImage;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
          if (theModel[i][j].getUnit() == GUI.gameboard.getEnemyUnitSelected())
          {
            image = EnemyUnitSelectedImage;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
          String unitName = theModel[i][j].getUnit().getIconImage();

          if (unitName.equals("CloneTrooper.png"))
          {
            image = cloneTrooper;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }

          if (unitName.equals("BattleDroid.png"))
          {
            image = battleDroid;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }

          if (unitName.equals("ImperialMedic.png"))
          {
            image = imperialmedic;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }

          if (unitName.equals("Luke_Skywalker_stance.png"))
          {
            image = lukeSkyWalkerJedi;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }

          if (unitName.equals("DarthVader.png"))
          {
            image = darthVader;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }

          if (unitName.equals("SpiderTank.png"))
          {
            image = spiderTank;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
          if (unitName.equals("Walker.png"))
          {
            image = walker;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
          if (unitName.equals("Rancor.png"))
          {
            image = rancor;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
          if (unitName.equals("Wampa.png"))
          {
            image = wampa;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
          if (unitName.equals("ArtilleryDroid.png"))
          {
            image = artilleryDroid;
            g2.drawImage(image, j * HEIGHT, i * WIDTH, HEIGHT, WIDTH, null);
          }
          if (unitName.equals("Droideka.png"))
          {
            image = droideka;
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
      cloneTrooper = ImageIO.read(new File(imageDir + "CloneTrooper.png"));
      lukeSkyWalkerJedi = ImageIO.read(new File(imageDir
          + "Luke_Skywalker_stance.png"));
      spiderTank = ImageIO.read(new File(imageDir + "SpiderTank.png"));
      imperialmedic = ImageIO.read(new File(imageDir + "ImperialMedic.png"));
      lavaSheet = ImageIO.read(new File(imageDir + "Lava.png"));
      boulderSheet = ImageIO.read(new File(imageDir + "Boulder.png"));
      iceSheet = ImageIO.read(new File(imageDir + "Ice.png"));
      quicksandSheet = ImageIO.read(new File(imageDir + "sand.png"));
      desertSheet = ImageIO.read(new File(imageDir + "desert.png"));
      grassSheet = ImageIO.read(new File(imageDir + "Grass.png"));
      CurrentUnitSelectedImage = ImageIO.read(new File(imageDir
          + "selectSquare.png"));
      EnemyUnitSelectedImage = ImageIO.read(new File(imageDir
          + "enemySquare.png"));
      battleDroid = ImageIO.read(new File(imageDir + "BattleDroid.png"));
      darthVader = ImageIO.read(new File(imageDir + "DarthVader.png"));
      rancor = ImageIO.read(new File(imageDir + "Rancor.png"));
      wampa = ImageIO.read(new File(imageDir + "Wampa.png"));
      artilleryDroid = ImageIO.read(new File(imageDir + "ArtilleryDroid.png"));
      walker = ImageIO.read(new File(imageDir + "Walker.png"));
      flag = ImageIO.read(new File(imageDir + "Flag.png"));
      droideka = ImageIO.read(new File(imageDir + "Droideka.png"));

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
      isLoaded = false;
      System.out.println("Could not find an Image!");
    }
    isLoaded = true;
  }

  public void run()
  {
    // Direction is never set... As of now.
    if (direction.equals("North") || direction.equals("South"))
    {
      for (int i = 0; i < HEIGHT; i++)
      {
        if (direction.equals("North"))
        {
        }
        if (direction.equals("South"))
        {
        }
        repaint();
        try
        {
          Thread.sleep(10);
        }
        catch (InterruptedException e)
        {
          System.out.println("Thread generates an error.");
        }
      }
    }

    if (direction.equals("East") || direction.equals("West"))
    {
      for (int i = 0; i < WIDTH; i++)
      {
        if (direction.equals("East"))
        {
        }
        if (direction.equals("West"))
        {
        }
        repaint();
        try
        {
          Thread.sleep(10);
        }
        catch (InterruptedException e)
        {
          System.out.println("Thread generates an error.");
        }
      }
    }

  }

  public static ArrayList<Image> getSheets()
  {
    if (!isLoaded)
    {
      System.err.println("run Imageview.loadImages first");
      return null;
    }
    else
    {
      sheetList = new ArrayList<Image>();
      sheetList.add(cloneTrooper);
      sheetList.add(medicSheet);
      sheetList.add(lukeSkyWalkerJedi);
      sheetList.add(spiderTank);
      sheetList.add(battleDroid);
      sheetList.add(darthVader);
      sheetList.add(rancor);
      sheetList.add(wampa);
      sheetList.add(imperialmedic);
      sheetList.add(artilleryDroid);
      sheetList.add(walker);
      return (ArrayList<Image>) sheetList;
    }
  }

  public static Image getSheet(String s)
  {
    Image img = null;
    if (!isLoaded)
    {
      System.err.println("run Imageview.loadImages first");
      return null;
    }
    else if (s.equals("cloneTrooper"))
      img = cloneTrooper;
    else if (s.equals("medic"))
      img = medicSheet;
    else if (s.equals("lukeSkywalker"))
      img = lukeSkyWalkerJedi;
    else if (s.equals("tank"))
      img = spiderTank;
    else if (s.equals("battleDroid"))
      img = battleDroid;
    else if (s.equals("vader"))
      img = darthVader;
    else if (s.equals("rancor"))
      img = rancor;
    else if (s.equals("wampa"))
      img = wampa;
    else if (s.equals("imperialmedic"))
      img = imperialmedic;
    else if (s.equals("artilleryDroid"))
      img = artilleryDroid;
    else if (s.equals("walker"))
      img = walker;
    else
      System.out.println("Check the argument: Imageview@getSheet():img");

    return img;
  }

  public static Image getBackgroundSheet() {
	  return backgroundSheet;
  }

}
