package unit;

/**
 * Subclass of Unit. Makes a Healer unit which takes in values from subclass.
 * 
 * @author Kal
 * 
 */
public class HealerUnit extends Unit
{
  /**
   * Calls super for given values passed from subclass.
   * 
   * @param imagestring
   * @param username
   */
  public HealerUnit(String imagestring, String username)
  {
    // Base states for HealerUnit
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
