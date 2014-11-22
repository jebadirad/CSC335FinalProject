package unit;

import java.awt.Image;
import java.awt.Point;

public class RangeUnit extends Unit
{

  public RangeUnit(String imagestring, String username)
  {
    super(3, 5, 10, 5, imagestring, username);
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
