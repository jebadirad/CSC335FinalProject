package unit;

/**
 * Subclass of MonsterUnit. Makes a Rancor unit which has own image.
 * 
 * @author Kal
 * 
 */
public class Rancor extends MonsterUnit
{
  /**
   * Takes in values from UnitFactory and calls the super Unit class.
   * 
   * @param imagestring
   * @param username
   */
  public Rancor(String imagestring, String username)
  {
    super(imagestring, username);
  }

  /**
   * toString for text view. Prints out identifier string.
   */
  public String toString()
  {
    return "Rancor";
  }

  @Override
  public String getDescription()
  {
    return "Rancor is a large carnivorous reptomammal native to the planet of Dathomir.";
  }

}