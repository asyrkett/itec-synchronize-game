package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.window.Game;
import itec.asyrkett.synchronize.window.PlayScreen;
import itec.asyrkett.synchronize.window.LevelSelectionScreen;
import itec.asyrkett.synchronize.window.MenuScreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (game.getGameMode() == GameMode.MENU)
		{
			MenuScreen menu = (MenuScreen) game.getScreen(GameMode.MENU);
			if (menu.getPlayButtonBounds().contains(mouseX, mouseY))
			{
				game.setGameMode(GameMode.PLAY);
			}
			else if (menu.getHelpButtonBounds().contains(mouseX, mouseY))
			{
				//System.out.println("HELP!");
			}
			else if (menu.getQuitButtonBounds().contains(mouseX, mouseY))
			{
				System.exit(1);
			}
		}
		else if (game.getGameMode() == GameMode.PLAY)
		{
			PlayScreen playScreen = (PlayScreen) game.getScreen(GameMode.PLAY);
			if (playScreen.getResetButtonBounds().contains(mouseX, mouseY))
			{
				game.resetLevel();
			}
			else if (playScreen.getMenuButtonBounds().contains(mouseX, mouseY))
			{
				game.setGameMode(GameMode.MENU);
			}
		}
		else if (game.getGameMode() == GameMode.LEVEL_SELECTION)
		{
			LevelSelectionScreen levelSelection = (LevelSelectionScreen) game.getScreen(GameMode.LEVEL_SELECTION);
			if (levelSelection.getMenuButtonBounds().contains(mouseX, mouseY))
			{
				game.setGameMode(GameMode.MENU);
			}
		}
	}

	public void mouseReleased(MouseEvent e)
	{
	}
}
