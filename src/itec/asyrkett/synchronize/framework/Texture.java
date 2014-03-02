package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

public class Texture 
{
	private SpriteSheet spriteSheet;
	
	public BufferedImage[] sprites = new BufferedImage[6];
	
	public Texture()
	{
		spriteSheet = new SpriteSheet(new BufferedImageLoader().loadImage("/sprite_sheet.png"));
		
		getTextures();
	}
	
	private void getTextures()
	{
		for (int i = 0; i < 6; i++)
		{
			sprites[i] = spriteSheet.grabImage(0, i, 32, 32);
		}
	}
}