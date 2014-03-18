package itec.asyrkett.synchronize.window;

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
		addButton(new Button(40, 430, Texture.BUTTON_TEXT_MENU));
	}
}