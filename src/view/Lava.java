package view;
import sprites.LavaSprite;
import unit.SpriteObject;

public class Lava extends SpriteObject{
	static LavaSprite l = new LavaSprite();
	protected Lava(int x, int y) {
		super(l, x, y, 50);
	}

	public void reset() {
		l.reset();
	}

	public boolean isFinished() {
		if(l.isFinished())
		return true;
		return false;
	}
}
