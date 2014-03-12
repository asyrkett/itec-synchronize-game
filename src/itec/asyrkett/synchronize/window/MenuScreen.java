package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This class represents the screen to render
 * when the game is in menu mode
 */
public class MenuScreen extends Screen
{
	private BufferedImage title; //the image of the game title
	
	/**
	 * Constructs a menu screen
	 */
	public MenuScreen()
	{
		super(GameMode.MENU);
		title = BufferedImageLoader.loadImage("/title.png");
	}
	
	/**
	 * Renders the menu with help, play, and quit buttons
	 */
	public void render(Graphics g)
	{	
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.drawImage(title, (Game.WIDTH - title.getWidth()) / 2, (Game.HEIGHT - title.getHeight()) / 2, title.getWidth(), title.getHeight(), null);
		
		drawImage(g, buttonBase, Game.WIDTH / 2 - buttonBase.getHeight(), 430);
		drawImage(g, playText, Game.WIDTH / 2 - playText.getHeight(), 430);
		
		drawImage(g, buttonBase, Game.WIDTH / 2 - buttonBase.getHeight() - 215, 430);
		drawImage(g, helpText, Game.WIDTH / 2 - helpText.getHeight() - 215, 430);
		
		drawImage(g, buttonBase, Game.WIDTH / 2 - buttonBase.getHeight() + 215, 430);
		drawImage(g, quitText, Game.WIDTH / 2 - quitText.getHeight() + 215, 430);
	}
	
	/**
	 * Returns the bounds of the play button
	 * @return the play button bounds
	 */
	public Rectangle getPlayButtonBounds()
	{
		return new Rectangle(Game.WIDTH / 2 - buttonBase.getHeight(), 430, buttonBase.getWidth(), buttonBase.getHeight());
	}
	
	/**
	 * Returns the bounds of the help button
	 * @return the help button bounds
	 */
	public Rectangle getHelpButtonBounds()
	{
		return new Rectangle(Game.WIDTH / 2 - buttonBase.getHeight() - 215, 430, buttonBase.getWidth(), buttonBase.getHeight());
	}
	
	/**
	 * Returns the bounds of the quit button
	 * @return the quit button bounds
	 */
	public Rectangle getQuitButtonBounds()
	{
		return new Rectangle(Game.WIDTH / 2 - buttonBase.getHeight() + 215, 430, buttonBase.getWidth(), buttonBase.getHeight());
	}
}