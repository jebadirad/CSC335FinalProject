package unit;

public abstract class RangeUnit extends Unit
{

  public RangeUnit(String imagestring, String username)
  {
    super(6, 6, 12, 5, imagestring, username);
  }

  public void attack(Unit victim)
  {
    victim.setHealth(victim.getHealth() - this.getDamage());

    this.setMovesLeft(0);
  }

  public void levelUp()
  {
    // NEED WORK
    setAttackRange(getAttackRange() + 1);
    setDamage(getDamage() + 1);
    setCurrentHealth(getCurrentHealth() + 4);
    setMoveRange(getMoveRange() + 2);
  }

  public abstract String getDescription();
}
