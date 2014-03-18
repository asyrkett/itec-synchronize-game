package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.Button;
import itec.asyrkett.synchronize.objects.LevelSelect;
import itec.asyrkett.synchronize.window.Game;
import itec.asyrkett.synchronize.window.LevelSelectionScreen;
import itec.asyrkett.synchronize.window.Screen;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.event.MouseInputListener;

/**
 * This class is a mouse listener for a Game object
 */
public class MouseInput implements MouseInputListener
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
		
		if (screen instanceof LevelSelectionScreen)
		{
			LevelSelectionScreen levelScreen = (LevelSelectionScreen) screen;
			LinkedList<LevelSelect> levelList = levelScreen.getLevelList();
			for (int i = 0; i < levelList.size(); i++)
			{
				LevelSelect levelSelect = levelList.get(i);
				if (levelSelect.getBounds().contains(e.getPoint()))
				{
					game.setLevel(levelSelect.getLevel());
					game.setGameMode(GameMode.PLAY);
				}
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

	public void mouseMoved(MouseEvent e)
	{
		Screen screen = game.getScreen(game.getGameMode());
		LinkedList<Button> buttons = screen.getButtons();
		
		screen.setButtonsUnhovered();
		for (int i = 0; i < buttons.size(); i++)
		{
			Button button = buttons.get(i);
			if (button.getBounds().contains(e.getPoint()))
			{
				button.setHovered(true);
				break;
			}
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{	
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
}
