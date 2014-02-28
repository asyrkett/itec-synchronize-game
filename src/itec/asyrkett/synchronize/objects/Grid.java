package itec.asyrkett.synchronize.objects;

import itec.asyrkett.synchronize.framework.GameObject;
import itec.asyrkett.synchronize.framework.ObjectId;
import itec.asyrkett.synchronize.window.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Grid extends GameObject 
{
	private int dimension;
	private int size;
	private int step;
	private boolean tracksVisible;
	private Cell[][] cells;
	
	public Grid(float x, float y, int dimension)
	{
		super(x, y, ObjectId.Grid);
		this.dimension = dimension;
		this.size = getDefaultGridSize(dimension);
		this.step = getDefaultGridStep(dimension);
		this.tracksVisible = true;
		initCells();
	}
	
	private void initCells()
	{
		cells = new Cell[dimension][dimension];
		for (int row = 0; row < dimension; row++)
		{
			for (int col = 0; col < dimension; col++)
			{
				cells[row][col] = new Cell(this, row, col);
			}
		}
	}
	
	public void tick(LinkedList<GameObject> objects)
	{

	}
	
	public void render(Graphics g)
	{	
		for (int xx = 0; xx < cells.length; xx++)
		{
			for (int yy = 0; yy < cells[xx].length; yy++)
			{
				cells[xx][yy].render(g);
			}
		}
		
		if (tracksVisible)
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.BLUE);
			
			//Horizontal track
			g2d.drawRoundRect((int) x, (int) (y + dimension / 2 * step), size, step, 20, 20);

			//Vertical track
			g2d.drawRoundRect((int)(x + dimension / 2 * step), (int) y, step, size, 20, 20);
		}
	}

	public Rectangle getHorizontalTrackBounds()
	{
		return new Rectangle((int)x, (int)(y + dimension / 2 * step), size, step);
	}
	
	public Rectangle getVerticalTrackBounds()
	{
		return new Rectangle((int)(x + dimension / 2 * step), (int) y, step, size);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, size, size);
	}
	
	public int getDimension()
	{
		return dimension;
	}

	public void setDimension(int dimension)
	{
		this.dimension = dimension;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getStep()
	{
		return step;
	}

	public void setStep(int step)
	{
		this.step = step;
	}
	
	public boolean getTracksVisible()
	{
		return tracksVisible;
	}
	
	public void setTracksVisible(boolean tracksVisible)
	{
		this.tracksVisible = tracksVisible;
	}

	public static int getDefaultGridSize(int dimension)
	{
		int size = Game.HEIGHT - Game.DEFAULT_MARGIN * 4;
		return size - ((size % dimension != 0) ? (size % dimension) : 0);
	}
	
	public static int getDefaultGridStep(int dimension)
	{
		return getDefaultGridSize(dimension) / dimension;
	}
	
	public Cell[][] getCells()
	{
		return cells;
	}
	
	public int getRow(Block block)
	{
		return (int)(block.getY() - y) / step;
	}
	
	public int getColumn(Block block)
	{
		return (int)(block.getX() - x) / step;
	}
	
	public boolean isEmpty()
	{
		boolean empty = true;
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[row].length; column++)
			{
				if (cells[row][column].getBlock() != null)
					return false;
			}
		}
		return empty;
	}

	public void clearCells()
	{
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[row].length; column++)
			{
				cells[row][column].removeBlock();
			}
		}
	}
	
	public void printCells()
	{
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[row].length; column++)
			{
				System.out.print(cells[row][column] + " ");
			}
			System.out.println();
		}
	}
	
	public Set<Cell> checkForMatch()
	{
		Set<Cell> cellsToRemove = new HashSet<Cell>();
		for (int row = 0; row < cells.length; row++)
		{
			for (int column = 0; column < cells[row].length; column++)
			{
				Cell cell = cells[row][column];
				if (cell.isOccupied())
				{
					List<Cell> verticalSGroup = new ArrayList<Cell>();
					Cell southCell = cell;
					while (southCell != null && southCell.isOccupied() && southCell.getBlock().getColor().equals(cell.getBlock().getColor()))
					{
						verticalSGroup.add(southCell);
						southCell = southCell.getSouthCell();
					}
					
					List<Cell> horizontalEGroup = new ArrayList<Cell>();
					Cell eastCell = cell;
					while (eastCell != null && eastCell.isOccupied() && eastCell.getBlock().getColor().equals(cell.getBlock().getColor()))
					{
						horizontalEGroup.add(eastCell);
						eastCell = eastCell.getEastCell();
						//System.out.println(eastCell);
					}
					
					List<Cell> verticalNGroup = new ArrayList<Cell>();
					Cell northCell = cell;
					while (northCell != null && northCell.isOccupied() && northCell.getBlock().getColor().equals(cell.getBlock().getColor()))
					{
						verticalNGroup.add(northCell);
						northCell = northCell.getNorthCell();
					}
					
					List<Cell> horizontalWGroup = new ArrayList<Cell>();
					Cell westCell = cell;
					while (westCell != null && westCell.isOccupied() && westCell.getBlock().getColor().equals(cell.getBlock().getColor()))
					{
						horizontalWGroup.add(westCell);
						westCell = westCell.getWestCell();
					}
					
					if (verticalNGroup.size() + horizontalWGroup.size() >= 4)
					{
						cellsToRemove.addAll(verticalNGroup);
						cellsToRemove.addAll(horizontalWGroup);
					}
					if (verticalSGroup.size() + horizontalWGroup.size() >= 4)
					{
						cellsToRemove.addAll(verticalSGroup);
						cellsToRemove.addAll(horizontalWGroup);
					}
					if (verticalSGroup.size() + horizontalEGroup.size() >= 4)
					{
						cellsToRemove.addAll(verticalSGroup);
						cellsToRemove.addAll(horizontalEGroup);
					}
					if (verticalNGroup.size() + horizontalEGroup.size() >= 4)
					{
						cellsToRemove.addAll(verticalNGroup);
						cellsToRemove.addAll(horizontalEGroup);
					}
					if (horizontalEGroup.size() >= 3)
						cellsToRemove.addAll(horizontalEGroup);
					if (verticalSGroup.size() >= 3)
						cellsToRemove.addAll(verticalSGroup);
				}
			}
		}
		return cellsToRemove;
	}
}