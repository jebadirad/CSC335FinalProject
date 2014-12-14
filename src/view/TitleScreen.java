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

public class TitleScreen extends JPanel
{
  private static final String imageDir = System.getProperty("user.dir")
      + File.separator + "images" + File.separator;

  private BufferedImage img;

  private JLabel jlabel;

  private JButton vsAI;
  private JButton vsHuman;

  private JLabel byWho;

  public TitleScreen()
  {
    try
    {
      img = ImageIO.read(new File(imageDir + "StarBackground.png"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    jlabel = new JLabel("Star Wars TRPG");
    jlabel.setFont(new Font("Verdana", 0, 100));
    jlabel.setForeground(Color.RED);

    byWho = new JLabel("By Aaron, Cramer, Ebadirad, Garcia");
    byWho.setFont(new Font("Verdana", 0, 12));
    byWho.setForeground(Color.RED);
    this.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
    this.setVisible(true);
    jlabel.setLocation(0, 300);
    this.add(jlabel);

    byWho.setLocation(0, 600);
    this.add(byWho);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    // paint the background image and scale it to fill the entire space
    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
  }

  private class SelectionButtonListener implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      // TODO Auto-generated method stub
      if (e.getSource() == vsAI)
      {

      }

      if (e.getSource() == vsHuman)
      {

      }
    }

  }
}
