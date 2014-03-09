package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

/**
 * This class grabs the sprites from a sprite sheet as textures
 */
public class Texture 
{
	private SpriteSheet spriteSheet;
	
	public BufferedImage[][] sprites = new BufferedImage[2][7];
	
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
		for (int row = 0; row < sprites.length; row++)
		{
			for (int col = 0; col < sprites[row].length; col++)
			{
				sprites[row][col] = spriteSheet.grabImage(row, col, 64, 64);
			}
		}
	}
}