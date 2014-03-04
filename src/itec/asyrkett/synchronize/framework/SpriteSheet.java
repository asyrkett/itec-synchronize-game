package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage grabImage(int row, int column, int width, int height)
	{
		return image.getSubimage(column * 64, row * 64, width, height);
	}
}