package unit;

public class SpiderTank extends TankUnit
{

  public SpiderTank(String imagestring, String username)
  {
    super(imagestring, username);
  }

  public String toString()
  {
    return "Imperial Spider Droid";
  }

  @Override
  public String getDescription()
  {
    return "Imperial Spiders are security droids deployed aboard the Death Star. They are slow but hard to take down.";
  }

}
