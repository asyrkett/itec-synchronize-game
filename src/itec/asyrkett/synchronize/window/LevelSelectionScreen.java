package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.Color;
import java.awt.Graphics;

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
		addButton(new Button(40, 430, Texture.BUTTON_TEXT_MENU));
	}
	
	/**
	 * Renders a menu button and level selection tiles
	 */
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		super.render(g);
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