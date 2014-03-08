package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * This class represents the screen to render
 * when the game is in level selection mode
 */
public class LevelSelectionScreen extends Screen
{
	private Rectangle menuButton; //the button to render to the screen
	private int numLevels; //the number of levels to display
	
	/**
	 * Constructs a level selection screen with the given number of levels
	 * @param numLevels the number of levels to display
	 */
	public LevelSelectionScreen(int numLevels)
	{
		super(GameMode.LEVEL_SELECTION);
		this.numLevels = numLevels;
		menuButton = new Rectangle(40, 440, 100, 50);
	}
	
	/**
	 * Renders a menu button and level selection tiles
	 */
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font font2 = new Font("arial", Font.BOLD, 30);
		g.setFont(font2);
		
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g2d.draw(menuButton);
		g.drawString("MENU", menuButton.x + 10, menuButton.y + 40);
	}
	
	/**
	 * Returns the bounds of the menu button
	 * @return the menu button bounds
	 */
	public Rectangle getMenuButtonBounds()
	{
		return menuButton;
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