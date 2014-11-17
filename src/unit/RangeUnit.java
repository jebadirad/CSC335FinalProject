package unit;

import java.awt.Image;
import java.awt.Point;

public class RangeUnit extends Unit
{

  public RangeUnit(String imagestring, String username)
  {
    super(10, 5, 10, 5, imagestring, username);

  }

  public void attack(Unit victim)
  {
    victim.setHealth(victim.getHealth() - this.getDamage());
  }

  public void levelUp()
  {
    // TODO Auto-generated method stub

  }

}
