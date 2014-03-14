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
	public MenuScreen()
	{
		super(GameMode.MENU);
		title = BufferedImageLoader.loadImage("/title.png");
		addButton(new Button(Game.WIDTH / 2 - 64, 430, Texture.BUTTON_TEXT_PLAY));
		addButton(new Button(Game.WIDTH / 2 - 64 - 215, 430, Texture.BUTTON_TEXT_HELP));
		addButton(new Button(Game.WIDTH / 2 - 64 + 215, 430, Texture.BUTTON_TEXT_QUIT));
	}
	
	/**
	 * Renders the menu with help, play, and quit buttons
	 */
	public void render(Graphics g)
	{	
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.drawImage(title, (Game.WIDTH - title.getWidth()) / 2, (Game.HEIGHT - title.getHeight()) / 2, title.getWidth(), title.getHeight(), null);
		
		super.render(g);
	}
}