package unit;

import java.awt.Image;
import java.awt.Point;

/**
 * Subclass of RangerUnit. Makes a CloneTrooper unit which has own image.
 * 
 * @author Kal
 * 
 */
public class CloneTrooper extends RangeUnit
{
  /**
   * Takes in values from UnitFactory and calls the super Unit class.
   * 
   * @param imagestring
   * @param username
   */
  public CloneTrooper(String imagestring, String username)
  {
    super(imagestring, username);
  }

  /**
   * toString for text view. Prints out identifier string.
   */
  public String toString()
  {
    return "C";
  }

}