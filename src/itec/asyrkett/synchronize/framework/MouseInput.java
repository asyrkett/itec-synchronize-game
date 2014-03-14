package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.Button;
import itec.asyrkett.synchronize.window.Game;
import itec.asyrkett.synchronize.window.LevelSelectionScreen;
import itec.asyrkett.synchronize.window.MenuScreen;
import itec.asyrkett.synchronize.window.PlayScreen;

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
			Button playButton = menu.getButton(Texture.BUTTON_TEXT_PLAY);
			Button helpButton = menu.getButton(Texture.BUTTON_TEXT_HELP);
			Button quitButton = menu.getButton(Texture.BUTTON_TEXT_QUIT);
			
			if (playButton.getBounds().contains(mouseX, mouseY))
			{
				playButton.setClicked(true);
			}
			else if (helpButton.getBounds().contains(mouseX, mouseY))
			{
				helpButton.setClicked(true);
			}
			else if (quitButton.getBounds().contains(mouseX, mouseY))
			{
				quitButton.setClicked(true);
			}
		}
		else if (game.getGameMode() == GameMode.PLAY)
		{
			PlayScreen playScreen = (PlayScreen) game.getScreen(GameMode.PLAY);
			Button resetButton = playScreen.getButton(Texture.BUTTON_TEXT_RESET);
			Button menuButton = playScreen.getButton(Texture.BUTTON_TEXT_MENU);
			
			if (resetButton.getBounds().contains(mouseX, mouseY))
			{
				resetButton.setClicked(true);
			}
			else if (menuButton.getBounds().contains(mouseX, mouseY))
			{
				menuButton.setClicked(true);
			}
		}
		else if (game.getGameMode() == GameMode.LEVEL_SELECTION)
		{
			LevelSelectionScreen levelSelection = (LevelSelectionScreen) game.getScreen(GameMode.LEVEL_SELECTION);
			Button menuButton = levelSelection.getButton(Texture.BUTTON_TEXT_MENU);
			
			if (menuButton.getBounds().contains(mouseX, mouseY))
			{
				menuButton.setClicked(true);
			}
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (game.getGameMode() == GameMode.MENU)
		{
			MenuScreen menu = (MenuScreen) game.getScreen(GameMode.MENU);
			Button playButton = menu.getButton(Texture.BUTTON_TEXT_PLAY);
			Button helpButton = menu.getButton(Texture.BUTTON_TEXT_HELP);
			Button quitButton = menu.getButton(Texture.BUTTON_TEXT_QUIT);
			
			playButton.setClicked(false);
			helpButton.setClicked(false);
			quitButton.setClicked(false);
			
			if (playButton.getBounds().contains(mouseX, mouseY))
			{
				game.setGameMode(GameMode.PLAY);
			}
			else if (helpButton.getBounds().contains(mouseX, mouseY))
			{
			}
			else if (quitButton.getBounds().contains(mouseX, mouseY))
			{
				System.exit(1);
			}
		}
		else if (game.getGameMode() == GameMode.PLAY)
		{
			PlayScreen playScreen = (PlayScreen) game.getScreen(GameMode.PLAY);
			Button resetButton = playScreen.getButton(Texture.BUTTON_TEXT_RESET);
			Button menuButton = playScreen.getButton(Texture.BUTTON_TEXT_MENU);
			
			resetButton.setClicked(false);
			menuButton.setClicked(false);
			
			if (resetButton.getBounds().contains(mouseX, mouseY))
			{
				game.resetLevel();
			}
			else if (menuButton.getBounds().contains(mouseX, mouseY))
			{
				game.setGameMode(GameMode.MENU);
			}
		}
		else if (game.getGameMode() == GameMode.LEVEL_SELECTION)
		{
			LevelSelectionScreen levelSelection = (LevelSelectionScreen) game.getScreen(GameMode.LEVEL_SELECTION);
			Button menuButton = levelSelection.getButton(Texture.BUTTON_TEXT_MENU);
			
			menuButton.setClicked(false);
			
			if (menuButton.getBounds().contains(mouseX, mouseY))
			{
				game.setGameMode(GameMode.MENU);
			}
		}
	}
}
