package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

/**
 * This class grabs the sprites from a sprite sheet as textures
 */
public class Texture 
{
	private SpriteSheet blockSpriteSheet;
	private SpriteSheet buttonSpriteSheet;

	public BufferedImage[][] blockTextures = new BufferedImage[2][7];
	public BufferedImage[][] buttonTextures = new BufferedImage[2][6];
	
	//block sprite sheet
	public static final int ROUNDED_SQUARE = 0;
	public static final int CIRCLE = 1;
	public static final int RED = 0;
	public static final int MAGENTA = 1;
	public static final int ORANGE = 2;
	public static final int YELLOW = 3;
	public static final int GREEN = 4;
	public static final int CYAN = 5;
	public static final int BLUE = 6;
	
	//button sprite sheet
	public static final int BUTTON_BASE = 0;
	public static final int TEXT = 1;
	public static final int TEXT_HELP = 0;
	public static final int TEXT_LEVEL = 1;
	public static final int TEXT_MENU = 2;
	public static final int TEXT_PLAY = 3;
	public static final int TEXT_QUIT = 4;
	public static final int TEXT_RESET = 5;

	/**
	 * Constructs a texture object from a sprite sheet
	 */
	public Texture()
	{
		blockSpriteSheet = new SpriteSheet(BufferedImageLoader.loadImage("/block_sprite_sheet.png"), 64, 64);
		buttonSpriteSheet = new SpriteSheet(BufferedImageLoader.loadImage("/button_sprite_sheet.png"), 64, 128);
		getTextures();
	}

	/**
	 * Grabs the textures for the sprites from the sprite sheet
	 */
	private void getTextures()
	{
		for (int row = 0; row < blockTextures.length; row++)
		{
			for (int col = 0; col < blockTextures[row].length; col++)
			{
				blockTextures[row][col] = blockSpriteSheet.grabImage(row, col, 64, 64);
			}
		}

		buttonTextures[0][0] = buttonSpriteSheet.grabImage(0, 0, 128, 64);
		for (int col = 0; col < buttonTextures[1].length; col++)
		{
			buttonTextures[1][col] = buttonSpriteSheet.grabImage(1, col, 128, 64);
		}
	}
}