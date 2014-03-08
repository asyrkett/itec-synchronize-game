package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

/**
 * This class represents a sprite sheet filled with
 * different textures for game objects
 */
public class SpriteSheet
{
	private BufferedImage image;
	
	/**
	 * Constructs a sprite sheet for the specified image
	 * @param image the sprite sheet
	 */
	public SpriteSheet(BufferedImage image)
	{
		this.image = image;
	}
	
	/**
	 * Gets the sprite from the specified row and column of the sprite sheet
	 * @param row the row to grab from the sprite sheet
	 * @param column the column to grab from the sprite sheet
	 * @param width the width of the sprite to grab
	 * @param height the height of the sprite to grab
	 * @return the image of the sprite
	 */
	public BufferedImage grabImage(int row, int column, int width, int height)
	{
		return image.getSubimage(column * 64, row * 64, width, height);
	}
}