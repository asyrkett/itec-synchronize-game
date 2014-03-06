package itec.asyrkett.synchronize.window;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The window in which the game runs
 */
public class Window
{
	/**
	 * Constructs the window of the game
	 * @param width the window width
	 * @param height the window height
	 * @param title the window title
	 * @param game the game to run in the window
	 */
	public Window(int width, int height, String title, Game game)
	{
		Dimension d = new Dimension(width, height);
		game.setPreferredSize(d);
		game.setMaximumSize(d);
		game.setMinimumSize(d);
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(300, 50);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		
		game.start();
	}

}