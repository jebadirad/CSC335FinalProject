package unit;

public class Droideka extends TankUnit
{

  public Droideka(String imagestring, String username)
  {
    super(imagestring, username);
  }

  public String toString()
  {
    return "Droideka";
  }

  @Override
  public String getDescription()
  {
    return "Droideka has a resilience to firepower, as well as the fear it instills in even the most powerful Jedi.";
  }

}
