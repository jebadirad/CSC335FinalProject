package unit;

public class ImperialMedic extends HealerUnit
{

  public ImperialMedic(String imagestring, String username)
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
