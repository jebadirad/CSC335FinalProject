package unit;

public class Medic extends HealerUnit
{

  public Medic(String imagestring, String username)
  {
    //
    super(imagestring, username);
  }

  public String toString()
  {
    return "Imperial Medic";
  }

  @Override
  public String getDescription()
  {
    return "Imperial Medic is a type of officer that has specialized medical training.";
  }

}
