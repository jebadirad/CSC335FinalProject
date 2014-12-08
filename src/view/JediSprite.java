package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class JediSprite extends Sprite{
	private static final int width = 20, height = 25; // width and height of a single frame
	
	private static BufferedImage sheet; // the sprite sheet
	private State previousState; // the last state of the frog
	@Override
	public Image getImage() {
		if (sheet == null) // load the sprite sheet into memory
			try{
				sheet = ImageIO.read(new File("images/jedi-spritesheet.png"));
			}catch(Exception e){};
		previousState = State.MOVING_DOWN;
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
