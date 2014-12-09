package unit;

/**
 * Subclass of ArtilleryUnit. Makes a ATRT unit which has own image.
 * 
 * @author Kal
 * 
 */
public class Walker extends ArtilleryUnit
{
  /**
   * Takes in values from UnitFactory and calls the super Unit class.
   * 
   * @param imagestring
   * @param username
   */
  public Walker(String imagestring, String username)
  {
    super(imagestring, username);
  }

  /**
   * toString for text view. Prints out identifier string.
   */
  public String toString()
  {
    return "Walker";
  }

  @Override
  public String getDescription()
  {
    return "Walkers are a slow moving machine which can do damage at a long range but can be taken down when in range.";
  }

}