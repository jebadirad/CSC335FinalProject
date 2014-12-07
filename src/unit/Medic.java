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
    return "M";
  }

@Override
public String getDescription() {
	// TODO Auto-generated method stub
	return "this is the description";
}

}
