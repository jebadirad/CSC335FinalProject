package unit;

/**
 * Subclass of ArtilleryUnit. Makes a ArtilleryDroid unit which has own image.
 * 
 * @author Kal
 * 
 */
public class ArtilleryDroid extends ArtilleryUnit
{
  /**
   * Takes in values from UnitFactory and calls the super Unit class.
   * 
   * @param imagestring
   * @param username
   */
  public ArtilleryDroid(String imagestring, String username)
  {
    super(imagestring, username);
  }

  /**
   * toString for text view. Prints out identifier string.
   */
  public String toString()
  {
    return "Artillery Droid";
  }

  @Override
  public String getDescription()
  {
    return "Artillery Droids are nearly twelve meters in height and armed with a laser cannons attached to their heads.";
  }

}