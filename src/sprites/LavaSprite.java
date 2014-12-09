package sprites;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class LavaSprite extends Sprite{
	private static BufferedImage sheet;
	private static int width = 32, height = 16; // frame width & height
	private static int MAX_FRAMES = 19; // magic number
	private boolean isDead = false;

	public LavaSprite(){
		//TODO 4: Implement
		if (sheet== null) {
			try {
				sheet = ImageIO.read(new File("images" + File.separator + "lava-bubblies.png"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Image getImage() {
		//TODO 5: implement getImage()
		int row = frame%4, col = frame / 5;
		if (frame==MAX_FRAMES) {
			isDead = true;
			return null;
		} else 
			frame++;
		return sheet.getSubimage(col*width, row*height, width, height);
	}
	//TODO 6: implement remaining methods

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public boolean isFinished() {
		return isDead;
	}

	public void reset() {
		isDead = false;
		super.frame = 0;
	}

}
