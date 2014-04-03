package itec.asyrkett.synchronize.window;

import itec.asyrkett.synchronize.framework.BufferedImageLoader;
import itec.asyrkett.synchronize.framework.GameMode;
import itec.asyrkett.synchronize.framework.Texture;
import itec.asyrkett.synchronize.objects.Button;
import itec.asyrkett.synchronize.objects.SelectButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class OptionScreen extends Screen
{
	private SelectButton squareBlocks, circleBlocks;
	private SelectButton showTracks, showCells;
	
	public OptionScreen(Game game)
	{
		super(game, GameMode.OPTIONS);
		background = BufferedImageLoader.loadImage("/transparent_black_background.png");
		addButton(new Button(250, 430, Texture.BUTTON_TEXT_CANCEL));
		addButton(new Button(422, 430, Texture.BUTTON_TEXT_APPLY));
		
		squareBlocks = new SelectButton(200, 170, (game.getBlockTextureType() == Texture.BLOCK_SQUARE));
		circleBlocks = new SelectButton(400, 170, !squareBlocks.isSelected());
		showTracks = new SelectButton(200, 300, game.getGridTracksVisible());
		showCells = new SelectButton(400, 300, game.getGridCellsVisible());
		addButton(squareBlocks);
		addButton(circleBlocks);
		addButton(showTracks);
		addButton(showCells);
	}
	
	public void render(Graphics g)
	{
		game.getScreen(game.getPreviousGameMode()).render(g);
		
		g.setColor(Color.BLACK);
		g.fillRect((int)(Game.WIDTH - 500) / 2, (int) (Game.HEIGHT - 400) / 2, 500, 400);
		super.render(g);
		
		g.setColor(Color.WHITE);
		g.drawRect((int)(Game.WIDTH - 500) / 2, (int) (Game.HEIGHT - 400) / 2, 500, 400);
		
		Font labelFont = Game.FONT.deriveFont((float) 30);
		g.setFont(labelFont);
		g.drawString("BLOCKS", (Game.WIDTH - 140) / 2, 140);
		g.drawString("GRID", (Game.WIDTH - 100) / 2, 270);
		
		g.setFont(Game.FONT);
		g.drawString("SQUARE", 270, 205);
		g.drawString("CIRCLE", 470, 205);
		g.drawString("SHOW", 270, 335);
		g.drawString("TRACKS", 290, 355);
		g.drawString("SHOW", 470, 335);
		g.drawString("CELLS", 490, 355);
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
					else if (button == showCells)
					{
						showCells.setSelected(!showCells.isSelected());
					}
				}
				else
				{
					switch(button.getTextureText())
					{
					case Texture.BUTTON_TEXT_CANCEL:
						squareBlocks.setSelected(game.getBlockTextureType() == Texture.BLOCK_SQUARE);
						circleBlocks.setSelected(!squareBlocks.isSelected());
						showTracks.setSelected(game.getGridTracksVisible());
						game.setGameMode(game.getPreviousGameMode());
						break;
					case Texture.BUTTON_TEXT_APPLY:
						if (squareBlocks.isSelected())
							game.setBlockTextureType(Texture.BLOCK_SQUARE);
						else if (circleBlocks.isSelected())
							game.setBlockTextureType(Texture.BLOCK_CIRCLE);
						
						game.setGridTracksVisible(showTracks.isSelected());
						game.setGridCellsVisible(showCells.isSelected());
						game.setGameMode(game.getPreviousGameMode());
						break;
					}
					break;
				}
			}
		}
	}
}
