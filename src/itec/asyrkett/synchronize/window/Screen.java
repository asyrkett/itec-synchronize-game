package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This is an abstract class that renders a certain mode
 * of the game
 */
public abstract class Screen
{
	protected static BufferedImage background; //the background of the screen
	protected static final Texture tex = Game.TEXTURE;
	protected static final BufferedImage buttonBase = tex.buttonTextures[Texture.BUTTON_BASE][Texture.BUTTON_BASE];
	protected static final BufferedImage menuText = tex.buttonTextures[Texture.TEXT][Texture.TEXT_MENU];
	protected static final BufferedImage helpText = tex.buttonTextures[Texture.TEXT][Texture.TEXT_HELP];
	protected static final BufferedImage levelText = tex.buttonTextures[Texture.TEXT][Texture.TEXT_LEVEL];
	protected static final BufferedImage quitText = tex.buttonTextures[Texture.TEXT][Texture.TEXT_QUIT];
	protected static final BufferedImage playText = tex.buttonTextures[Texture.TEXT][Texture.TEXT_PLAY];
	protected static final BufferedImage resetText = tex.buttonTextures[Texture.TEXT][Texture.TEXT_RESET];

	protected GameMode gameMode; //the game mode to render
	
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
	
	/**
	 * Draws the image to the graphics with the image's default width and height
	 * @param g the graphics on which to draw
	 * @param image the image to draw
	 * @param x the x coordinate of the image's upper left corner
	 * @param y the y coordinate of the image's upper left corner
	 */
	public static void drawImage(Graphics g, BufferedImage image, int x, int y)
	{
		g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
	}
}
