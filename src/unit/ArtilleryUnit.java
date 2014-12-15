package unit;

public abstract class ArtilleryUnit extends Unit
{

	
  public ArtilleryUnit(String imagestring, String username)
  {
    super(10, 5, 5, 3, imagestring, username);
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
