package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Block;
import itec.asyrkett.synchronize.objects.Button;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class represents the screen to render when
 * the game is in play mode
 */
public class PlayScreen extends Screen
{
	private int nextBlockColor;
	
	/**
	 * Constructs a play screen
	 */
	public PlayScreen(Game game)
	{
		super(game, GameMode.PLAY);
		this.nextBlockColor = Texture.BLOCK_RED;
		
		final int padding = (266 - 64 * 2) / 3;
		
		addButton(new Button(10, 100, Texture.BUTTON_TEXT_OPTIONS));
		addButton(new Button(10, 100 + 64 + padding, Texture.BUTTON_TEXT_HELP));
		addButton(new Button(10, 100 + 64 * 2 + padding * 2, Texture.BUTTON_TEXT_LEVEL));
		addButton(new Button(10, 430, Texture.BUTTON_TEXT_MENU));
		addButton(new Button(660, 430, Texture.BUTTON_TEXT_RESET));
		addButton(new Button(660, 100, Texture.BUTTON_TEXT_QUIT));
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		game.getHandler().render(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(660, (Game.HEIGHT - 128)/2, 128, 128);
		g.setColor(Color.WHITE);
		g.drawString("NEXT", 660 + 33, (Game.HEIGHT - 128) / 2 + 20);
		
		BufferedImageLoader.drawImage(g, Texture.getBlock(game.getBlockTextureType(), nextBlockColor), 
				660 + (Block.DEFAULT_IMAGE_SIZE / 2), (Game.HEIGHT - Block.DEFAULT_IMAGE_SIZE) / 2);
		
		g.setFont(Game.FONT.deriveFont((float) 50));
		g.drawString("LEVEL " + game.getLevel(), (Game.WIDTH - 300) / 2, 580);
	}

	/**
	 * @return the nextBlockColor
	 */
	public int getNextBlockColor()
	{
		return nextBlockColor;
	}

	/**
	 * @param nextBlockColor the nextBlockColor to set
	 */
	public void setNextBlockColor(int nextBlockColor)
	{
		this.nextBlockColor = nextBlockColor;
	}	
}