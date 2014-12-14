package view;
import sprites.ExplosionSprite;
import unit.SpriteObject;

// TODO 9: Look at Explosion. Notice how tiny this is!
public class Explosion extends SpriteObject{
	public Explosion(int x, int y){
		super(new ExplosionSprite(), x, y, 50);
	}
}
