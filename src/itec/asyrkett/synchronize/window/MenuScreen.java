package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This class represents the screen to render
 * when the game is in menu mode
 */
public class MenuScreen extends Screen
{
	private BufferedImage title; //the image of the game title
	private Rectangle playButton, helpButton, quitButton; //the buttons rendered to the screen
	
	/**
	 * Constructs a menu screen
	 */
	public MenuScreen()
	{
		super(GameMode.MENU);
		title = BufferedImageLoader.loadImage("/title.png");
		playButton = new Rectangle(Game.WIDTH / 2 - 50, 440, 100, 50);
		helpButton = new Rectangle(playButton.x - 215, playButton.y, 100, 50);
		quitButton = new Rectangle(playButton.x + 215, playButton.y, 100, 50);
	}
	
	/**
	 * Renders the menu with help, play, and quit buttons
	 */
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.drawImage(title, (Game.WIDTH - title.getWidth()) / 2, (Game.HEIGHT - title.getHeight()) / 2, title.getWidth(), title.getHeight(), null);
		
		Font font2 = new Font("arial", Font.BOLD, 30);
		g.setFont(font2);
		
		g2d.draw(playButton);
		g.drawString("PLAY", playButton.x + 10, playButton.y + 40);
		
		g2d.draw(helpButton);
		g.drawString("HELP", helpButton.x + 10, helpButton.y + 40);
		
		g2d.draw(quitButton);
		g.drawString("QUIT", quitButton.x + 10, quitButton.y + 40);
	}
	
	/**
	 * Returns the bounds of the play button
	 * @return the play button bounds
	 */
	public Rectangle getPlayButtonBounds()
	{
		return playButton;
	}
	
	/**
	 * Returns the bounds of the help button
	 * @return the help button bounds
	 */
	public Rectangle getHelpButtonBounds()
	{
		return helpButton;
	}
	
	/**
	 * Returns the bounds of the quit button
	 * @return the quit button bounds
	 */
	public Rectangle getQuitButtonBounds()
	{
		return quitButton;
	}
}