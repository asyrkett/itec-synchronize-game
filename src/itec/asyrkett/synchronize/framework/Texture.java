package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

public class Texture 
{
	private SpriteSheet spriteSheet;
	
	public BufferedImage[] sprites = new BufferedImage[7];
	
	public Texture()
	{
		spriteSheet = new SpriteSheet(new BufferedImageLoader().loadImage("/sprite_sheet.png"));
		
		getTextures();
	}
	
	private void getTextures()
	{
		for (int i = 0; i < 7; i++)
		{
			sprites[i] = spriteSheet.grabImage(0, i, 64, 64);
		}
	}
}