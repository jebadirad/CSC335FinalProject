package unit;

/**
 * Subclass of RangerUnit. Makes a BattleDroid unit which has own image.
 * 
 * @author Kal
 * 
 */
public class BattleDroid extends RangeUnit
{
  /**
   * Takes in values from UnitFactory and calls the super Unit class.
   * 
   * @param imagestring
   * @param username
   */
  public BattleDroid(String imagestring, String username)
  {
    super(imagestring, username);
  }

  /**
   * toString for text view. Prints out identifier string.
   */
  public String toString()
  {
    return "Battle Droid";
  }

  @Override
  public String getDescription()
  {
    // TODO Auto-generated method stub
    return "This is a Battle droids used to build armies. They were easier and " +
    		"cheaper to create than an all organic army. They are quite durable," +
    		" while others were nothing more than mass produced cannon fodder. ";
  }

}