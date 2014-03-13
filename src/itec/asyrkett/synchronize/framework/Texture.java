package itec.asyrkett.synchronize.framework;

import java.awt.image.BufferedImage;

/**
 * This class grabs the sprites from a sprite sheet as textures
 */
public final class Texture 
{
	public static final SpriteSheet blockSpriteSheet = 
			new SpriteSheet(BufferedImageLoader.loadImage("/block_sprite_sheet.png"), 64, 64);
	public static final SpriteSheet buttonSpriteSheet = 
			new SpriteSheet(BufferedImageLoader.loadImage("/button_sprite_sheet.png"), 64, 128);
	
	//block sprite sheet
	public static final int BLOCK_SQUARE = 0;
	public static final int BLOCK_CIRCLE = 1;
	public static final int BLOCK_RED = 0;
	public static final int BLOCK_MAGENTA = 1;
	public static final int BLOCK_ORANGE = 2;
	public static final int BLOCK_YELLOW = 3;
	public static final int BLOCK_GREEN = 4;
	public static final int BLOCK_CYAN = 5;
	public static final int BLOCK_BLUE = 6;
	
	//button sprite sheet
	public static final int BUTTON_BASE = 0;
	public static final int BUTTON_TEXT = 1;
	public static final int BUTTON_TEXT_HELP = 0;
	public static final int BUTTON_TEXT_LEVEL = 1;
	public static final int BUTTON_TEXT_MENU = 2;
	public static final int BUTTON_TEXT_PLAY = 3;
	public static final int BUTTON_TEXT_QUIT = 4;
	public static final int BUTTON_TEXT_RESET = 5;
	
	public static BufferedImage getButtonBase()
	{
		return buttonSpriteSheet.grabImage(BUTTON_BASE, BUTTON_BASE, 128, 64);
	}
	
	public static BufferedImage getButtonText(int text)
	{
		return buttonSpriteSheet.grabImage(BUTTON_TEXT, text, 128, 64);
	}
	
	public static BufferedImage getBlock(int type, int color)
	{
		return blockSpriteSheet.grabImage(type, color, 64, 64);
	}
}