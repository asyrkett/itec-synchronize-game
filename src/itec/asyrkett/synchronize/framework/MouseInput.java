package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.window.Game;
import itec.asyrkett.synchronize.window.Menu;

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
		
		Menu menu = game.getMenu();
		if (menu.getPlayButtonBounds().contains(mouseX, mouseY))
		{
			game.setState(GameState.GAME);
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

	public void mouseReleased(MouseEvent e)
	{
		
	}
}
