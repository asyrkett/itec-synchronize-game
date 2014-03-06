package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Screen
{
	protected GameState gameState;
	protected BufferedImage background;
	
	public Screen(GameState gameState)
	{
		this.gameState = gameState;
		background = BufferedImageLoader.loadImage("/background.png");
	}
	
	public abstract void render(Graphics g);
	
	public GameState getGameState()
	{
		return gameState;
	}
}
