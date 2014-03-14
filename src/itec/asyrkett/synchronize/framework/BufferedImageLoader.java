package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.window.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class allows loading of images from the project's resources
 */
public final class BufferedImageLoader
{	
	/**
	 * Loads and returns the image at the specified path in resources
	 * @param path the file path of the image to load
	 * @return the loaded image at the path
	 */
	public static BufferedImage loadImage(String path)
	{
		BufferedImage image = null;
		try {
			image = ImageIO.read(Game.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	/**
	 * Draws the image to the graphics with the image's default width and height
	 * @param g the graphics on which to draw
	 * @param image the image to draw
	 * @param x the x coordinate of the image's upper left corner
	 * @param y the y coordinate of the image's upper left corner
	 */
	public static void drawImage(Graphics g, BufferedImage image, int x, int y)
	{
		g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
	}
}