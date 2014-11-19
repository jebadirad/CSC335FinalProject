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

import model.Cell;
import model.GameBoard;
import model.Terrain;

public class imageview extends JPanel
{

  private BufferedImage image;
  private Point pos;
  private BufferedImage hunterImage, grass, bloodImage, goopImage, slimeImage,
      slimePitImage, wumpusImage, blackImage;
  private Cell[][] board;
  private GameBoard theModel;
  private JLabel message;

  public imageview()
  {
    setLayout(null);
    message = new JLabel("");
    message.setFont(new Font("Algerian", Font.BOLD, 32));
    message.setBounds(0, 0, 500, 500);
    message.setForeground(Color.RED);

    add(message);
    pos = new Point(0, 0);
    try
    {

      grass = ImageIO.read(new File("Images/Grass.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

   public void update(GameBoard arg0)
   {
   theModel = (GameBoard) arg0;
   board = theModel.getBoard();
   repaint();
   }

  @Override
  public void paintComponent(Graphics g)
  {
    

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    g2.setPaint(Color.WHITE);
    g2.fillRect(0, 0, 1280, 500);

    for (int i = 0; i < 10; i++)
    {
      for (int j = 0; j < 10; j++)
      {
        pos.x = j;
        pos.y = i;

        image = grass;
        g2.drawImage(image, pos.x * 50, pos.y * 50, 50, 50, null);

        if (board[i][j].getTerrain() == Terrain.Nothing)
        {
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

}
