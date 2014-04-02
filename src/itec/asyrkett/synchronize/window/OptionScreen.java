package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;
import itec.asyrkett.synchronize.objects.SelectButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class OptionScreen extends Screen
{
	private SelectButton squareBlocks;
	private SelectButton circleBlocks;
	private SelectButton showTracks;
	
	public OptionScreen(Game game)
	{
		super(game, GameMode.OPTIONS);
		background = BufferedImageLoader.loadImage("/transparent_black_background.png");
		addButton(new Button(250, 430, Texture.BUTTON_TEXT_CANCEL));
		addButton(new Button(422, 430, Texture.BUTTON_TEXT_APPLY));
		
		boolean isSquare = false;
		if (game.getBlockTextureType() == Texture.BLOCK_SQUARE)
			isSquare = true;
		squareBlocks = new SelectButton(200, 150, isSquare);
		circleBlocks = new SelectButton(400, 150, !isSquare);
		showTracks = new SelectButton(200, 300, game.areGridTracksVisible());
		addButton(squareBlocks);
		addButton(circleBlocks);
		addButton(showTracks);
	}
	
	public void render(Graphics g)
	{
		game.getScreen(game.getPreviousGameMode()).render(g);
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(Game.WIDTH - 500) / 2, (int) (Game.HEIGHT - 400) / 2, 500, 400);
		g.setColor(Color.WHITE);
		g.drawRect((int)(Game.WIDTH - 500) / 2, (int) (Game.HEIGHT - 400) / 2, 500, 400);
		super.render(g);
		
		g.setColor(Color.WHITE);
		g.drawString("SQUARE", 270, 150);
		g.drawString("CIRCLE", 470, 150);
		g.drawString("SHOW TRACKS", 270, 300);
		//BufferedImageLoader.drawImage(g, Texture.getRadioButton(false), 100, 100);
	}
	
	/**
	 * Sets a button of the screen to its pressed state
	 * if it is pressed by the mouse
	 * @param e the mouse event passed
	 */
	public void mousePressed(MouseEvent e)
	{
		for (int i = 0; i < buttons.size(); i++)
		{
			Button button = buttons.get(i);
			if (button.getBounds().contains(e.getPoint()))
			{
				button.setPressed(true);
				break;
			}
		}
	}

	/**
	 * Completes the specified action when a button
	 * is released by the mouse
	 * @param e the mouse event passed
	 */
	public void mouseReleased(MouseEvent e)
	{
		setButtonsUnpressed();
		for(int i = 0; i < buttons.size(); i++)
		{
			Button button = buttons.get(i);
			if (button.getBounds().contains(e.getPoint()))
			{
				if (button instanceof SelectButton)
				{
					if (button == squareBlocks || button == circleBlocks)
					{
						squareBlocks.setSelected(!squareBlocks.isSelected());
						circleBlocks.setSelected(!circleBlocks.isSelected());
					}
					else if (button == showTracks)
					{
						showTracks.setSelected(!showTracks.isSelected());
					}
				}
				else
				{
					switch(button.getTextureText())
					{
					case Texture.BUTTON_TEXT_CANCEL:
						game.setGameMode(game.getPreviousGameMode());
						break;
					case Texture.BUTTON_TEXT_APPLY:
						if (squareBlocks.isSelected())
							game.setBlockTextureType(Texture.BLOCK_SQUARE);
						else if (circleBlocks.isSelected())
							game.setBlockTextureType(Texture.BLOCK_CIRCLE);
						
						game.setGridTracksVisible(showTracks.isSelected());
						game.setGameMode(game.getPreviousGameMode());
						break;
					}
					break;
				}
			}
		}
	}
}
