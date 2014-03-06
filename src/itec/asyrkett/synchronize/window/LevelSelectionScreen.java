package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LevelSelectionScreen extends Screen
{
	private Rectangle menuButton = new Rectangle(40, 440, 100, 50);
	private int numLevels;
	
	public LevelSelectionScreen(int numLevels)
	{
		super(GameState.LEVEL_SELECTION);
		this.numLevels = numLevels;
	}
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font font2 = new Font("arial", Font.BOLD, 30);
		g.setFont(font2);
		
		g.setColor(Color.BLACK);
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		
		g2d.draw(menuButton);
		g.drawString("MENU", menuButton.x + 10, menuButton.y + 40);
	}
	
	public Rectangle getMenuButtonBounds()
	{
		return menuButton;
	}
	
	public int getNumLevels()
	{
		return numLevels;
	}
}