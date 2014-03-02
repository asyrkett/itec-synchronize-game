package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Menu
{
	private BufferedImage title, background;
	private Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 440, 100, 50);
	private Rectangle helpButton = new Rectangle(playButton.x - 215, playButton.y, 100, 50);
	private Rectangle quitButton = new Rectangle(playButton.x + 215, playButton.y, 100, 50);
	
	public Menu()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		title = loader.loadImage("/title.png");
		background = loader.loadImage("/background.png");
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