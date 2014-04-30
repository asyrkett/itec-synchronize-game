package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * The window in which the game runs
 */
public class Window implements WindowListener
{
	private Game game;
	
	/**
	 * Constructs the window of the game
	 * @param width the window width
	 * @param height the window height
	 * @param title the window title
	 * @param game the game to run in the window
	 */
	public Window(int width, int height, String title, Game game)
	{
		this.game = game;
		
		Dimension d = new Dimension(width, height);
		game.setPreferredSize(d);
		game.setMaximumSize(d);
		game.setMinimumSize(d);
		
		JFrame frame = new JFrame(title);
		frame.setIconImage(BufferedImageLoader.loadImage("/img/icon64.png"));
		frame.addWindowListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(300, 50);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		
		game.start();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		game.saveGame();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}