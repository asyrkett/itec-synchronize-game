package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.Graphics;
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
	public MenuScreen(Game game)
	{
		super(game, GameMode.MENU);
		title = BufferedImageLoader.loadImage("/title.png");
		final int padding = (Game.WIDTH - 128 * 4) / 5;
		addButton(new Button(padding, 430, Texture.BUTTON_TEXT_HELP));
		addButton(new Button(128 + padding * 2, 430, Texture.BUTTON_TEXT_LEVEL));
		addButton(new Button(128 * 2 + padding * 3, 430, Texture.BUTTON_TEXT_PLAY));
		addButton(new Button(128 * 3 + padding * 4, 430, Texture.BUTTON_TEXT_QUIT));
	}
	
	/**
	 * Renders the menu with help, play, and quit buttons
	 */
	public void render(Graphics g)
	{	
		super.render(g);
		g.drawImage(title, (Game.WIDTH - title.getWidth()) / 2, (Game.HEIGHT - title.getHeight()) / 2, title.getWidth(), title.getHeight(), null);
	}
}