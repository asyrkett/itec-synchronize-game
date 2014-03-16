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
	public static final int BLOCK_SQUARE = 0; //row
	public static final int BLOCK_CIRCLE = 1; //row
	public static final int BLOCK_RED = 0; //column
	public static final int BLOCK_MAGENTA = 1; //column
	public static final int BLOCK_ORANGE = 2; //column
	public static final int BLOCK_YELLOW = 3; //column
	public static final int BLOCK_GREEN = 4; //column
	public static final int BLOCK_CYAN = 5; //column
	public static final int BLOCK_BLUE = 6; //column
	
	//button sprite sheet
	public static final int BUTTON_BASE = 0; //row
	public static final int BUTTON_TEXT_HOVERED = 1; //row
	public static final int BUTTON_TEXT_BASE = 2; //row
	public static final int BUTTON_TEXT_HELP = 0; //column
	public static final int BUTTON_TEXT_LEVEL = 1; //column
	public static final int BUTTON_TEXT_MENU = 2; //column
	public static final int BUTTON_TEXT_PLAY = 3; //column
	public static final int BUTTON_TEXT_QUIT = 4; //column
	public static final int BUTTON_TEXT_RESET = 5; //column
	
	/**
	 * Returns the image of the button base texture
	 * @return the button base image
	 */
	public static BufferedImage getButtonBase()
	{
		return buttonSpriteSheet.grabImage(BUTTON_BASE, BUTTON_BASE, 128, 64);
	}
	
	/**
	 * Returns the image of the specified text for a button
	 * @param text the text texture from the Texture class (Texture.BUTTON_TEXT_MENU, Texture.BUTTON_TEXT_RESET, etc.)
	 * @param hovered the version of the text if hovered over by the mouse
	 * @return the button text image
	 */
	public static BufferedImage getButtonText(int text, boolean hovered)
	{
		if (hovered)
			return buttonSpriteSheet.grabImage(BUTTON_TEXT_HOVERED, text, 128, 64);
		else
			return buttonSpriteSheet.grabImage(BUTTON_TEXT_BASE, text, 128, 64);
	}
	
	/**
	 * Returns the image of the specified block
	 * @param type the type of the block from the Texture class (Texture.BLOCK_CIRCLE, Texture.BLOCK_SQUARE, etc.)
	 * @param color the color of the block from the Texture class (Texture.BLOCK_RED, Texture.BLOCK_MAGENTA, etc.)
	 * @return the block texture image
	 */
	public static BufferedImage getBlock(int type, int color)
	{
		return blockSpriteSheet.grabImage(type, color, 64, 64);
	}
}