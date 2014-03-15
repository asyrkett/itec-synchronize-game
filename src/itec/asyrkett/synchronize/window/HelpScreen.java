package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.Color;
import java.awt.Graphics;

public class HelpScreen extends Screen
{
	public HelpScreen()
	{
		super(GameMode.HELP);
		addButton(new Button(40, 430, Texture.BUTTON_TEXT_MENU));
	}

	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		super.render(g);
	}
}