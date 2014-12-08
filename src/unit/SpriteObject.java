package unit;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import view.Sprite;
//TODO 8: Look at and discuss SpriteObject.
// Consider: Despite having no abstract methods, this class is abstract. Why would that be necessary?
public abstract class SpriteObject {
	public Point position; // position of the sprite object
	private Sprite sprite; // the object's sprite
	private Image frame; // the last frame of the sprite
	private Timer t; // timer used to update the frame
	
	/**
	 * Constructs a new SpriteObject.
	 * 
	 * @param sprite	sprite to use for this object
	 * @param x	x position of the object
	 * @param y	y position of the object
	 * @param delay	delay in switching animation frames
	 */
	protected SpriteObject(Sprite sprite, int x, int y, int delay){
		this.sprite = sprite;
		this.position = new Point(x, y);
		frame = sprite.getImage();
		
		// this timer is constructed using an anonymous class
		t = new Timer(delay, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextFrame();
			}
		});
	}
	
	/**
	 * Get the sprite for this SpriteObject
	 * @return	the object's sprite
	 */
	public Sprite getSprite(){
		return sprite;
	}
	
	/**
	 * Sets the SpriteObject to be at the given position
	 * @param x	the new x position
	 * @param y	the new y position
	 */
	public void setPosition(int x, int y){
		position.setLocation(x, y);
	}

	/**
	 * Draws the SpriteObject on the given context
	 * @param g	the graphics context to render to
	 */
	public void draw(Graphics g){
		if (sprite != null && !sprite.isFinished()) // only render if not finished
			g.drawImage(frame, position.x  - sprite.getWidth()/2, position.y - sprite.getHeight()/2, null);
	}
	
	/**
	 * Move the SpriteObject left
	 */
	public void moveLeft(){
		if (sprite.getState() != Sprite.State.MOVING_LEFT){
			sprite.setState(Sprite.State.MOVING_LEFT);
			nextFrame();
		}
		
		position.translate(-5, 0);
	}
	
	/**
	 * Move the SpriteObject right
	 */
	public void moveRight(){
		if (sprite.getState() != Sprite.State.MOVING_RIGHT){
			sprite.setState(Sprite.State.MOVING_RIGHT);
			nextFrame();
		}
		
		position.translate(5, 0);
	}
	
	/**
	 * Move the SpriteObject up
	 */
	public void moveUp(){
		if (sprite.getState() != Sprite.State.MOVING_UP){
			sprite.setState(Sprite.State.MOVING_UP);
			nextFrame();
		}
		
		position.translate(0, -5);
	}
	
	/**
	 * Move the SpriteObject down
	 */
	public void moveDown(){
		if (sprite.getState() != Sprite.State.MOVING_DOWN){
			sprite.setState(Sprite.State.MOVING_DOWN);
			nextFrame();
		}
		
		position.translate(0, 5);
	}
	
	/**
	 * Stop moving the SpriteObject
	 */
	public void moveStop(){
		if (sprite.getState() != Sprite.State.IDLE){
			sprite.setState(Sprite.State.IDLE);
			nextFrame();
		}
	}
	
	/**
	 * Start animating the SpriteObject
	 */
	public void start(){
		if (!t.isRunning())
			t.start();
		sprite.reset();
	}
	
	/**
	 * Stop animating the sprite
	 */
	public void stop(){
		t.stop();
	}
	
	/**
	 * Advance the frame to the next image
	 */
	private void nextFrame(){
		frame = sprite.getImage();
	}
}
