package unit;

import java.awt.Point;

public class UnitFactory
{
  public Unit makeUnit(String unittype, Point location, String username)
  {
    if (unittype.equals("CloneTrooper"))
      return new CloneTrooper("CT_IMAGE.png", location, username);

    return null;
  }
}
