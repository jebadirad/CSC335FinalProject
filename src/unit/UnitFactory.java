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
    // Code to make new BattleDroid.
    if (unitType.equals("BattleDroid"))
      return new BattleDroid("BattleDroid.png", username);

    // CODE FOR HEALER UNITS
    // Code to make new Medic.
    if (unitType.equals("ImperialMedic"))
      return new ImperialMedic("ImperialMedic.png", username);

    // CODE FOR JEDI UNITS
    // Code to make new Jedi LukeSkywalker.
    if (unitType.equals("LukeSkywalker"))
      return new LukeSkywalkerJedi("Luke_Skywalker_stance.png", username);
    // Code to make new Jedi DarthVader.
    if (unitType.equals("DarthVader"))
      return new DarthVaderJedi("DarthVader.png", username);

    // CODE FOR TANK UNITS
    // Code to make new SpiderTank.
    if (unitType.equals("SpiderTank"))
      return new SpiderTank("SpiderTank.png", username);
    // Code to make new Droideka.
    if (unitType.equals("Droideka"))
      return new Droideka("Droideka.png", username);

    // CODE FOR MONSTER UNITS
    // Code to make new Rancor.
    if (unitType.equals("Rancor"))
      return new Rancor("Rancor.png", username);
    // Code to make new Wampa.
    if (unitType.equals("Wampa"))
      return new Wampa("Wampa.png", username);

    // CODE FOR ARTILARY UNITS

    // Return null if unitType does not exist.
    return null;
  }
}
