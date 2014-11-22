package unit;

import java.awt.Image;
import java.awt.Point;

public class HealerUnit extends Unit
{

  public HealerUnit(String imagestring, String username)
  {
    super(2, 5, 8, 5, imagestring, username);
  }

  public void attack(Unit target)
  {
    target.setHealth(target.getHealth() + this.getDamage());

    this.setMovesLeft(0);
  }

  public void levelUp()
  {
    // TODO Auto-generated method stub

  }

}
