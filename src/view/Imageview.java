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
    g2.setPaint(Color.BLACK);
    g2.setFont(new Font("Courier New", Font.BOLD, 17));
    int y = 0;
    for (int i = 0; i < 10; i++)
    {
      for (int j = 0; j < 10; j++)
      {
        // pos.x = j;
        // pos.y = i;

        if (theModel[i][j].getTerrain() == Terrain.Nothing)
        {
          image = grassTile;
          g2.drawImage(image, pos.x * 50, pos.y * 50, 50, 50, null);
        }

        else
        {

        }
        // pos.x = theModel.getHunter().y;
        // pos.y = theModel.getHunter().x;
        // Point hunter = new Point(theModel.getHunter().x,
        // theModel.getHunter().y);
        // image = hunterImage;
        // g.drawImage(image, pos.x * 50, pos.y * 50, 50, 50, null);

        // if (theModel.isGameOver() == false)
        // {
        // message.setText(theModel.gameOverReason);
        // }
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
