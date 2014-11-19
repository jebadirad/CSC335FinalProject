package unit;

import java.awt.Point;

public class UnitFactory
{
  public Unit makeUnit(String unitType, String username)
  {
    // Code to make new CloneTrooper. Need to get correct Image.
    if (unitType.equals("CloneTrooper"))
      return new CloneTrooper("CloneTrooper.png", username);

    // Code to make new CloneTrooper. Need to get correct Image.
    if (unitType.equals("Medic"))
      return new Medic("Medic.png", username);

    // Return null if unitType does not exist.
    return null;
  }
}
