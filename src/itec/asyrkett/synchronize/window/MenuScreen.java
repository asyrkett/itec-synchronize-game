package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameState;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MenuScreen extends Screen
{
	private BufferedImage title;
	private Rectangle playButton, helpButton, quitButton;
	
	public MenuScreen()
	{
		super(GameState.MENU);
		title = BufferedImageLoader.loadImage("/title.png");
		playButton = new Rectangle(Game.WIDTH / 2 - 50, 440, 100, 50);
		helpButton = new Rectangle(playButton.x - 215, playButton.y, 100, 50);
		quitButton = new Rectangle(playButton.x + 215, playButton.y, 100, 50);
	}
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g.drawImage(title, Game.WIDTH / 2 - title.getWidth() / 2, Game.HEIGHT / 2 - title.getHeight() / 2, title.getWidth(), title.getHeight(), null);
		
		Font font2 = new Font("arial", Font.BOLD, 30);
		g.setFont(font2);
		
		g2d.draw(playButton);
		g.drawString("PLAY", playButton.x + 10, playButton.y + 40);
		
		g2d.draw(helpButton);
		g.drawString("HELP", helpButton.x + 10, helpButton.y + 40);
		
		g2d.draw(quitButton);
		g.drawString("QUIT", quitButton.x + 10, quitButton.y + 40);
	}
	
	public Rectangle getPlayButtonBounds()
	{
		return playButton;
	}
	
	public Rectangle getHelpButtonBounds()
	{
		return helpButton;
	}
	
	public Rectangle getQuitButtonBounds()
	{
		return quitButton;
	}
}