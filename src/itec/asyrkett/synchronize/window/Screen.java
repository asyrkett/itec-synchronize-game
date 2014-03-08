package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This is an abstract class that renders a certain mode
 * of the game
 */
public abstract class Screen
{
	protected GameMode gameMode; //the game mode to render
	protected BufferedImage background; //the background of the screen
	
	/**
	 * Constructs a screen of the given mode and default background
	 * @param gameMode the type of game mode to render
	 */
	public Screen(GameMode gameMode)
	{
		this.gameMode = gameMode;
		background = BufferedImageLoader.loadImage("/background.png");
	}
	
	/**
	 * The graphics to render to the game
	 * @param g the graphics on which to draw
	 */
	public abstract void render(Graphics g);
	
	/**
	 * Gets the game mode of the screen
	 * @return the game mode
	 */
	public GameMode getGameMode()
	{
		return gameMode;
	}
}
