package itec.aida.synchronize.window;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The window in which the game runs
 * @author Aida
 *
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
		game.setPreferredSize(new Dimension(width, height));
		game.setMaximumSize(new Dimension(width, height));
		game.setMinimumSize(new Dimension(width, height));
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(300, 50);
		//frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		
		game.start();
	}

}