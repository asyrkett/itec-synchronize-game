package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.window.Game;
import itec.asyrkett.synchronize.window.Screen;

import java.awt.event.MouseEvent;

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
		screen.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e)
	{
		Screen screen = game.getScreen(game.getGameMode());
		screen.mouseReleased(e);
	}

	public void mouseMoved(MouseEvent e)
	{
		Screen screen = game.getScreen(game.getGameMode());
		screen.mouseMoved(e);
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