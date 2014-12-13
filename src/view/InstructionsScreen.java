package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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

public class InstructionsScreen extends JPanel
{
  private static final String imageDir = System.getProperty("user.dir")
      + File.separator + "images" + File.separator;

  private BufferedImage img;

  public InstructionsScreen()
  {
    try
    {
      img = ImageIO.read(new File(imageDir + "Instruction.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    this.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
    this.setVisible(true);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    // paint the background image and scale it to fill the entire space
    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
  }

}
