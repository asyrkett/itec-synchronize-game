package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.window.Game;

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
	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(Game.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}