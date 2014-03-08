package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.Block;
import itec.asyrkett.synchronize.objects.Cell;
import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;
import itec.asyrkett.synchronize.window.Game;
import itec.asyrkett.synchronize.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * This class is the game's keylistener and directs
 * the movements of the player-controlled center block
 */
public class KeyInput extends KeyAdapter
{
	private Game game;
	private Handler handler;
	private Grid grid;
	private Cell[][] cells;

	/**
	 * Constructs a key adapter for the game
	 * @param game the game the listener controls
	 */
	public KeyInput(Game game)
	{
		this.game = game;
		this.handler = game.getHandler();
	}

	/**
	 * Gets the current grid and its cells of the game's handler
	 */
	private void getGrid()
	{ 
		grid = (Grid) handler.getObject(ObjectId.Grid);
		if (grid != null)
			cells = grid.getCells();
	}

	public void keyPressed(KeyEvent e)
	{
		//if the game is not in play mode, do nothing
		if (handler.getGame().getGameMode() != GameMode.PLAY)
			return;

		//if a center block is not set, do nothing
		CenterBlock centerBlock = (CenterBlock) handler.getObject(ObjectId.CenterBlock);
		if (centerBlock == null)
			return;
		
		//get the current grid of the game
		getGrid();

		//get the key code of the key pressed
		int keyCode = e.getKeyCode();
		
		int row = grid.getRow(centerBlock);
		int column = grid.getColumn(centerBlock);
		Cell destinationCell = null;

		//if the center block is in the horizontal tracks
		if (grid.getHorizontalTrackBounds().contains(centerBlock.getBounds()))
		{
			//right or left key pressed, reset arrow highlights
			if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_LEFT)
				centerBlock.setDirection(Direction.CENTER);

			//if right key pressed and moving right will stay within the track bounds, move one cell right
			if (keyCode == KeyEvent.VK_RIGHT &&
					centerBlock.getX() != grid.getX() + grid.getSize() - centerBlock.getSize())
				centerBlock.setX(centerBlock.getX() + grid.getStep());
			//if left key pressed and moving left will stay within the track bounds, move one cell left
			else if (keyCode == KeyEvent.VK_LEFT && centerBlock.getX() != grid.getX())
				centerBlock.setX(centerBlock.getX() - grid.getStep());
			//if up key pressed, highlight up arrow
			else if (keyCode == KeyEvent.VK_UP)
				centerBlock.setDirection(Direction.NORTH);
			//if down key pressed, highlight down arrow
			else if (keyCode == KeyEvent.VK_DOWN)
				centerBlock.setDirection(Direction.SOUTH);
			//if space pressed
			else if (keyCode == KeyEvent.VK_SPACE)
			{	
				//if up arrow is selected, move the center block up/north
				if (centerBlock.getDirection() == Direction.NORTH)
				{
					destinationCell = getDestinationCell(row, column, Direction.NORTH);
					moveBlock(centerBlock, destinationCell);
				}
				//if down arrow is selected, move the center block down/south 
				else if (centerBlock.getDirection() == Direction.SOUTH)
				{
					destinationCell = getDestinationCell(row, column, Direction.SOUTH);
					moveBlock(centerBlock, destinationCell);
				}
			}
		}
		//if the center block is in the vertical tracks
		if (grid.getVerticalTrackBounds().contains(centerBlock.getBounds()))
		{
			//if up or down keys pressed, reset arrow highlights
			if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN)
				centerBlock.setDirection(Direction.CENTER);

			//if up key pressed and moving up will keep the block within the track bounds, move up
			if (keyCode == KeyEvent.VK_UP && centerBlock.getY() != grid.getY())
				centerBlock.setY(centerBlock.getY() - grid.getStep());
			//if down key pressed and moving down will keep the block within the track bounds, move down
			else if (keyCode == KeyEvent.VK_DOWN &&
					centerBlock.getY() != (grid.getY() + grid.getSize() - centerBlock.getSize()))
				centerBlock.setY(centerBlock.getY() + grid.getStep());
			//if right key pressed, highlight right arrow
			else if (keyCode == KeyEvent.VK_RIGHT)
				centerBlock.setDirection(Direction.EAST);
			//if left key pressed, highlight left arrow
			else if (keyCode == KeyEvent.VK_LEFT)
				centerBlock.setDirection(Direction.WEST);
			//if space bar is pressed
			else if (keyCode == KeyEvent.VK_SPACE)
			{
				//if right arrow is selected, move right/east
				if (centerBlock.getDirection() == Direction.EAST)
				{
					destinationCell = getDestinationCell(row, column, Direction.EAST);
					moveBlock(centerBlock, destinationCell);
				}
				//if left arrow is selected, move left/west
				else if (centerBlock.getDirection() == Direction.WEST)
				{
					destinationCell = getDestinationCell(row, column, Direction.WEST);
					moveBlock(centerBlock, destinationCell);
				}
			}
		}
		//if the center block is within the exact center, reset all arrows
		if (grid.getVerticalTrackBounds().contains(centerBlock.getBounds()) 
				&& grid.getHorizontalTrackBounds().contains(centerBlock.getBounds()))
		{
			centerBlock.setDirection(Direction.CENTER);
		}
	}

	/**
	 * Moves the given center block to the specified cell of the grid
	 * and adds a new center block of a random color to continue the game
	 * @param centerBlock the block to move
	 * @param destinationCell the destination cell of the center block
	 */
	private void moveBlock(CenterBlock centerBlock, Cell destinationCell)
	{
		if (destinationCell == null)
			return;

		Block block = centerBlock.toBlock();
		destinationCell.addBlock(block);
		block.setMoving(true);

		handler.addObject(block);
		handler.removeCenterBlock();

		try {
			Thread.sleep(800);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		Set<Cell> cellsToRemove = grid.checkForMatch();
		for (Cell cell : cellsToRemove)
		{
			Block toRemove = cell.removeBlock();
			handler.removeObject(toRemove);
		}
		handler.addCenterBlock();

		if (grid.isEmpty())
		{
			nextLevel();
		}
	}
	
	/**
	 * If the game still has more levels, prompts the user to go
	 * to the level selection screen, the menu, or advance to the next level.
	 * Otherwise, prompts the user to go to the level selection screen or menu
	 */
	private void nextLevel()
	{
		if (game.getLevel() == Game.TOTAL_LEVELS)
		{
			Object[] options = {"MENU",
					"LEVEL SELECTION"};
			int n = JOptionPane.showOptionDialog(game,
					"PERFECT! GAME COMPLETE",
					"LEVEL COMPLETED",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[0]);
			if (n == JOptionPane.CANCEL_OPTION)
			{
				game.nextLevel();
			}
			else if (n == JOptionPane.YES_OPTION)
			{
				game.setGameMode(GameMode.MENU);
			}
			else if (n == JOptionPane.NO_OPTION)
			{
				game.setGameMode(GameMode.LEVEL_SELECTION);
			}
		}
		else
		{
			Object[] options = {"MENU",
				"LEVEL SELECTION",
				"NEXT LEVEL"};
			int n = JOptionPane.showOptionDialog(game,
				"PERFECT!",
				"LEVEL COMPLETED",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[2]);
			
			if (n == JOptionPane.CANCEL_OPTION)
			{
				game.nextLevel();
			}
			else if (n == JOptionPane.YES_OPTION)
			{
				game.setGameMode(GameMode.MENU);
			}
			else if (n == JOptionPane.NO_OPTION)
			{
				game.setGameMode(GameMode.LEVEL_SELECTION);
			}
		}
	}

	/**
	 * 
	 * @param row
	 * @param column
	 * @param direction
	 * @return
	 */
	private Cell getDestinationCell(int row, int column, Direction direction)
	{
		Cell currentCell = cells[row][column];
		while (currentCell.getAdjacentCell(direction) != null && !currentCell.getAdjacentCell(direction).isOccupied())
			currentCell = currentCell.getAdjacentCell(direction);
		return currentCell;
	}
}