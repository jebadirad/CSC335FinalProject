package view;
import sprites.ExplosionSprite;
import unit.SpriteObject;

// TODO 9: Look at Explosion. Notice how tiny this is!
public class Explosion extends SpriteObject{
	static ExplosionSprite e = new ExplosionSprite();
	public Explosion(int x, int y){
		super(e, x, y, 50);
	}
}
