package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class represents the screen to render
 * when the game is in level selection mode
 */
public class LevelSelectionScreen extends Screen
{
	private int numLevels; //the number of levels to display
	
	/**
	 * Constructs a level selection screen with the given number of levels
	 * @param numLevels the number of levels to display
	 */
	public LevelSelectionScreen(int numLevels)
	{
		super(GameMode.LEVEL_SELECTION);
		this.numLevels = numLevels;
	}
	
	/**
	 * Renders a menu button and level selection tiles
	 */
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		drawImage(g, buttonBase, 40, 430);
		drawImage(g, menuText, 40, 430);
	}
	
	/**
	 * Returns the bounds of the menu button
	 * @return the menu button bounds
	 */
	public Rectangle getMenuButtonBounds()
	{
		return new Rectangle(40, 430, buttonBase.getWidth(), buttonBase.getHeight());
	}
	
	/**
	 * Returns the number of levels the screen displays
	 * @return the number of levels
	 */
	public int getNumLevels()
	{
		return numLevels;
	}
}