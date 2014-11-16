package unit;

import java.awt.Image;
import java.awt.Point;

public class CloneTrooper extends RangeUnit
{

  public CloneTrooper(Image iconImage, Point location, String username)
  {
    super(iconImage, location, username);
  }

  public String toString()
  {
    return "CT";
  }

}
