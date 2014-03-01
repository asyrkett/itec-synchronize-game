package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.Block;
import itec.asyrkett.synchronize.objects.Cell;
import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;
import itec.asyrkett.synchronize.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private Grid grid;
	private Cell[][] cells;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
		this.grid = (Grid) handler.getObject(ObjectId.Grid);
		if (grid != null)
			this.cells = grid.getCells();
	}
	
	public void keyPressed(KeyEvent e)
	{
		if (handler.getGame().getState() != GameState.GAME)
			return;
		//grid.printCells();
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE)
		{
			System.exit(1);
		}
		
		if (handler.getObject(ObjectId.CenterBlock) != null)
		{
			CenterBlock centerBlock = (CenterBlock) handler.getObject(ObjectId.CenterBlock);
			int row = grid.getRow(centerBlock);
			int column = grid.getColumn(centerBlock);
			Cell destinationCell = null;
			
			if (grid.getHorizontalTrackBounds().contains(centerBlock.getBounds()))
			{
				if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_LEFT)
					centerBlock.setDirection(Direction.CENTER);
					//centerBlock.setUpKeyPressed(false);
				
				if (keyCode == KeyEvent.VK_RIGHT && centerBlock.getX() != grid.getX() + grid.getSize() - centerBlock.getSize())
					centerBlock.setX(centerBlock.getX() + grid.getStep());
				else if (keyCode == KeyEvent.VK_LEFT && centerBlock.getX() != grid.getX())
					centerBlock.setX(centerBlock.getX() - grid.getStep());
				else if (keyCode == KeyEvent.VK_UP)
					centerBlock.setDirection(Direction.NORTH);
					//centerBlock.setUpKeyPressed(true);
				else if (keyCode == KeyEvent.VK_DOWN)
					centerBlock.setDirection(Direction.SOUTH);
					//centerBlock.setDownKeyPressed(true);
				else if (keyCode == KeyEvent.VK_SPACE)
				{	
					if (centerBlock.getDirection() == Direction.NORTH)
					{
						destinationCell = getDestinationCell(row, column, Direction.NORTH);
						moveBlock(centerBlock, destinationCell);
					}
					else if (centerBlock.getDirection() == Direction.SOUTH)
					{
						destinationCell = getDestinationCell(row, column, Direction.SOUTH);
						moveBlock(centerBlock, destinationCell);
					}
				}
			}
			if (grid.getVerticalTrackBounds().contains(centerBlock.getBounds()))
			{
				if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN)
					centerBlock.setDirection(Direction.CENTER);
					//centerBlock.setRightKeyPressed(false);
				
				if (keyCode == KeyEvent.VK_UP && centerBlock.getY() != grid.getY())
					centerBlock.setY(centerBlock.getY() - grid.getStep());
				else if (keyCode == KeyEvent.VK_DOWN && centerBlock.getY() != (grid.getY() + grid.getSize() - centerBlock.getSize()))
					centerBlock.setY(centerBlock.getY() + grid.getStep());
				else if (keyCode == KeyEvent.VK_RIGHT)
					centerBlock.setDirection(Direction.EAST);
					//centerBlock.setRightKeyPressed(true);
				else if (keyCode == KeyEvent.VK_LEFT)
					centerBlock.setDirection(Direction.WEST);
					//centerBlock.setLeftKeyPressed(true);
				else if (keyCode == KeyEvent.VK_SPACE)
				{
					if (centerBlock.getDirection() == Direction.EAST)
					{
						destinationCell = getDestinationCell(row, column, Direction.EAST);
						moveBlock(centerBlock, destinationCell);
					}
					else if (centerBlock.getDirection() == Direction.WEST)
					{
						destinationCell = getDestinationCell(row, column, Direction.WEST);
						moveBlock(centerBlock, destinationCell);
					}
				}
			}
		}
	}
	
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
		//System.out.println(cellsToRemove);
		for (Cell cell : cellsToRemove)
		{
			Block toRemove = cell.removeBlock();
			handler.removeObject(toRemove);
		}
		handler.addCenterBlock();
	}
	
	private Cell getDestinationCell(int row, int column, Direction direction)
	{
		Cell destinationCell = null;
		if (direction == Direction.NORTH)
		{
			for (int i = row - 1; i >= 0; i--)
			{
				if (cells[i][column].isOccupied())
				{
					if (i != row - 1)
						destinationCell = cells[i + 1][column];
					break;
				}
				else if (i == 0)
					destinationCell = cells[i][column];
			}
		}
		else if (direction == Direction.SOUTH)
		{
			for (int i = row + 1; i < cells.length; i++)
			{
				if (cells[i][column].isOccupied())
				{
					if (i != row + 1)
						destinationCell = cells[i - 1][column];
					break;
				}
				else if (i == cells.length - 1)
					destinationCell = cells[i][column];
			}
		}
		else if (direction == Direction.EAST)
		{
			for (int i = column + 1; i < cells.length; i++)
			{
				if (cells[row][i].isOccupied())
				{
					if (i != column + 1)
						destinationCell = cells[row][i - 1];
					break;
				}
				else if (i == cells.length - 1)
					destinationCell = cells[row][i];
			}
		}
		else if (direction == Direction.WEST)
		{
			for (int i = column - 1; i >= 0; i--)
			{
				if (cells[row][i].isOccupied())
				{
					if (i != column - 1)
						destinationCell = cells[row][i + 1];
					break;
				}
				else if (i == 0)
					destinationCell = cells[row][i];
			}
		}
		return destinationCell;
	}
}