package unit;

/**
 * Subclass of MonsterUnit. Makes a Wampa unit which has own image.
 * 
 * @author Kal
 * 
 */
public class Wampa extends MonsterUnit
{
  /**
   * Takes in values from UnitFactory and calls the super Unit class.
   * 
   * @param imagestring
   * @param username
   */
  public Wampa(String imagestring, String username)
  {
    super(imagestring, username);
  }

  /**
   * toString for text view. Prints out identifier string.
   */
  public String toString()
  {
    return "Wampa";
  }

  @Override
  public String getDescription()
  {
    return "Wampa is an ice beast standing over two meters in height with shaggy white fur constantly stained by the blood and guts of slaughtered prey.";
  }

}