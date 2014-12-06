package unit;

public class RangeUnit extends Unit
{

  public RangeUnit(String imagestring, String username)
  {
    super(5, 5, 10, 5, imagestring, username);
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
