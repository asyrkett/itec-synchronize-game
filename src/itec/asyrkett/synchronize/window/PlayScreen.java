package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * This class represents the screen to render when
 * the game is in play mode
 */
public class PlayScreen extends Screen
{
	private Rectangle resetButton, menuButton; //the buttons rendered to the screen
	
	/**
	 * Constructs a play screen
	 */
	public PlayScreen()
	{
		super(GameMode.PLAY);
		resetButton = new Rectangle(660, 440, 100, 50);
		menuButton = new Rectangle(40, 440, 100, 50);
	}
	
	/**
	 * Renders a background with a reset button and a menu button
	 */
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font font2 = new Font("arial", Font.BOLD, 30);
		g.setFont(font2);
		
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g2d.draw(resetButton);
		g.drawString("RESET", resetButton.x, resetButton.y + 40);
		
		g2d.draw(menuButton);
		g.drawString("MENU", menuButton.x + 10, menuButton.y + 40);
	}
	
	/**
	 * Returns the bounds of the reset button
	 * @return the reset button bounds
	 */
	public Rectangle getResetButtonBounds()
	{
		return resetButton;
	}
	
	/**
	 * Returns the bounds of the menu button
	 * @return the menu button bounds
	 */
	public Rectangle getMenuButtonBounds()
	{
		return menuButton;
	}
}