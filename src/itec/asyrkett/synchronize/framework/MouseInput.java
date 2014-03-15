package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.Button;
import itec.asyrkett.synchronize.window.Game;
import itec.asyrkett.synchronize.window.Screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * This class is a mouse listener for a Game object
 */
public class MouseInput implements MouseListener
{
	private Game game;

	/**
	 * Constructs a mouse listener for the specified game
	 * @param game the game to add a mouse listener
	 */
	public MouseInput(Game game)
	{
		this.game = game;
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
		Screen screen = game.getScreen(game.getGameMode());
		LinkedList<Button> buttons = screen.getButtons();
		
		for (int i = 0; i < buttons.size(); i++)
		{
			Button button = buttons.get(i);
			if (button.getBounds().contains(e.getPoint()))
			{
				button.setClicked(true);
				break;
			}
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		Screen screen = game.getScreen(game.getGameMode());
		LinkedList<Button> buttons = screen.getButtons();
		
		screen.setButtonsUnclicked();
		for(int i = 0; i < buttons.size(); i++)
		{
			Button button = buttons.get(i);
			if (button.getBounds().contains(e.getPoint()))
			{
				switch(button.getTextureText())
				{
				case Texture.BUTTON_TEXT_PLAY:
					game.setGameMode(GameMode.PLAY);
					break;
				case Texture.BUTTON_TEXT_HELP:
					game.setGameMode(GameMode.HELP);
					break;
				case Texture.BUTTON_TEXT_MENU:
					game.setGameMode(GameMode.MENU);
					break;
				case Texture.BUTTON_TEXT_LEVEL:
					game.setGameMode(GameMode.LEVEL_SELECTION);
					break;
				case Texture.BUTTON_TEXT_QUIT:
					System.exit(1);
					break;
				case Texture.BUTTON_TEXT_RESET:
					game.resetLevel();
					break;
				}
				break;
			}
		}
	}
}
