package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GameBackground
{
	private BufferedImage background;
	private Rectangle resetButton = new Rectangle(660, 440, 100, 50);
	
	public GameBackground()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		background = loader.loadImage("/background.png");
	}
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font font2 = new Font("arial", Font.BOLD, 30);
		g.setFont(font2);
		
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g2d.draw(resetButton);
		g.drawString("RESET", resetButton.x, resetButton.y + 40);
	}
	
	public Rectangle getResetButtonBounds()
	{
		return resetButton;
	}
}