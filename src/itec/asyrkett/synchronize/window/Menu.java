package itec.asyrkett.synchronize.window;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu
{
	private Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 150, 100, 50);
	private Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 50, 250, 100, 50);
	private Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 50, 350, 100, 50);
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font font1 = new Font("arial", Font.BOLD, 50);
		g.setFont(font1);
		g.drawString("SYNCHRONIZE", Game.WIDTH / 2 - 180, 100);
		
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