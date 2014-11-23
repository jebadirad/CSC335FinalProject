package unit;

import java.awt.Point;

public class UnitFactory
{
  public Unit makeUnit(String unitType, String username)
  {
    // Code to make new CloneTrooper.
    if (unitType.equals("CloneTrooper"))
      return new CloneTrooper("CloneTrooper.png", username);

    // Code to make new Medic.
    if (unitType.equals("Medic"))
      return new Medic("Medic.png", username);

    // Code to make new Jedi LukeSkywalker.
    if (unitType.equals("LukeSkywalker"))
      return new LukeSkywalkerJedi("Luke_Skywalker_stance.png", username);

    // Code to make new SpiderTank.
    if (unitType.equals("SpiderTank"))
      return new SpiderTank("SpiderTank.png", username);

    // Return null if unitType does not exist.
    return null;
  }
}
