package view;
import java.awt.Image;
import unit.Unit;

public abstract class Sprite {
	public static enum State {
		IDLE, MOVING_LEFT, MOVING_RIGHT, MOVING_DOWN, MOVING_UP
	};

	protected State state; // current state
	protected int frame;   // current frame

	/**
	 * Constructs a new sprite. Sprites are initialized in the IDLE state
	 */
	public Sprite(){
		state = State.IDLE;
		frame = 0;
	}

	/**
	 * Sets the state of the sprite
	 * @param state	the new state of the sprite
	 */
	public void setState(State state){ this.state = state; }

	/**
	 * Gets the current state of the sprite
	 * @return	the current state of the sprite
	 */
	public State getState(){ return state; }

	/**
	 * Indicates whether the sprite has finished animating.
	 * 
	 * @return	false; by default all sprites never finish
	 */
	public boolean isFinished(){ return false; }

	/**
	 * Tells the sprite to restart its animation. By default, resetting a sprite does nothing.
	 */
	public void reset(){}
	
	/**
	 * Gets the current image for the sprite.
	 * @return	the current image for the sprite
	 */
	public abstract Image getImage();

	/**
	 * Gets the width of the sprite
	 * @return	the width of the sprite
	 */
	public abstract int getWidth();

	/**
	 * Gets the height of the sprite
	 * @return	the height of the sprite
	 */
	public abstract int getHeight();
}
