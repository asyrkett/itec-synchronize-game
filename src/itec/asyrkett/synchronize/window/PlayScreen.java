package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;

/**
 * This class represents the screen to render when
 * the game is in play mode
 */
public class PlayScreen extends Screen
{
	/**
	 * Constructs a play screen
	 */
	public PlayScreen(Game game)
	{
		super(game, GameMode.PLAY);
		addButton(new Button(660, 430, Texture.BUTTON_TEXT_RESET));
		addButton(new Button(10, 430, Texture.BUTTON_TEXT_MENU));
	}
}