package unit;

public class DarthVaderJedi extends JediUnit
{

  public DarthVaderJedi(String imagestring, String username)
  {
    super(imagestring, username);
  }

  public String toString()
  {
    return "Darth Vader";
  }

  @Override
  public String getDescription()
  {
    return "Darth Vader under the dark side of the Force seeks to crush the fledgling Rebel Alliance.";
  }

}
