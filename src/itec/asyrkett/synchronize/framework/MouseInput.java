package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.window.Game;
import itec.asyrkett.synchronize.window.PlayScreen;
import itec.asyrkett.synchronize.window.LevelSelectionScreen;
import itec.asyrkett.synchronize.window.MenuScreen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private Game game;

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

		if (game.getState() == GameState.MENU)
		{
			MenuScreen menu = (MenuScreen) game.getScreen(GameState.MENU);
			if (menu.getPlayButtonBounds().contains(mouseX, mouseY))
			{
				game.setState(GameState.PLAY);
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
		else if (game.getState() == GameState.PLAY)
		{
			PlayScreen playScreen = (PlayScreen) game.getScreen(GameState.PLAY);
			if (playScreen.getResetButtonBounds().contains(mouseX, mouseY))
			{
				game.resetLevel();
			}
			else if (playScreen.getMenuButtonBounds().contains(mouseX, mouseY))
			{
				game.setState(GameState.MENU);
			}
		}
		else if (game.getState() == GameState.LEVEL_SELECTION)
		{
			LevelSelectionScreen levelSelection = (LevelSelectionScreen) game.getScreen(GameState.LEVEL_SELECTION);
			if (levelSelection.getMenuButtonBounds().contains(mouseX, mouseY))
			{
				game.setState(GameState.MENU);
			}
		}
	}

	public void mouseReleased(MouseEvent e)
	{

	}
}
