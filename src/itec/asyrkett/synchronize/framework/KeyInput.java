package itec.asyrkett.synchronize.framework;

import itec.asyrkett.synchronize.objects.Block;
import itec.asyrkett.synchronize.objects.Cell;
import itec.asyrkett.synchronize.objects.CenterBlock;
import itec.asyrkett.synchronize.objects.Grid;
import itec.asyrkett.synchronize.window.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE)
		{
			System.exit(1);
		}
		
		if (handler.getObject(ObjectId.CenterBlock) != null && handler.getObject(ObjectId.Grid)!= null )
		{
			CenterBlock centerBlock = (CenterBlock) handler.getObject(ObjectId.CenterBlock);
			Grid grid = (Grid) handler.getObject(ObjectId.Grid);
			Cell[][] cells = grid.getCells();
			int row = grid.getRow(centerBlock);
			int column = grid.getColumn(centerBlock);
			Cell destinationCell = null;
			
			if (grid.getHorizontalTrackBounds().contains(centerBlock.getBounds()))
			{
				if (keyCode == KeyEvent.VK_RIGHT && centerBlock.getX() != grid.getX() + grid.getSize() - centerBlock.getSize())
				{
					centerBlock.setUpKeyPressed(false);
					centerBlock.setDownKeyPressed(false);
					centerBlock.setX(centerBlock.getX() + grid.getStep());
				}
				else if (keyCode == KeyEvent.VK_LEFT && centerBlock.getX() != grid.getX())
				{
					centerBlock.setUpKeyPressed(false);
					centerBlock.setDownKeyPressed(false);
					centerBlock.setX(centerBlock.getX() - grid.getStep());
				}
				else if (keyCode == KeyEvent.VK_UP)
				{
					centerBlock.setUpKeyPressed(true);
				}
				else if (keyCode == KeyEvent.VK_DOWN)
				{
					centerBlock.setDownKeyPressed(true);
				}
				else if (keyCode == KeyEvent.VK_SPACE)
				{	
					if (centerBlock.isUpKeyPressed())
					{
						for (int i = row - 1; i >= 0; i--)
						{
							if (cells[i][column].isOccupied())
							{
								if (i != row - 1)
								{
									destinationCell = cells[i + 1][column];
								}
								break;
							}
							else if (i == 0)
							{
								destinationCell = cells[i][column];
								//System.out.println(destinationCell + ", row " + i + ", column" + column + ", " + centerBlock.getX() + ", " + centerBlock.getY());
							}
						}
						//centerBlock.setMovingUp(true);
						//handler.addCenterBlock();
						if (destinationCell != null)
						{
							Block block = centerBlock.toBlock();
							destinationCell.addBlock(block);
							block.setMovingUp(true);
							handler.addObject(block);
							handler.addCenterBlock();
						}
					}
					else if (centerBlock.isDownKeyPressed())
					{
						//System.out.println("GO DOWN!");
						for (int i = row + 1; i < cells.length; i++)
						{
							//System.out.println("row " + i + ", column " + column);
							if (cells[i][column].isOccupied())
							{
								if (i != row + 1)
								{
									destinationCell = cells[i - 1][column];
								}
								break;
							}
							else if (i == cells.length - 1)
							{
								destinationCell = cells[i][column];
								//System.out.println(destinationCell + ", row " + i + ", column" + column + ", " + centerBlock.getX() + ", " + centerBlock.getY());
							}
						}
						//System.out.println(destinationCell);
						if (destinationCell != null)
						{
							Block block = centerBlock.toBlock();
							destinationCell.addBlock(block);
							block.setMovingDown(true);
							handler.addObject(block);
							handler.addCenterBlock();
						}
					}
				}
			}
			if (grid.getVerticalTrackBounds().contains(centerBlock.getBounds()))
			{
				if (keyCode == KeyEvent.VK_UP && centerBlock.getY() != grid.getY())
				{
					centerBlock.setRightKeyPressed(false);
					//centerBlock.setLeftKeyPressed(false);
					centerBlock.setY(centerBlock.getY() - grid.getStep());
				}
				else if (keyCode == KeyEvent.VK_DOWN && centerBlock.getY() != (grid.getY() + grid.getSize() - centerBlock.getSize()))
				{
					centerBlock.setLeftKeyPressed(false);
					//centerBlock.setRightKeyPressed(false);
					centerBlock.setY(centerBlock.getY() + grid.getStep());
				}
				else if (keyCode == KeyEvent.VK_RIGHT)
				{
					centerBlock.setRightKeyPressed(true);
				}
				else if (keyCode == KeyEvent.VK_LEFT)
				{
					centerBlock.setLeftKeyPressed(true);
				}
				else if (keyCode == KeyEvent.VK_SPACE)
				{
					if (centerBlock.isRightKeyPressed())
					{
						for (int i = column + 1; i < cells.length; i++)
						{
							if (cells[row][i].isOccupied())
							{
								if (i != column + 1)
								{
									destinationCell = cells[row][i - 1];
								}
								break;
							}
							else if (i == cells.length - 1)
							{
								destinationCell = cells[row][i];
							}
						}
						if (destinationCell != null)
						{
							Block block = centerBlock.toBlock();
							destinationCell.addBlock(block);
							block.setMovingRight(true);
							handler.addObject(block);
							handler.addCenterBlock();
						}
					}
					else if (centerBlock.isLeftKeyPressed())
					{
						for (int i = column - 1; i >= 0; i--)
						{
							if (cells[row][i].isOccupied())
							{
								if (i != column - 1)
								{
									destinationCell = cells[row][i + 1];
								}
								break;
							}
							else if (i == 0)
							{
								destinationCell = cells[row][i];
							}
						}
						if (destinationCell != null)
						{
							Block block = centerBlock.toBlock();
							destinationCell.addBlock(block);
							block.setMovingLeft(true);
							handler.addObject(block);
							handler.addCenterBlock();
						}
					}
				}
			}
		}
	}
}
