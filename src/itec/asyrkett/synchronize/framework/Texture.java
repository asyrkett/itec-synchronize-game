package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

/**
 * This class grabs the sprites from a sprite sheet as textures
 */
public class Texture 
{
	private SpriteSheet spriteSheet;
	
	public BufferedImage[] sprites = new BufferedImage[7];
	
	/**
	 * Constructs a texture object from a sprite sheet
	 */
	public Texture()
	{
		spriteSheet = new SpriteSheet(BufferedImageLoader.loadImage("/sprite_sheet.png"));
		getTextures();
	}
	
	/**
	 * Grabs the textures for the sprites from the sprite sheet
	 */
	private void getTextures()
	{
		for (int i = 0; i < 7; i++)
		{
			sprites[i] = spriteSheet.grabImage(0, i, 64, 64);
		}
	}
}