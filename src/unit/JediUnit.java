package unit;

public abstract class JediUnit extends Unit
{

  public JediUnit(String imagestring, String username)
  {
    // Base states for JediUnit
    super(2, 8, 15, 8, imagestring, username);
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

  public abstract String getDescription();

}
