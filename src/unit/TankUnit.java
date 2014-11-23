package unit;

import java.awt.Image;
import java.awt.Point;

public class TankUnit extends Unit
{

  public TankUnit(String imagestring, String username)
  {
    super(2, 10, 20, 3, imagestring, username);
  }

  public void attack(Unit victim)
  {
    victim.setHealth(victim.getHealth() - this.getDamage());

    this.setMovesLeft(0);
  }

  public void levelUp()
  {
    // TODO Auto-generated method stub

  }

}
