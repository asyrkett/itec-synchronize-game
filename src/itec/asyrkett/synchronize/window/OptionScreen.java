package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.Color;
import java.awt.Graphics;

public class OptionScreen extends Screen
{

	public OptionScreen(Game game)
	{
		super(game, GameMode.OPTIONS);
		background = BufferedImageLoader.loadImage("/transparent_black_background.png");
		addButton(new Button(250, 430, Texture.BUTTON_TEXT_CANCEL));
		addButton(new Button(422, 430, Texture.BUTTON_TEXT_APPLY));
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
