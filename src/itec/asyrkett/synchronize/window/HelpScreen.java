package itec.asyrkett.synchronize.window;

import java.awt.Color;
import java.awt.Graphics;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;

/**
 * This class is a screen that displays
 * directions on how to play the game.
 */
public class HelpScreen extends Screen
{
	public HelpScreen(Game game)
	{
		super(game, GameMode.HELP);
		background = BufferedImageLoader.loadImage("/img/transparent_black_background.png");
		addButton(new Button((Game.WIDTH - Button.DEFAULT_WIDTH) / 2, 430, Texture.BUTTON_TEXT_CANCEL));
	}
	
	public void render(Graphics g)
	{
		game.getScreen(game.getPreviousGameMode()).render(g);
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(Game.WIDTH - 500) / 2, (int) (Game.HEIGHT - 400) / 2, 500, 400);
		g.setColor(Color.WHITE);
		g.drawRect((int)(Game.WIDTH - 500) / 2, (int) (Game.HEIGHT - 400) / 2, 500, 400);
		super.render(g);
	}
}