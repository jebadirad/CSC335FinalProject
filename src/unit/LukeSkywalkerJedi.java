package unit;

public class LukeSkywalkerJedi extends JediUnit
{

  public LukeSkywalkerJedi(String imagestring, String username)
  {
    super(imagestring, username);
  }

  public String toString()
  {
    return "Luke Skywalker";
  }

  @Override
  public String getDescription()
  {
    return "Luke Skywalker is a Force-sensitive Human male who helped found the New Republic, as well as the New Jedi Order.";
  }

}
