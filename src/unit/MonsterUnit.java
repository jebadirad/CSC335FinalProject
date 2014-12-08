package unit;

public abstract class MonsterUnit extends Unit
{

  public MonsterUnit(String imagestring, String username)
  {
    super(1, 10, 100, 3, imagestring, username);
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
