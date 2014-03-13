package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This class represents the screen to render when
 * the game is in play mode
 */
public class PlayScreen extends Screen
{
	private BufferedImage menuText, resetText;
	
	/**
	 * Constructs a play screen
	 */
	public PlayScreen()
	{
		super(GameMode.PLAY);
		menuText = Texture.getButtonText(Texture.BUTTON_TEXT_MENU);
		resetText = Texture.getButtonText(Texture.BUTTON_TEXT_RESET);
	}
	
	/**
	 * Renders a background with a reset button and a menu button
	 */
	public void render(Graphics g)
	{	
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		drawImage(g, buttonBase, 660, 430);
		drawImage(g, resetText, 660, 430);
		
		drawImage(g, buttonBase, 10, 430);
		drawImage(g, menuText, 10, 430);
	}
	
	/**
	 * Returns the bounds of the reset button
	 * @return the reset button bounds
	 */
	public Rectangle getResetButtonBounds()
	{
		return new Rectangle(660, 430, buttonBase.getWidth(), buttonBase.getHeight());
	}
	
	/**
	 * Returns the bounds of the menu button
	 * @return the menu button bounds
	 */
	public Rectangle getMenuButtonBounds()
	{
		return new Rectangle(10, 430, buttonBase.getWidth(), buttonBase.getHeight());
	}
}