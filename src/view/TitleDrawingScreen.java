package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleDrawingScreen extends JPanel
{
  private JLabel jlabel;

  


  public TitleDrawingScreen(String title)
  {
    jlabel = new JLabel(title);
    jlabel.setFont(new Font("Verdana", Font.BOLD, 64));
    jlabel.setForeground(Color.RED);
    this.setVisible(true);
    jlabel.setLocation(0, 500);
    this.add(jlabel);


  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setPaint(Color.BLACK);
    g2.setFont(new Font("Courier New", Font.BOLD, 10));
    
  }
}
