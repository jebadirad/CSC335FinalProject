package unit;

/**
 * 
 * This class is the Unit Factory that makes all the units for the game. When
 * need to construct and unit this class is passed the desired paramters for
 * different units.
 * 
 * @author Kal
 * 
 */
public class UnitFactory
{
  /**
   * This is the only method in class. It decides what unit to return based on
   * unit type.
   * 
   * Returns Unit or null
   * 
   * @param unitType
   * @param username
   * @return
   */
  public Unit makeUnit(String unitType, String username)
  {
    // CODE FOR RANGE UNITS
    // Code to make new CloneTrooper.
    if (unitType.equals("CloneTrooper"))
      return new CloneTrooper("CloneTrooper.png", username);

    if (unitType.equals("BattleDroid"))
      return new BattleDroid("BattleDroid.png", username);

    // CODE FOR HEALER UNITS
    // Code to make new Medic.
    if (unitType.equals("Medic"))
      return new Medic("Medic.png", username);

    // CODE FOR JEDI UNITS
    // Code to make new Jedi LukeSkywalker.
    if (unitType.equals("LukeSkywalker"))
      return new LukeSkywalkerJedi("Luke_Skywalker_stance.png", username);

    if (unitType.equals("DarthVader"))
      return new DarthVaderJedi("DarthVader.png", username);

    // CODE FOR TANK UNITS
    // Code to make new SpiderTank.
    if (unitType.equals("SpiderTank"))
      return new SpiderTank("SpiderTank.png", username);

    // CODE FOR ???? UNITS

    // Return null if unitType does not exist.
    return null;
  }
}
