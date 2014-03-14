package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class represents the screen to render when
 * the game is in play mode
 */
public class PlayScreen extends Screen
{
	/**
	 * Constructs a play screen
	 */
	public PlayScreen()
	{
		super(GameMode.PLAY);
		addButton(new Button(660, 430, Texture.BUTTON_TEXT_RESET));
		addButton(new Button(10, 430, Texture.BUTTON_TEXT_MENU));
	}
	
	/**
	 * Renders a background with a reset button and a menu button
	 */
	public void render(Graphics g)
	{	
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		super.render(g);
	}
}