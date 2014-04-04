package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.Block;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.image.BufferedImage;

/**
 * This class grabs the sprites from a sprite sheet as textures
 */
public final class Texture 
{
	public static final SpriteSheet blockSpriteSheet = 
			new SpriteSheet(BufferedImageLoader.loadImage("/spritesheet/block_sprite_sheet.png"), Block.DEFAULT_IMAGE_SIZE, Block.DEFAULT_IMAGE_SIZE);
	public static final SpriteSheet buttonSpriteSheet = 
			new SpriteSheet(BufferedImageLoader.loadImage("/spritesheet/button_sprite_sheet.png"), Button.DEFAULT_HEIGHT, Button.DEFAULT_WIDTH);
	
	//block sprite sheet
	public static final int BLOCK_SQUARE = 0; //row
	public static final int BLOCK_CIRCLE = 1; //row
	public static final int RADIO_BUTTON = 2; //row
	public static final int BLOCK_RED = 0; //column
	public static final int BLOCK_MAGENTA = 1; //column
	public static final int BLOCK_ORANGE = 2; //column
	public static final int BLOCK_YELLOW = 3; //column
	public static final int BLOCK_GREEN = 4; //column
	public static final int BLOCK_CYAN = 5; //column
	public static final int BLOCK_BLUE = 6; //column
	public static final int RADIO_BUTTON_UNSELECTED = 0; //column
	public static final int RADIO_BUTTON_SELECTED = 1; //column
	
	//button sprite sheet
	public static final int BUTTON_BASE = 0; //column
	public static final int BUTTON_LOCK = 1; //column
	public static final int BUTTON_TEXT_HOVERED = 2; //column
	public static final int BUTTON_TEXT_BASE = 3; //column
	
	public static final int BUTTON_TEXT_APPLY = 0; //row
	public static final int BUTTON_TEXT_CANCEL = 1; //row
	public static final int BUTTON_TEXT_HELP = 2; //row
	public static final int BUTTON_TEXT_LEVEL = 3; //row
	public static final int BUTTON_TEXT_MENU = 4; //row
	public static final int BUTTON_TEXT_OPTIONS = 5; //row
	public static final int BUTTON_TEXT_PLAY = 6; //row
	public static final int BUTTON_TEXT_QUIT = 7; //row
	public static final int BUTTON_TEXT_RESET = 8; //row
	public static final int BUTTON_LOCK_LOCKED = 0; //row
	public static final int BUTTON_LOCK_UNLOCKED = 1; //row
	
	/**
	 * Returns the image of the button base texture
	 * @return the button base image
	 */
	public static BufferedImage getButtonBase()
	{
		return buttonSpriteSheet.grabImage(BUTTON_BASE, BUTTON_BASE, 
				buttonSpriteSheet.getColWidth(), buttonSpriteSheet.getRowHeight());
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
			return buttonSpriteSheet.grabImage(text, BUTTON_TEXT_HOVERED, 
					buttonSpriteSheet.getColWidth(), buttonSpriteSheet.getRowHeight());
		else
			return buttonSpriteSheet.grabImage(text, BUTTON_TEXT_BASE, 
					buttonSpriteSheet.getColWidth(), buttonSpriteSheet.getRowHeight());
	}
	
	/**
	 * Returns the image of the specified block
	 * @param type the type of the block from the Texture class (Texture.BLOCK_CIRCLE, Texture.BLOCK_SQUARE, etc.)
	 * @param color the color of the block from the Texture class (Texture.BLOCK_RED, Texture.BLOCK_MAGENTA, etc.)
	 * @return the block texture image
	 */
	public static BufferedImage getBlock(int type, int color)
	{
		return blockSpriteSheet.grabImage(type, color,
				blockSpriteSheet.getColWidth(), blockSpriteSheet.getRowHeight());
	}
	
	/**
	 * Returns the image of the specified lock symbol for a level select button
	 * @param type the type of lock symbol, Texture.BUTTON_LOCK_UNLOCKED or Texture.BUTTON_LOCK_LOCKED
	 * @return the image of the lock symbol
	 */
	public static BufferedImage getLockSymbol(boolean locked)
	{
		if (locked)
			return buttonSpriteSheet.grabImage(BUTTON_LOCK_LOCKED, BUTTON_LOCK,
					buttonSpriteSheet.getColWidth(), buttonSpriteSheet.getRowHeight());
		else
			return buttonSpriteSheet.grabImage(BUTTON_LOCK_UNLOCKED, BUTTON_LOCK,
					buttonSpriteSheet.getColWidth(), buttonSpriteSheet.getRowHeight());
	}
	
	/**
	 * Returns the image of the specified radio button
	 * @param selected whether or not the radio button is selected
	 * @return the image of the radio button
	 */
	public static BufferedImage getRadioButton(boolean selected)
	{
		if (selected)
			return blockSpriteSheet.grabImage(RADIO_BUTTON, RADIO_BUTTON_SELECTED,
					blockSpriteSheet.getColWidth(), blockSpriteSheet.getRowHeight());
		else
			return blockSpriteSheet.grabImage(RADIO_BUTTON, RADIO_BUTTON_UNSELECTED,
					blockSpriteSheet.getColWidth(), blockSpriteSheet.getRowHeight());					
	}
}